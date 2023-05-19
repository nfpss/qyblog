package com.qy.domian.vo.admin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @author: qy
 * @create: 2022/12/18 20:40
 * @description:
 **/
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
@Accessors(chain = true)
public class AdminAllRoleVO implements Serializable {

    private static final long serialVersionUID = 6771293300997144914L;

    private Long createBy;

    private Date createTime;

    private Integer delFlag;

    private Long id;

    private String remark;

    private String rolekey;

    private String roleName;

    private Integer roleSort;

    private String status;

    private Long updateBy;

}
