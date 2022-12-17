package com.qy.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qy.domian.dto.ArticleDTO;
import com.qy.domian.entity.ArticleDO;
import com.qy.domian.vo.ArticleDetailVO;
import com.qy.domian.vo.HotArticleVO;
import com.qy.domian.vo.PageVO;
import com.qy.response.ResponseResult;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * 文章表(Article)表服务接口
 *
 * @author makejava
 * @since 2022-12-12 23:41:18
 */
@Service
public interface ArticleService extends IService<ArticleDO> {

    ResponseResult<List<HotArticleVO>> hotArticleList();

    ResponseResult<PageVO> articleList(Integer pageNum, Integer pageSize, Long categoryId);

    ArticleDetailVO getArticleDetail(Long id);

    void updateViewCount(Long id);

    void saveArtice(ArticleDTO articleDTO);
}

