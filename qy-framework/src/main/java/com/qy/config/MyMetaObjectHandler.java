package com.qy.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.qy.utils.SecurityUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author pengxiaoxi
 * @Description
 * @date 2022/12/15 14:53
 **/
@Component
@Slf4j
public class MyMetaObjectHandler implements MetaObjectHandler {
    private Long userId = 1L;

    @Override
    public void insertFill(MetaObject metaObject) {

        try {
            userId = SecurityUtils.getUserId();
        } catch (Throwable throwable) {
            log.info("用户未登录。。。将userId设置为默认处理人:1");
            userId = 1L;
        }
        log.info("start insert fill ....");
        this.strictInsertFill(metaObject, "createTime", Date.class, new Date());
        this.strictInsertFill(metaObject, "createBy", Long.class, userId);
        this.strictInsertFill(metaObject, "updateTime", Date.class, new Date());
        this.strictInsertFill(metaObject, "updateBy", Long.class, userId);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        //todo 定时任务有问题记得修改
        log.info("start update fill ....");
        this.strictInsertFill(metaObject, "updateTime", Date.class, new Date());
        this.strictInsertFill(metaObject, "updateBy", Long.class, userId);
    }
}
