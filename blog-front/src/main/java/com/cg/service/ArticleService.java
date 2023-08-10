package com.cg.service;

import com.cg.entity.Articles;
import com.cg.vo.ResponseResult;

public interface ArticleService {
    ResponseResult GetArticlesByPage(Integer pageNum, Integer pageSize);

    ResponseResult GetHotArticle();

    ResponseResult GetArticleById(Long id);

    ResponseResult UpdateArticle(Articles articles);
}
