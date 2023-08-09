package com.cg.service.impl;

import com.cg.mapper.ArticlesDao;
import com.cg.entity.Articles;
import com.cg.service.ArticleService;
import com.cg.vo.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticlesDao articlesDao;

    //TODO Redis缓存 pageNum参数格式验证
    @Override
    public ResponseResult GetArticlesByPage(Integer pageNum, Integer pageSize) {
        //1、先查询缓存有没有分页的文章数据

        //2、没有缓存查询数据库并添加到缓存中
        pageNum = (pageNum - 1) * pageSize;
        List<Articles> articles = articlesDao.GetArticlesByPage(pageNum, pageSize);

        //3、将结果封装成VO对象

        return ResponseResult.okResult(articles);
    }
}
