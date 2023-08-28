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

    //查询所有文章
    List<Articles> GetArticles();

    //文章总数目
    Integer GetArticlesCount();

    //文章详情
    Articles GetArticleById(@Param("id") Long id);

    //查询所有文章的分类id并去重
    List<Integer> GetCategoryIds();

    //添加文章
    Integer AddArticle(Articles articles);

    //批量更新数据



}
