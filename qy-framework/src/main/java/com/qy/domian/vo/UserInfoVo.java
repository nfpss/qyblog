package com.qy.domian.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author pengxiaoxi
 * @Description
 * @date 2022/12/14 16:49
 **/
@Data
@Accessors(chain = true)
public class UserInfoVo implements Serializable {

    private static final long serialVersionUID = 3853244040257771870L;
    /**
     * 主键
     */
    private Long id;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 头像
     */
    private String avatar;

    private String sex;

    private String email;


}
