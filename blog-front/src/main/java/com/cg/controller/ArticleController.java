package com.cg.controller;

import com.cg.service.ArticleService;
import com.cg.vo.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    /**
     * 分页查询文章
     * @param pageNum 当前页数
     * @param pageSize 每页展示数据
     * @return
     */
    @GetMapping
    public ResponseResult GetArticlesByPage(Integer pageNum,Integer pageSize){
        return articleService.GetArticlesByPage(pageNum,pageSize);
    }



}
