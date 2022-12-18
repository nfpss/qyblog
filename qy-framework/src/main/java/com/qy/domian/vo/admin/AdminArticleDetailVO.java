package com.qy.domian.vo.admin;

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
 * @create: 2022/2/7 7:4
 * @description:
 **/
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
@Accessors(chain = true)
public class AdminArticleDetailVO implements Serializable {

    private static final long serialVersionUID = -78467645422744759L;

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

    private Long createBy;

    private Integer delFlag;
    
    private String isComment;
    
    private String isTop;
    
    private List<Long> tags;
    
    private Date updateTime;
    
    private Long updateBy;

    private String status;
    
    
}
