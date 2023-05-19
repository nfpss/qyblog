package com.qy.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qy.domian.entity.CommentDO;
import com.qy.domian.vo.CommentVO;
import com.qy.domian.vo.PageVO;


/**
 * 评论表(Comment)表服务接口
 *
 * @author makejava
 * @since 2022-12-15 10:30:16
 */
public interface CommentService extends IService<CommentDO> {

    PageVO<CommentVO> commentList(String commentType,Long articleId, Integer pageNum, Integer pageSize);

    void comment(CommentDO commentDO);
}
