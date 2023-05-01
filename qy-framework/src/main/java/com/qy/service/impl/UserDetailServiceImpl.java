package com.qy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.google.common.collect.Lists;
import com.qy.constants.SystemConst;
import com.qy.domian.entity.LoginUser;
import com.qy.domian.entity.MenuDO;
import com.qy.domian.entity.UserDO;
import com.qy.domian.entity.UserRoleDO;
import com.qy.exception.BizException;
import com.qy.response.AppHttpCodeEnum;
import com.qy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author pengxiaoxi
 * @Description
 * @date 2022/12/14 17:15
 **/
@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRoleServiceImpl userRoleService;

    @Autowired
    private MenuServiceImpl menuService;


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserDO userDO = userService.getOne(Wrappers.lambdaQuery(UserDO.class).eq(UserDO::getUserName, s));
        if (Objects.isNull(userDO)) {
            throw new BizException(AppHttpCodeEnum.USER_NOT_EXIST);
        }
        Long id = userDO.getId();
        //如果用户id为1代表管理员，roles 中只需要有admin，permissions中需要有所有菜单类型为C或者F的，状态为正常的，未被删除的权限
        List<UserRoleDO> list = userRoleService.list(Wrappers.lambdaQuery(UserRoleDO.class).eq(UserRoleDO::getUserId, id));
        if (list.stream().anyMatch(userRoleDO -> Objects.equals(userRoleDO.getRoleId(), SystemConst.ROLE_BY_ADMIN))) {
            ArrayList<String> strings = Lists.newArrayList(SystemConst.MENU_TYPE_C, SystemConst.MENU_TYPE_F);
            LambdaQueryWrapper<MenuDO> queryWrapper = Wrappers.lambdaQuery(MenuDO.class)
                    .eq(MenuDO::getStatus, SystemConst.MENU_STATUS_NORMAL)
                    .in(MenuDO::getMenuType, strings);
            List<String> permList = menuService.list(queryWrapper).stream().map(MenuDO::getPerms).collect(Collectors.toList());
            return new LoginUser(userDO, permList);
        }
        List<String> notAdmin = menuService.getBaseMapper().listPermissByUserId(id);

        return new LoginUser(userDO, notAdmin);
    }
}
