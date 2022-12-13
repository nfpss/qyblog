package com.qy.domian.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author: qy
 * @create: 2022/12/13 23:17
 * @description:
 **/
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class CategoryVO implements Serializable {

    private static final long serialVersionUID = 2444360526051055067L;

    private Long id;

    private String name;
}
