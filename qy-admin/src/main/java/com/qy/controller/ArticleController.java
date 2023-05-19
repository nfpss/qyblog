package com.qy.controller;

import com.qy.domian.dto.ArticleDTO;
import com.qy.domian.dto.ArticleUpdateDTO;
import com.qy.domian.entity.PageParameterHelper;
import com.qy.domian.vo.PageVO;
import com.qy.domian.vo.admin.AdminArticleDetailVO;
import com.qy.domian.vo.admin.AdminArticleVO;
import com.qy.response.ResponseResult;
import com.qy.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/list")
    public ResponseResult<PageVO<AdminArticleVO>> list(Integer pageNum, Integer pageSize, String title, String summary) {
        PageVO<AdminArticleVO> pageVO = articleService.list(new PageParameterHelper(pageNum, pageSize), title, summary);
        return ResponseResult.success(pageVO);
    }

    @GetMapping("/{id}")
    public ResponseResult<AdminArticleDetailVO> getInfo(@PathVariable("id") Long id) {
        AdminArticleDetailVO detailVO = articleService.getInfo(id);
        return ResponseResult.success(detailVO);
    }

    @PutMapping()
    public ResponseResult update(@RequestBody ArticleUpdateDTO articleUpdateDTO) {
        articleService.updateByDTO(articleUpdateDTO);
        return ResponseResult.success();
    }

    @DeleteMapping("/{id}")
    public ResponseResult delete(@PathVariable("id") Long id) {
        articleService.delete(id);
        return ResponseResult.success();
    }
}
