package com.cg.controller;

import com.cg.dto.CommentDTO;
import com.cg.service.CommentService;
import com.cg.vo.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    /**
     * 添加评论
     * @param commentDTO 传输DTO
     * @return
     */
    @PostMapping
    public ResponseResult AddComment(@RequestBody CommentDTO commentDTO){
        return commentService.AddComment(commentDTO);
    }





}
