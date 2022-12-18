package com.qy.domian.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author: qy
 * @create: 2022/12/18 22:10
 * @description:
 **/
@Data
public class CategoryDTO implements Serializable {
    private static final long serialVersionUID = -1409891977739341084L;

    private String name;

    private String description;

    private String status;
}
