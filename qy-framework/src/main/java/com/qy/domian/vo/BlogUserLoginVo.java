package com.qy.domian.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author pengxiaoxi
 * @Description
 * @date 2022/12/14 16:47
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BlogUserLoginVo implements Serializable {

    private static final long serialVersionUID = 6931732535499356274L;

    private String token;
    private UserInfoVo userInfo;
}
