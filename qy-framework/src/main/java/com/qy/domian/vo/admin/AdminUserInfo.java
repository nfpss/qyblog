package com.qy.domian.vo.admin;

import com.qy.domian.vo.UserInfoVo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * @author pengxiaoxi
 * @Description
 * @date 2022/12/16 15:17
 **/
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
@Accessors(chain = true)
public class AdminUserInfo implements Serializable {

    private static final long serialVersionUID = -7008619472644051437L;

    private UserInfoVo user;

    private List<String> roles;

    private List<String> permissions;
}
