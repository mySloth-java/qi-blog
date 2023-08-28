package com.cg.entity;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * (Articles)实体类
 *
 * @author makejava
 * @since 2023-08-08 21:23:46
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Articles {
    //唯一标识
    private Long id;
    //标题
    private String title;
    //文章摘要
    private String summarize;

    //文章内容
    private String content;
    //文章预览图片
    private String img;
    //点赞数
    private Integer likeNumber;
    //预览数
    private Integer previewNumber;
    //评论数
    private Long commentNumber;
    //登陆才允许评论(0为否，1为是)
    private String commentFlag;

    //状态标记，是否为草稿(0为草稿，1为非草稿)
    private String statusFlag;

    //逻辑删除标记(0为否，1为是)
    private String delFlag;
    //置顶标记(0为否，1为是)
    private String topFlag;
    //创建时间
    private Date createTime;
    //修改时间
    private Date updateTime;
    //分类表id
    private Long classifyId;
    //用户id
    private Long userId;


}

