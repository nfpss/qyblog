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
 * @create: 2022/12/18 16:51
 * @description:
 **/
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
@Accessors(chain = true)
public class RoleDTO implements Serializable {
    private static final long serialVersionUID = -9041100561397993747L;

    private String roleName;
    //角色权限字符串
    private String roleKey;
    //显示顺序
    private Integer roleSort;
    //角色状态（0正常 1停用）
    private String status;

    private List<Long> menuIds;

    private String remark;
}
