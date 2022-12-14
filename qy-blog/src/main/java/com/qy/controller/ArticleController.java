package com.qy.controller;

import com.qy.domian.vo.ArticleDetailVO;
import com.qy.domian.vo.HotArticleVO;
import com.qy.domian.vo.PageVO;
import com.qy.response.ResponseResult;
import com.qy.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author: qy
 * @create: 2022/12/12 23:44
 * @description:
 **/
@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @GetMapping("/hotArticleList")
    public ResponseResult<List<HotArticleVO>> hotArticleList() {
        return articleService.hotArticleList();
    }

    @GetMapping("/articleList")
    public ResponseResult<PageVO> articleList(Integer pageNum, Integer pageSize, Long categoryId){
        return articleService.articleList(pageNum,pageSize,categoryId);
    }

    @GetMapping("/{id}")
    public ResponseResult<ArticleDetailVO> getArticleDetail(@PathVariable("id") Long id){
       ArticleDetailVO articleDetailVO=articleService.getArticleDetail(id);
       return ResponseResult.success(articleDetailVO);
    }
}
