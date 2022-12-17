package com.qy.controller;

import com.qy.domian.dto.ArticleDTO;
import com.qy.response.ResponseResult;
import com.qy.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: qy
 * @create: 2022/12/17 15:29
 * @description:
 **/
@RestController
@RequestMapping("/content/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @PostMapping
    public ResponseResult writeArticle(@RequestBody ArticleDTO articleDTO) {
        articleService.saveArtice(articleDTO);
        return ResponseResult.success();
    }
}
