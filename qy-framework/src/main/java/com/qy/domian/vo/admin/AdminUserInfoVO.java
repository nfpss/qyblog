package com.qy.domian.vo.admin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * @author: qy
 * @create: 2022/12/18 21:11
 * @description:
 **/
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Accessors(chain = true)
public class AdminUserInfoVO implements Serializable {

    private static final long serialVersionUID = -632348987524699256L;

    private List<AdminAllRoleVO> roles;

    private List<Long> roleIds;

    private AdminUserVO user;
}
