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
 * @create: 2022/2/7 7:24
 * @description:
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Accessors(chain = true)
public class AdminArticleVO implements Serializable {
    private static final long serialVersionUID = 89272529988557273L;

    private Long id;

    //标题
    private String title;

    private String content;

    //文章摘要
    private String summary;
    //所属分类id

    private Long categoryId;
    //缩略图
    private String thumbnail;

    //访问量
    private Long viewCount;

    private Date createTime;

    private String isComment;

    private String isTop;

    private String status;
}
