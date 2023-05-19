package com.qy.domian.vo.admin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author: qy
 * @create: 2022/12/18 16:02
 * @description:
 **/
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Accessors(chain = true)
public class AdminRoleVO implements Serializable {
    private static final long serialVersionUID = -1995217100672920688L;

    private Long id;

    private String rolekey;

    private String roleName;

    //显示顺序
    private Integer roleSort;
    //角色状态（0正常 1停用）
    private String status;

    private String remark;
}
