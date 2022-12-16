package com.qy.job;

import com.qy.config.RedisCache;
import com.qy.constants.SystemConst;
import com.qy.domian.entity.ArticleDO;
import com.qy.service.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author pengxiaoxi
 * @Description
 * @date 2022/12/16 10:47
 **/
@Component
@Slf4j
public class UpdateViewCountJob {

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private ArticleService articleServicel;

    @Scheduled(cron = "0 0/3 * * * ? ")
    public void updateViewCount(){
        log.info("开始刷新浏览量");
        doUpdateViewCount();
        log.info("刷新浏览量结束" );
    }

    private void doUpdateViewCount() {
        Map<String, Integer> cacheMap = redisCache.getCacheMap(SystemConst.ARTICLE_COUNT_KEY);
        List<ArticleDO> articleDOList = cacheMap.entrySet().stream()
                .map(entry -> new ArticleDO(Long.parseLong(entry.getKey()), Long.parseLong(entry.getValue().toString())))
                .collect(Collectors.toList());
        articleServicel.updateBatchById(articleDOList);
    }
}
