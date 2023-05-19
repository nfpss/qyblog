package com.qy.domian.vo.admin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author: qy
 * @create: 2022/12/18 5:29
 * @description:
 **/
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
@Accessors(chain = true)
public class AdminRoleMenuDetailVO implements Serializable {

    private static final long serialVersionUID = 8208302344974747824L;

    private Long id;
    //菜单名称
    private String menuName;
    //父菜单ID
    private Long parentId;
    //显示顺序
    private Integer orderNum;
    //路由地址
    private String path;
    //菜单类型（M目录 C菜单 F按钮）
    private String menuType;
    //菜单状态（0显示 隐藏）
    private String visible;
    //菜单状态（0正常 停用）
    private String status;

    //菜单图标
    private String icon;
    //备注
    private String remark;
}
