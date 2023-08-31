package com.cg.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.cg.entity.Comments;
import com.cg.mapper.CommentDao;
import com.cg.service.CommentService;
import com.cg.util.GlobalException;
import com.cg.vo.CommentsVO;
import com.cg.vo.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.cg.enums.HttpCode.COMMENT_PAGE_ERROR;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentDao commentDao;


    /**
     * 根据文章Id分页查询评论表
     * @param articleId 文章Id
     * @param pageNum 起始页
     * @param pageSize 结束页
     * @return
     */
    @Override
    public ResponseResult GetCommentByPage(Long articleId, Integer pageNum, Integer pageSize) {
        pageNum = ((pageNum - 1) * pageSize);
        if(pageNum < 0){
            throw new GlobalException(COMMENT_PAGE_ERROR);
        }
        //1、根据文章Id和分页信息查询出评论表
        List<Comments> commentsList = commentDao.GetCommentByPage(articleId, pageNum, pageSize);

        //2、TODO 调用方法，根据userID和toUserId查询出对应的用户信息



        //3、过滤出第一级父评论
        List<Comments> rootComment = commentsList.stream()
                .filter(comments -> comments.getParentId() == -1)
                .collect(Collectors.toList());

        //4、对上述评论进行封装，添加到对应的父评论的children下
        List<CommentsVO> commentsVOS = rootComment.stream()
                .map(root -> {
                    CommentsVO rootVO = BeanUtil.copyProperties(root, CommentsVO.class);
                    List<CommentsVO> children = commentsList.stream()
                            .filter(comment -> comment.getParentId() == root.getId())
                            .map(child -> BeanUtil.copyProperties(child, CommentsVO.class))
                            .collect(Collectors.toList());
                    rootVO.setChildren(children);
                    return rootVO;
                })
                .collect(Collectors.toList());

        return ResponseResult.okResult(commentsVOS);
    }
}
