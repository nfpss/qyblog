package com.qy.domian.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @author pengxiaoxi
 * @Description
 * @date 2022/12/14 17:00
 **/
@Data
public class UserDTO implements Serializable {

    private static final long serialVersionUID = -5395500295016560434L;

    @NotBlank(message = "用户名不能为空")
    private String userName;

    @NotBlank(message = "密码不能为空")
    private String password;

    @NotBlank(message = "邮箱不能为空")
    private String email;

    @NotBlank(message = "用户昵称不能为空")
    private String nickName;

}
