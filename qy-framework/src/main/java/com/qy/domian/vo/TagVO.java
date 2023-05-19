package com.qy.domian.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author: qy
 * @create: 2022/12/17 15:01
 * @description:
 **/
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
@Accessors(chain = true)
public class TagVO implements Serializable {

    private static final long serialVersionUID = -6528011691386729298L;

    private Long id;

    private String name;

    private String remark;
}
