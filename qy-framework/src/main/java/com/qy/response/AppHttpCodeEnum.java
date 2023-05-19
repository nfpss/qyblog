package com.qy.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author: qy
 * @create: 2022/12/13 0:03
 * @description:
 **/
@AllArgsConstructor
@Getter
public enum AppHttpCodeEnum {
    // 成功
    SUCCESS(200, "操作成功"),
    // 登录
    NEED_LOGIN(401, "需要登录后操作"),
    NO_OPERATOR_AUTH(403, "无权限操作"),
    SYSTEM_ERROR(500, "出现错误"), FILE_TYPE_ERROR(1003, "文件类型错误"),
    USERNAME_EXIST(501, "用户名已存在"), USER_NOT_EXIST(1001, "用户不存在"),
    PHONENUMBER_EXIST(502, "手机号已存在"), EMAIL_EXIST(503, "邮箱已存在"),
    REQUIRE_USERNAME(504, "必需填写用户名"), PARAM_ERROR(1000, "请求参数异常"),
    LOGIN_ERROR(505, "用户名或密码错误"), TOKEN_PARSE_ERROR(1002, "token解析失败");
    private int code;
    private String msg;
}
