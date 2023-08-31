package com.cg.mapper;

import com.cg.entity.Comments;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CommentDao {
    //分页查询评论列表
    List<Comments> GetCommentByPage(@Param("articleId") Long articleId,
                                    @Param("pageNum") Integer pageNum,
                                    @Param("pageSize") Integer pageSize);
}
