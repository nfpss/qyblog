package com.qy.domian.vo.admin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @author: qy
 * @create: 2022/12/18 20:15
 * @description:
 **/
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
@Accessors(chain = true)
public class AdminUserVO implements Serializable {
    private static final long serialVersionUID = -4641527275385606810L;

    private String avatar;

    private Date createTime;

    private String email;

    private Long id;

    private String nickName;

    private String phonenumber;

    private String sex;

    private String status;

    private Long updateBy;

    private Date updateTime;

    private String userName;
}
