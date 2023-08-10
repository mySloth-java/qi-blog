package com.cg.mapper;

import com.cg.entity.Articles;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ArticlesDao {

    //分页查询文章
    List<Articles> GetArticlesByPage(@Param("pageNum") Integer pageNum,
                                   @Param("pageSize") Integer pageSize);

    //文章总数目
    Integer GetArticlesCount();

    //文章详情
    Articles GetArticleById(@Param("id") Long id);


}
