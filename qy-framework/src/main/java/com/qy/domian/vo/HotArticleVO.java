package com.qy.domian.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author: qy
 * @create: 2022/12/13 0:29
 * @description:
 **/
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Accessors(chain = true)
public class HotArticleVO implements Serializable {

    private static final long serialVersionUID = -2316607113031671363L;

    private Long id;
    //标题
    private String title;

    //访问量
    private Long viewCount;
}
