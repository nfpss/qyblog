package com.qy.controller;

import com.qy.domian.vo.AdminCategoryVO;
import com.qy.response.ResponseResult;
import com.qy.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
