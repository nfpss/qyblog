package com.qy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qy.constants.SystemConst;
import com.qy.domian.entity.ArticleDO;
import com.qy.domian.entity.CategoryDO;
import com.qy.domian.vo.ArticleVO;
import com.qy.domian.vo.HotArticleVO;
import com.qy.domian.vo.PageVO;
import com.qy.mapper.ArticleMapper;
import com.qy.mapper.CategoryMapper;
import com.qy.response.ResponseResult;
import com.qy.service.ArticleService;
import com.qy.service.CategoryService;
import com.qy.utils.BeanCopyUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 文章表(Article)表服务实现类
 *
 * @author makejava
 * @since 2022-12-12 23:41:18
 */
@Service("articleService")
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, ArticleDO> implements ArticleService {
    @Resource
    private ArticleMapper articleMapper;

    @Resource
    private CategoryMapper categoryMapper;

    @Resource
    private CategoryService categoryService;

    @Override
    public ResponseResult hotArticleList() {
        LambdaQueryWrapper<ArticleDO> queryWrapper = Wrappers.lambdaQuery(ArticleDO.class)
                .eq(ArticleDO::getStatus, SystemConst.ARTICLE_STATUS_NORMAL)
                .orderByDesc(ArticleDO::getViewCount);
        Page page = new Page(1, 10);
        articleMapper.selectPage(page, queryWrapper);
        List list = BeanCopyUtils.copyList(page.getRecords(), HotArticleVO.class);
        return ResponseResult.okResult(list);
    }

    //    要求：①只能查询正式发布的文章 ②置顶的文章要显示在最前面
    @Override
    public ResponseResult articleList(Integer pageNum, Integer pageSize, Long categoryId) {
        LambdaQueryWrapper<ArticleDO> queryWrapper = Wrappers.lambdaQuery(ArticleDO.class)
                .eq(ArticleDO::getStatus, SystemConst.ARTICLE_STATUS_NORMAL)
                .eq((Objects.nonNull(categoryId)&&categoryId!=0), ArticleDO::getCategoryId, categoryId)
                .orderByDesc(ArticleDO::getIsTop);
        Page<ArticleDO> page = new Page<>(pageNum == null ? 1 : pageNum, pageSize == null ? 10 : pageSize);
        List<ArticleDO> articleDOList = articleMapper.selectPage(page, queryWrapper).getRecords();
        Set<Long> longSet = articleDOList.stream().map(ArticleDO::getCategoryId).collect(Collectors.toSet());
        Map<Long, String> categoryNameMap = categoryService.listByIds(longSet).stream()
                .collect(Collectors.toMap(CategoryDO::getId, CategoryDO::getName));
        List<ArticleVO> articleVOList = BeanCopyUtils.copyList(articleDOList, ArticleVO.class)
                .stream()
                .map(articleVO -> {
                    articleVO.setCategoryName(categoryNameMap.get(articleVO.getCategoryId()));
                    return articleVO;
                }).collect(Collectors.toList());
        return ResponseResult.okResult(new PageVO(articleVOList, page.getTotal()));
    }
}

