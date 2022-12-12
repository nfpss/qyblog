package com.qy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qy.constants.SystemConst;
import com.qy.domian.entity.ArticleDO;
import com.qy.domian.vo.ArticleVO;
import com.qy.mapper.ArticleMapper;
import com.qy.response.ResponseResult;
import com.qy.service.ArticleService;
import com.qy.utils.BeanCopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 文章表(Article)表服务实现类
 *
 * @author makejava
 * @since 2022-12-12 23:41:18
 */
@Service("articleService")
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, ArticleDO> implements ArticleService {
    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public ResponseResult hotArticleList() {
        LambdaQueryWrapper<ArticleDO> queryWrapper = Wrappers.lambdaQuery(ArticleDO.class)
                .eq(ArticleDO::getStatus, SystemConst.ARTICLE_STATUS_NORMAL)
                .orderByDesc(ArticleDO::getViewCount);
        Page page = new Page(1, 10);
        articleMapper.selectPage(page, queryWrapper);
        List list = BeanCopyUtils.copyList(page.getRecords(), ArticleVO.class);
        return ResponseResult.okResult(list);
    }
}

