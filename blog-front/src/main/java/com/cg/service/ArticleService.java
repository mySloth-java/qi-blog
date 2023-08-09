package com.cg.service;

import com.cg.vo.ResponseResult;

public interface ArticleService {
    ResponseResult GetArticlesByPage(Integer pageNum, Integer pageSize);
}
