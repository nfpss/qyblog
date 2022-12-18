package com.qy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qy.constants.SystemConst;
import com.qy.domian.entity.CommentDO;
import com.qy.domian.entity.PageParameterHelper;
import com.qy.domian.entity.UserDO;
import com.qy.domian.vo.CommentVO;
import com.qy.domian.vo.PageVO;
import com.qy.mapper.CommentMapper;
import com.qy.service.CommentService;
import com.qy.service.UserService;
import com.qy.utils.BeanCopyUtils;
import com.qy.utils.ValidatorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 评论表(Comment)表服务实现类
 *
 * @author makejava
 * @since 2022-12-15 10:30:16
 */
@Service("commentService")
public class CommentServiceImpl extends ServiceImpl<CommentMapper, CommentDO> implements CommentService {

    @Autowired
    private UserService userService;

    @Override
    public PageVO<CommentVO> commentList(String commentType, Long articleId, Integer pageNum, Integer pageSize) {
        //查询当前文章对应的根评论
        PageParameterHelper parameterHelper = new PageParameterHelper(pageNum, pageSize);
        LambdaQueryWrapper<CommentDO> wrapper = Wrappers.lambdaQuery(CommentDO.class)
                .eq(Objects.equals(commentType, SystemConst.COMMENT_ARTICLE_TYPE),CommentDO::getArticleId, articleId)
                .eq(CommentDO::getRootId, SystemConst.ROOT_COMMENT)
                .eq(CommentDO::getType, commentType)
                .orderByAsc(CommentDO::getCreateTime);
        Page<CommentDO> page = page(new Page<>(parameterHelper.getCurrentPage(), parameterHelper.getPageSize()), wrapper);
        List<CommentDO> rootCommentList = page.getRecords();
        //所有根VO
        List<CommentVO> commentVOS = setNickNameToVO(rootCommentList);
        //根评论对应的子评论集合
        Set<Long> longSet = rootCommentList.stream().map(CommentDO::getId).collect(Collectors.toSet());
        //防止没评论时出错
        if (CollectionUtils.isEmpty(longSet)) {
            return new PageVO<>(commentVOS, 0L);
        }
        LambdaQueryWrapper<CommentDO> queryWrapper = Wrappers.lambdaQuery(CommentDO.class)
                .in(CommentDO::getRootId, longSet)
                .orderByAsc(CommentDO::getCreateTime);
        Map<Long, List<CommentDO>> longListMap = list(queryWrapper).stream()
                .collect(Collectors.toMap(CommentDO::getRootId, commentDO -> {
                    List<CommentDO> commentDOList = new ArrayList<>();
                    commentDOList.add(commentDO);
                    return commentDOList;
                }, (commentDOS, commentDOS2) -> {
                    commentDOS.addAll(commentDOS2);
                    return commentDOS;
                }));
        for (CommentVO commentVO : commentVOS) {
            List<CommentDO> commentDOList = longListMap.get(commentVO.getId());
            commentDOList = longListMap.get(commentVO.getId()) == null ? Collections.emptyList() : commentDOList;
            List<CommentVO> commentVOS1 = setNickNameToVO(commentDOList);
            commentVO.setChildren(commentVOS1);
        }
        return new PageVO<>(commentVOS, page.getTotal());
    }

    @Override
    public void comment(CommentDO commentDO) {
        ValidatorUtils.valid(commentDO);
        save(commentDO);
    }

    /**
     * 设置用户昵称并且转成VO
     */
    private List<CommentVO> setNickNameToVO(List<CommentDO> list) {
        List<CommentVO> commentVOList = BeanCopyUtils.copyList(list, CommentVO.class);
        //获取当前评论的评论人昵称<评论人id，评论人昵称>
        Set<Long> creatIdSet = commentVOList.stream().map(CommentVO::getCreateBy).collect(Collectors.toSet());
        Map<Long, String> idToNickName = new HashMap<>(16);
        if (CollectionUtils.isNotEmpty(creatIdSet)) {
            idToNickName = userService.listByIds(creatIdSet).stream()
                    .collect(Collectors.toMap(UserDO::getId, UserDO::getNickName));
        }
        //需要设置评论谁的昵称
        Set<Long> set = commentVOList.stream().filter(commentVO -> commentVO.getRootId() != SystemConst.ROOT_COMMENT)
                .map(CommentVO::getToCommentUserId)
                .collect(Collectors.toSet());
        Map<Long, String> collect = new HashMap<>(16);
        if (CollectionUtils.isNotEmpty(set)) {
            collect = userService.listByIds(set).stream().collect(Collectors.toMap(UserDO::getId, UserDO::getNickName));
        }
        for (CommentVO commentVO : commentVOList) {
            commentVO.setUsername(idToNickName.get(commentVO.getCreateBy()));
            if (CollectionUtils.isNotEmpty(collect) && commentVO.getRootId() != SystemConst.ROOT_COMMENT) {
                commentVO.setToCommentUserName(collect.get(commentVO.getToCommentUserId()));
            }
        }
        return commentVOList;
    }
}


