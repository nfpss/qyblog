package com.qy.controller;

import com.qy.domian.dto.UserDTO;
import com.qy.domian.vo.admin.AdminUserInfo;
import com.qy.domian.vo.RouterVO;
import com.qy.exception.BizException;
import com.qy.response.AppHttpCodeEnum;
import com.qy.response.ResponseResult;
import com.qy.service.AdminLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author pengxiaoxi
 * @Description
 * @date 2022/12/16 14:03
 **/
@RestController
public class LoginController {

    @Autowired
    private AdminLoginService adminLoginService;


    @PostMapping("/user/login")
    public ResponseResult login(@RequestBody UserDTO user) {
        if (!StringUtils.hasText(user.getUserName())) {
            //提示 必须要传用户名
            throw new BizException(AppHttpCodeEnum.REQUIRE_USERNAME);
        }
        Map<String, String> map = adminLoginService.login(user);
        return ResponseResult.success(map);
    }

    @GetMapping("/getInfo")
    public ResponseResult<AdminUserInfo> getInfo() {
        AdminUserInfo adminUserInfo = adminLoginService.getInfo();
        return ResponseResult.success(adminUserInfo);
    }

    @GetMapping("/getRouters")
    public ResponseResult getRouters() {
        RouterVO routerVOList = adminLoginService.getRouters();
        return ResponseResult.success(routerVOList);
    }

    @PostMapping("/user/logout")
    public ResponseResult<Void> logout() {
        adminLoginService.logout();
        return ResponseResult.success();
    }
}
