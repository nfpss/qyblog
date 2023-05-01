package com.qy.domian.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * @author: qy
 * @create: 2022/12/18 21:21
 * @description:
 **/
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
@Accessors(chain = true)
public class UpdateUserDTO implements Serializable {
    private static final long serialVersionUID = 2950431914329034250L;

    private String email;

    private Long id;

    private String nickName;

    private String sex;

    private String userName;

    private String phonenumber;

    private List<Long> roleIds;
}
