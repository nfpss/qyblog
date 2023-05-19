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
 * @create: 2022/12/18 17:07
 * @description:
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Accessors(chain = true)
public class AdminRoleMenDetail implements Serializable {

    private static final long serialVersionUID = 9350183851154618L;

    private List<AdminRoleMenuVO> menus;

    private List<Long> checkedKeys;
}
