package com.qy.domian.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author: qy
 * @create: 2022/2/7 23:00
 * @description:
 **/
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
@Accessors(chain = true)
public class ArticleUpdateDTO implements Serializable {

    private static final long serialVersionUID = -6048487353039566L;

    private String title;

    private String thumbnail;

    private String isTop;

    private String isComment;

    private String content;

    private List<Long> tags;

    private Long categoryId;

    private String status;

    private String summary;

    private Long createBy;

    private Date createTime;
            
    private Integer delFlag;

    private Long id;

    private Long updateBy;

    private Date updateTime;

    private Long viewCount;
}
