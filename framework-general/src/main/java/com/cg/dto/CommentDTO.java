package com.cg.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDTO {
    //唯一标识
    private Long id;
    //评论内容
    private String content;
    //父评论Id(默认-1无父id）
    private Long parentId;
    //评论文章Id
    private Long articleId;
    //评论发布人的Id
    private String userId;
    //回复评论Id
    private Long toCommentId;
    //回复评论的用户Id
    private String toUserId;
    //评论表类型（1为文章表）
    private String type;
    //评论点赞数
    private Integer likeNumber;
}
