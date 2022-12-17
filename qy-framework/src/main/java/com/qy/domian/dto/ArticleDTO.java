package com.qy.domian.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * @author: qy
 * @create: 2022/12/17 15:47
 * @description:
 **/
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
@Accessors(chain = true)
public class ArticleDTO implements Serializable {

    private static final long serialVersionUID = 643410680670749043L;

    private String title;

    private String thumbnail;

    private String isTop;

    private String isComment;

    private String content;

    private List<Long> tags;

    private Long categoryId;

    private Integer status;

    private String summary;
}
