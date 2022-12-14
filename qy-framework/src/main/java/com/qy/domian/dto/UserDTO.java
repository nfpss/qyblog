package com.qy.domian.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author pengxiaoxi
 * @Description
 * @date 2022/12/14 17:00
 **/
@Data
public class UserDTO implements Serializable {

    private static final long serialVersionUID = -5395500295016560434L;

    private String username;

    private String password;

}
