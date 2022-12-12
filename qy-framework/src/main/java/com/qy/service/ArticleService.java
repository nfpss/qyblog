package com.qy.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qy.domian.entity.ArticleDO;
import com.qy.response.ResponseResult;
import org.springframework.stereotype.Service;


/**
 * 文章表(Article)表服务接口
 *
 * @author makejava
 * @since 2022-12-12 23:41:18
 */
@Service
public interface ArticleService extends IService<ArticleDO> {

    ResponseResult hotArticleList();
}

