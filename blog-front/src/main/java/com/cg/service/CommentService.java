package com.cg.service;

import com.cg.dto.CommentDTO;
import com.cg.vo.ResponseResult;

public interface CommentService {

    ResponseResult GetCommentByPage(Long articleId, Integer pageNum, Integer pageSize);

    ResponseResult AddComment(CommentDTO commentDTO);
}
