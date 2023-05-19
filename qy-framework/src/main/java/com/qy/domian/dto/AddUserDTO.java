package com.qy.domian.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;

/**
 * @author: qy
 * @create: 2022/12/18 20:51
 * @description:
 **/
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
@Accessors(chain = true)
public class AddUserDTO implements Serializable {
    private static final long serialVersionUID = 214384053889234925L;

    @NotBlank(message = "必须填写用户名")
    private String userName;

    private String nickName;

    private String password;

    private String phonenumber;

    private String email;

    private String sex;

    private String status;

    private List<Long> roleIds;
}
