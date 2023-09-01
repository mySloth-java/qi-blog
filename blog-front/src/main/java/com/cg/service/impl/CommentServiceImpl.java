package com.cg.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.cg.dto.CommentDTO;
import com.cg.entity.Comments;
import com.cg.entity.User;
import com.cg.mapper.CommentDao;
import com.cg.mapper.UserDao;
import com.cg.service.CommentService;
import com.cg.util.GlobalException;
import com.cg.vo.CommentsVO;
import com.cg.vo.LoginUser;
import com.cg.vo.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.cg.enums.HttpCode.*;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentDao commentDao;
    @Autowired
    private UserDao userDao;



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

        //2、根据userID和toUserId查询出对应的用户信息
        List<CommentsVO> commentsVOList = commentsList.stream()
                .map(comments -> {
                    CommentsVO commentsVO = BeanUtil.copyProperties(comments, CommentsVO.class);
                    User user = userDao.GetUserById(commentsVO.getUserId());
                    //参数判断，如果用户Id查询不到说明评论时录入就错误了，或者用户删除了
                    if(Objects.isNull(user)){
                        throw new GlobalException(COMMENT_NOT_USERID);
                    }
                    commentsVO.setName(user.getName());
                    //TODO 如果没有回复就不添加，防止报错Null
//                    if(commentsVO.getToCommentId() != null){
//                        User toUser = userDao.GetUserById(commentsVO.getToUserId());
//                        commentsVO.setToName(toUser.getName());
//                    }
                    return commentsVO;
                })
                .collect(Collectors.toList());

        //3、过滤出第一级父评论
        List<CommentsVO> rootComment = commentsVOList.stream()
                .filter(comments -> comments.getParentId() == -1)
                .collect(Collectors.toList());

        //4、对上述评论进行封装，添加到对应的父评论的children下
        List<CommentsVO> commentsVOS = rootComment.stream()
                .map(root -> {
                    List<CommentsVO> children = commentsVOList.stream()
                            .filter(comment -> comment.getParentId() == root.getId())
                            .collect(Collectors.toList());
                    root.setChildren(children);
                    return root;
                })
                .collect(Collectors.toList());

        return ResponseResult.okResult(commentsVOS);
    }

    /**
     * 添加评论
     * @param commentDTO 文章传输对象
     * @return
     */
    @Override
    public ResponseResult AddComment(CommentDTO commentDTO) {
        //1、从security中根据Token获取用户Id
        LoginUser principal = (LoginUser) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        commentDTO.setUserId(principal.getUser().getId());

        //2、调用DAO层添加评论
        Integer integer = commentDao.AddComment(commentDTO);
        if(Objects.isNull(integer)){
            throw new GlobalException(COMMENT_ADD_ERROR);
        }

        return ResponseResult.okResult(integer);
    }
}
