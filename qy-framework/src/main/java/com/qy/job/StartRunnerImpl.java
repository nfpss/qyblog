package com.qy.job;

import com.qy.config.RedisCache;
import com.qy.constants.SystemConst;
import com.qy.domian.entity.ArticleDO;
import com.qy.domian.entity.RoleDO;
import com.qy.domian.entity.UserRoleDO;
import com.qy.service.ArticleService;
import com.qy.service.RoleService;
import com.qy.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author pengxiaoxi
 * @Description springboot启动时初始化一些数据
 * @date 2022/12/16 10:41
 **/
@Component
public class StartRunnerImpl implements CommandLineRunner {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private RoleService roleService;

    @Override
    public void run(String... args) throws Exception {
        List<ArticleDO> list = articleService.list();
        Map<String, Integer> map = list.stream().collect(Collectors.toMap(articleDO -> articleDO.getId().toString(), articleDO -> articleDO.getViewCount().intValue()));
        redisCache.setCacheMap(SystemConst.ARTICLE_COUNT_KEY, map);
        List<UserRoleDO> roleDOS = userRoleService.list();
        Map<Long, String> collect = roleService.list().stream().collect(Collectors.toMap(RoleDO::getId, RoleDO::getRoleKey));
        Map<Long, List<Long>> collect1 = roleDOS.stream().collect(Collectors.toMap(UserRoleDO::getUserId, userRoleDO -> {
                    List<Long> roleId = new ArrayList<>();
                    roleId.add(userRoleDO.getRoleId());
                    return roleId;
                }, (s1, s2) -> {
                    s1.addAll(s2);
                    return s1;
                }
        ));
        Map<Long, List<String>> userRoleMap = new HashMap<>(16);
        for (Map.Entry<Long, List<Long>> longListEntry : collect1.entrySet()) {
            List<String> nameList = longListEntry.getValue().stream().map(aLong -> collect.get(aLong)).collect(Collectors.toList());
            userRoleMap.put(longListEntry.getKey(), nameList);
        }
        redisCache.setCacheObject(SystemConst.USER_ROLE_KEY, userRoleMap);
    }
}
