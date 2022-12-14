package com.qy.domian.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author pengxiaoxi
 * @Description
 * @date 2022/12/14 16:20
 **/
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class LinkVO implements Serializable {

    private static final long serialVersionUID = -5033492701309568751L;

    private Long id;


    private String name;

    private String logo;

    private String description;
    //网站地址
    private String address;
}
