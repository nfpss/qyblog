package com.qy.controller;

import com.qy.domian.entity.TagDO;
import com.qy.response.ResponseResult;
import com.qy.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author pengxiaoxi
 * @Description
 * @date 2022/12/16 13:51
 **/
@RestController
@RequestMapping("/content/tag")
public class TagController {

    @Autowired
    private TagService tagService;

    @GetMapping("/list")
    public ResponseResult<List<TagDO>> list() {
        return ResponseResult.success(tagService.list());
    }
}
