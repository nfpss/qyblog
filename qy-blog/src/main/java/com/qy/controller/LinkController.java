package com.qy.controller;

import com.qy.domian.vo.LinkVO;
import com.qy.response.ResponseResult;
import com.qy.service.LinkService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author pengxiaoxi
 * @Description
 * @date 2022/12/14 16:21
 **/
@RestController
@RequestMapping("/link")
public class LinkController {
    @Autowired
    private LinkService linkService;

    @GetMapping("/getAllLink")
    @ApiOperation(value = "获取所有友链",tags = "友链")
    public ResponseResult getAllLink() {
        List<LinkVO> linkVOList = linkService.getAllLink();
        return ResponseResult.success(linkVOList);
    }
}
