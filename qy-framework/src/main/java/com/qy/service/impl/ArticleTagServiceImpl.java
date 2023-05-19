package com.qy.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qy.domian.entity.ArticleTagDO;
import com.qy.mapper.ArticleTagMapper;
import com.qy.service.ArticleTagService;
import org.springframework.stereotype.Service;

/**
 * 文章标签关联表(ArticleTag)表服务实现类
 *
 * @author makejava
 * @since 2022-12-17 15:55:14
 */
@Service("articleTagService")
public class ArticleTagServiceImpl extends ServiceImpl<ArticleTagMapper, ArticleTagDO> implements ArticleTagService {

}

