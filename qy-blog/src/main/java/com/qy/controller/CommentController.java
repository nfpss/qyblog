package com.qy.controller;

import com.qy.constants.SystemConst;
import com.qy.domian.entity.CommentDO;
import com.qy.domian.vo.CommentVO;
import com.qy.domian.vo.PageVO;
import com.qy.response.ResponseResult;
import com.qy.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author pengxiaoxi
 * @Description
 * @date 2022/12/15 10:32
 **/
@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    /**
     * 文章评论列表
     */
    @GetMapping("/commentList")
    public ResponseResult<PageVO<CommentVO>> commentList(Long articleId, Integer pageNum, Integer pageSize) {
        PageVO<CommentVO> pageVO = commentService.commentList(SystemConst.COMMENT_ARTICLE_TYPE, articleId, pageNum, pageSize);
        return ResponseResult.success(pageVO);
    }

    /**
     * 新增文章评论
     */
    @PostMapping()
    public ResponseResult<Void> comment(@RequestBody CommentDO commentDO) {
        commentService.comment(commentDO);
        return ResponseResult.success();
    }

    /**
     * 友链评论接口
     */
    @GetMapping("/linkCommentList")
    public ResponseResult<PageVO<CommentVO>> linkCommentList(Integer pageNum, Integer pageSize) {
        PageVO<CommentVO> pageVO = commentService.commentList(SystemConst.COMMENT_LINK_TYPE, null, pageNum, pageSize);
        return ResponseResult.success(pageVO);
    }
}
