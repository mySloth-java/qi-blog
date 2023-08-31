package com.cg.controller;

import com.cg.service.CommentService;
import com.cg.vo.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    /**
     * 根据文章Id分页查询评论表
     * @param articleId 文章Id
     * @param pageNum 起始页
     * @param pageSize 结束页
     * @return
     */
    @GetMapping
    public ResponseResult GetCommentByPage(Long articleId,Integer pageNum,Integer pageSize){
        return commentService.GetCommentByPage(articleId,pageNum,pageSize);
    }




}
