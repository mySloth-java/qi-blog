package com.cg.service;

import com.cg.entity.Articles;
import com.cg.vo.ResponseResult;

public interface ArticleService {
    ResponseResult GetArticlesByPage(Integer pageNum, Integer pageSize);

    ResponseResult GetHotArticle();

    ResponseResult GetArticleById(Long id);

    ResponseResult UpdateArticle(Articles articles);

    ResponseResult UpdateLikeNumber(Long id);

    ResponseResult UpdatePreviewNumber(Long id);

    ResponseResult AddArticle(Articles articles);
}
