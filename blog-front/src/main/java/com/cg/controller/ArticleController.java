package com.cg.controller;

import com.cg.entity.Articles;
import com.cg.service.ArticleService;
import com.cg.vo.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    /**
     * 热门文章查询
     * TODO
     * @return
     */
    @GetMapping("/hot")
    public ResponseResult GetHotArticle(){
        return articleService.GetHotArticle();
    }

    /**
     * 文章详情页面查询
     * @param id 文章唯一标识(自增) 没必要使用UUID
     * @return
     */
    @GetMapping("/detail/{id}")
    public ResponseResult GetArticleById(@PathVariable("id") Long id){
        return articleService.GetArticleById(id);
    }

    /**
     * 文章修改
     * TODO
     * @param articles 文章实体
     * @return
     */
    @PostMapping
    public ResponseResult UpdateArticle(Articles articles){
        return articleService.UpdateArticle(articles);
    }

    /**
     * 点赞数增加
     * TODO
     * @param id 文章标识
     * @return
     */
    @PostMapping("/likeNumber/{id}")
    public ResponseResult UpdateLikeNumber(@PathVariable("id") Long id){
        return articleService.UpdateLikeNumber(id);
    }


    /**
     * 浏览量增加
     * @param id 文章标识
     * @return
     */
    @PostMapping("/previewNumber/{id}")
    public ResponseResult UpdatePreviewNumber(@PathVariable("id") Long id){
        return articleService.UpdatePreviewNumber(id);
    }

    /**
     * 文章发布
     * @param articles 文章实体
     * @return
     */
    @PostMapping("/punish")
    public ResponseResult AddArticle(@RequestBody Articles articles){
        return articleService.AddArticle(articles);
    }

    /**
     * 浏览量查询
     * TODO
     * @param id
     * @return
     */
    @GetMapping("/previewNumber/{id}")
    public ResponseResult GetPreviewNumber(@PathVariable("id") Long id){
        return articleService.GetPreviewNumber(id);
    }
}
