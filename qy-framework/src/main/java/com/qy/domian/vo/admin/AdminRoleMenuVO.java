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
 * @create: 2022/12/18 16:22
 * @description:
 **/
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Accessors(chain = true)
public class AdminRoleMenuVO implements Serializable {
    private static final long serialVersionUID = 7414745195450286207L;

    private Long id;

    private String label;

    private Long parentId;

    private List<AdminRoleMenuVO> children;
}
