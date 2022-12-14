package com.qy.controller;

import com.qy.domian.vo.CategoryVO;
import com.qy.response.ResponseResult;
import com.qy.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author: qy
 * @create: 2022/12/13 23:18
 * @description:
 **/
@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/getCategoryList")
    public ResponseResult<List<CategoryVO>> getCategoryList(){
        return categoryService.getCategoryList();
    }
}
