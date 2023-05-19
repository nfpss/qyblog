package com.qy.controller;

import com.qy.domian.dto.CategoryDTO;
import com.qy.domian.entity.PageParameterHelper;
import com.qy.domian.vo.CategoryVO;
import com.qy.domian.vo.PageVO;
import com.qy.domian.vo.admin.AdminCategoryVO;
import com.qy.response.ResponseResult;
import com.qy.service.ArticleService;
import com.qy.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author: qy
 * @create: 2022/12/17 15:29
 * @description:
 **/
@RestController
@RequestMapping("/content/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ArticleService articleService;

    @GetMapping("/listAllCategory")
    public ResponseResult listAllCategory() {
        List<AdminCategoryVO> adminCategoryVOS = categoryService.listAllCategory();
        return ResponseResult.success(adminCategoryVOS);
    }

    @GetMapping("/export")
    @PreAuthorize("@per.hasPermission('content:category:exports')")
    public void export(HttpServletResponse response) {
        categoryService.export(response);
    }

    @GetMapping("/list")
    public ResponseResult list(Integer pageNum, Integer pageSize, String name, String status) {
        PageVO<AdminCategoryVO> pageVO = categoryService.list(new PageParameterHelper(pageNum, pageSize), name, status);
        return ResponseResult.success(pageVO);
    }

    @PostMapping
    public ResponseResult add(@RequestBody CategoryDTO categoryDTO) {
        categoryService.add(categoryDTO);
        return ResponseResult.success();
    }

    @GetMapping("/{id}")
    public ResponseResult getInfo(@PathVariable("id") Long id) {
        CategoryVO categoryVO = categoryService.getInfo(id);
        return ResponseResult.success(categoryVO);
    }

    @PutMapping
    public ResponseResult update(@RequestBody CategoryVO categoryVO) {
        categoryService.updateByVO(categoryVO);
        return ResponseResult.success();
    }

    @DeleteMapping("/{id}")
    public ResponseResult delete(@PathVariable("id") Long id) {
        categoryService.deletById(id);
        return ResponseResult.success();
    }
}
