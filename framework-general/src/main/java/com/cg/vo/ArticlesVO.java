package com.cg.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

//文章实体返回封装
@Data
@AllArgsConstructor
public class ArticlesVO {
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
    private Long likeNumber;
    //预览数
    private Long previewNumber;
    //评论数
    private Long commentNumber;

    //分类表id
    private Long classifyId;
    //用户id
    private Long userId;

    //创建时间
    private Date createTime;
    //修改时间
    private Date updateTime;

    /**
     * 非数据库字段
     */
    //分类名称
    private String categoryName;


}
