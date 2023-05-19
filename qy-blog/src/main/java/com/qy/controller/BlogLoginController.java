package com.qy.controller;

import com.qy.domian.dto.UserDTO;
import com.qy.domian.vo.BlogUserLoginVo;
import com.qy.response.ResponseResult;
import com.qy.service.BlogLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author pengxiaoxi
 * @Description
 * @date 2022/12/14 16:54
 **/
@RestController
public class BlogLoginController {

    @Autowired
    private BlogLoginService blogLoginService;


    @PostMapping("/login")
    public ResponseResult<BlogUserLoginVo> login(@RequestBody UserDTO user) {
        BlogUserLoginVo blogUserLoginVo = blogLoginService.login(user);
        return ResponseResult.success(blogUserLoginVo);
    }

    @PostMapping("/logout")
    public ResponseResult<Void> logout(){
        blogLoginService.logout();
        return ResponseResult.success();
    }

    @GetMapping("/test")
    public String logis(){
        return "sf";
    }
}
