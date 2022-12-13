package com.qy.domian.vo;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * @author: qy
 * @create: 2022/12/13 23:46
 * @description:
 **/
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class ArticleVO implements Serializable {

    private static final long serialVersionUID = 5803145634065511979L;

    private Long id;

    //标题
    private String title;

    //文章摘要
    private String summary;
    //所属分类id
    private String categoryName;

    //所属分类id
    @JSONField(serialize = false)
    private Long categoryId;
    //缩略图
    private String thumbnail;

    //访问量
    private Long viewCount;

    private Date createTime;


}
