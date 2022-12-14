package com.qy.domian.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * @author pengxiaoxi
 * @Description
 * @date 2022/12/14 15:43
 **/
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Accessors(chain = true)
public class ArticleDetailVO {
    @TableId
    private Long id;

    //标题
    private String title;
    //文章内容
    private String content;
    //缩略图
    private String thumbnail;

    private String categoryName;
    //访问量
    private Long viewCount;
    //是否允许评论 1是，0否
    private String isComment;
}
