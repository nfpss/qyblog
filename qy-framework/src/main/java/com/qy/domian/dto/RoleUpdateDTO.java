package com.qy.domian.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author: qy
 * @create: 2022/12/18 17:34
 * @description:
 **/
@Data
public class RoleUpdateDTO implements Serializable {
    private static final long serialVersionUID = -4704607909564539773L;

    private Long id;

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
