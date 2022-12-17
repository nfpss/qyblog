package com.qy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qy.config.RedisCache;
import com.qy.constants.SystemConst;
import com.qy.domian.dto.ArticleDTO;
import com.qy.domian.entity.ArticleDO;
import com.qy.domian.entity.ArticleTagDO;
import com.qy.domian.entity.CategoryDO;
import com.qy.domian.vo.ArticleDetailVO;
import com.qy.domian.vo.ArticleVO;
import com.qy.domian.vo.HotArticleVO;
import com.qy.domian.vo.PageVO;
import com.qy.mapper.ArticleMapper;
import com.qy.response.ResponseResult;
import com.qy.service.ArticleService;
import com.qy.service.ArticleTagService;
import com.qy.service.CategoryService;
import com.qy.utils.BeanCopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;
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
    private ArticleTagService articleTagService;

    @Resource
    private CategoryService categoryService;

    @Autowired
    private RedisCache redisCache;

    @Override
    public ResponseResult<List<HotArticleVO>> hotArticleList() {
        LambdaQueryWrapper<ArticleDO> queryWrapper = Wrappers.lambdaQuery(ArticleDO.class)
                .eq(ArticleDO::getStatus, SystemConst.ARTICLE_STATUS_NORMAL)
                .orderByDesc(ArticleDO::getViewCount);

        Page page = new Page(1, 10);
        articleMapper.selectPage(page, queryWrapper);
        List<HotArticleVO> list = BeanCopyUtils.copyList(page.getRecords(), HotArticleVO.class);
        return ResponseResult.success(list);
    }

    //    要求：①只能查询正式发布的文章 ②置顶的文章要显示在最前面
    @Override
    public ResponseResult<PageVO> articleList(Integer pageNum, Integer pageSize, Long categoryId) {
        LambdaQueryWrapper<ArticleDO> queryWrapper = Wrappers.lambdaQuery(ArticleDO.class)
                .eq(ArticleDO::getStatus, SystemConst.ARTICLE_STATUS_NORMAL)
                .eq((Objects.nonNull(categoryId) && categoryId != 0), ArticleDO::getCategoryId, categoryId)
                .orderByDesc(ArticleDO::getIsTop);
        Page<ArticleDO> page = new Page<>(pageNum == null ? 1 : pageNum, pageSize == null ? 10 : pageSize);
        List<ArticleDO> articleDOList = articleMapper.selectPage(page, queryWrapper).getRecords();
        Set<Long> longSet = articleDOList.stream().map(ArticleDO::getCategoryId).collect(Collectors.toSet());
        Map<Long, String> categoryNameMap = categoryService.listByIds(longSet).stream()
                .collect(Collectors.toMap(CategoryDO::getId, CategoryDO::getName));
        List<ArticleVO> articleVOList = BeanCopyUtils.copyList(articleDOList, ArticleVO.class)
                .stream()
                .peek(articleVO -> articleVO.setCategoryName(categoryNameMap.get(articleVO.getCategoryId())))
                .collect(Collectors.toList());
        return ResponseResult.success(new PageVO(articleVOList, page.getTotal()));
    }

    @Override
    public ArticleDetailVO getArticleDetail(Long id) {
        ArticleDO articleDO = articleMapper.selectById(id);
        ArticleDetailVO articleDetailVO = Optional.ofNullable(articleDO)
                .map(articleDO1 -> {
                    CategoryDO categoryDO = categoryService.getById(articleDO.getCategoryId());
                    ArticleDetailVO detailVO = BeanCopyUtils.copyBean(articleDO1, ArticleDetailVO.class);
                    detailVO.setCategoryName(categoryDO.getName() == null ? "" : categoryDO.getName());
                    detailVO.setViewCount(Long.parseLong(redisCache.getCacheMapValue(SystemConst.ARTICLE_COUNT_KEY, id.toString()).toString()));
                    return detailVO;
                })
                .orElseGet(ArticleDetailVO::new);
        return articleDetailVO;
    }

    @Override
    public void updateViewCount(Long id) {
        redisCache.incrementMapValue(id.toString(), 1);
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public void saveArtice(ArticleDTO articleDTO) {
        ArticleDO articleDO = BeanCopyUtils.copyBean(articleDTO, ArticleDO.class);
        save(articleDO);
        List<Long> tags = articleDTO.getTags();
        List<ArticleTagDO> collect = tags.stream().map(integer -> new ArticleTagDO(articleDO.getId(), integer)).collect(Collectors.toList());
        articleTagService.saveBatch(collect);
    }
}

