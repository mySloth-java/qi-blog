package com.cg.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentsVO {
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
    //评论点赞数
    private Integer likeNumber;
    //置顶标志（0为非置顶）
    private String topFlag;
    //创建时间
    private Date createTime;

    /**
     * 数据库没有字段
     */
    private String name;//用户信息名称
    private String toName;//回复的用户名
    private List<CommentsVO> children;//子评论
}
