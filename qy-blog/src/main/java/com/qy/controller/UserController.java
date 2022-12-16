package com.qy.controller;

import com.qy.annotion.LogPrint;
import com.qy.domian.dto.UserDTO;
import com.qy.domian.entity.UserDO;
import com.qy.domian.vo.UserInfoVo;
import com.qy.response.ResponseResult;
import com.qy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author pengxiaoxi
 * @Description
 * @date 2022/12/15 15:35
 **/
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/userInfo")
    public ResponseResult<UserInfoVo> getUserInfo() {
        UserInfoVo userInfoVo = userService.getUserInfo();
        return ResponseResult.success(userInfoVo);
    }

    @LogPrint
    @PutMapping("/userInfo")
    public ResponseResult<Void> updateUserInfo(@RequestBody UserDO user){
       userService.updateUserInfo(user);
       return ResponseResult.success();
    }

    @LogPrint(value = "用户注册接口")
    @PostMapping("/register")
    public ResponseResult<Void> register(@RequestBody UserDTO userDTO){
        userService.register(userDTO);
        return ResponseResult.success();
    }
}
