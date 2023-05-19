package com.qy.domian.vo.admin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author: qy
 * @create: 2022/12/17 15:33
 * @description:
 **/
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
@Accessors(chain = true)
public class AdminCategoryVO implements Serializable {

    private static final long serialVersionUID = 2095240280255564220L;

    private Long id;

    private String description;

    private String name;

    private String status;
}
