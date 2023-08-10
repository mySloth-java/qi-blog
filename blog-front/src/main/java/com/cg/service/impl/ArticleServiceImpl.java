package com.cg.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.json.JSONNull;
import cn.hutool.json.JSONUtil;
import com.cg.mapper.ArticlesDao;
import com.cg.entity.Articles;
import com.cg.service.ArticleService;
import com.cg.vo.ArticlesVO;
import com.cg.vo.PageVO;
import com.cg.vo.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.cg.util.SystemConstants.*;


@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticlesDao articlesDao;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 分页查询获取文章
     * TODO 2、pageNum参数格式验证  3、redis查询选择问题
     * @param pageNum 当前页数
     * @param pageSize 每页显示数
     * @return
     */
    @Override
    public ResponseResult GetArticlesByPage(Integer pageNum, Integer pageSize) {
        //1、先查询缓存有没有分页的文章数据
        String articlesJSON = stringRedisTemplate.opsForValue().get(ARTICLE_PAGE);
        //非空查询缓存
        if(!articlesJSON.isEmpty()){
            Articles article = JSONUtil.toBean(articlesJSON, Articles.class);
        }

        //2、没有缓存查询数据库并添加到缓存中
        pageNum = (pageNum - 1) * pageSize;
        List<Articles> articles = articlesDao.GetArticlesByPage(pageNum, pageSize);

        for (Articles article :
                articles) {
            String articleToJson = JSONUtil.toJsonPrettyStr(article);
            stringRedisTemplate.opsForValue().set(ARTICLE_PAGE+article.getId(),articleToJson);
        }

        Integer articlesCount = articlesDao.GetArticlesCount();

        //3、将结果封装成VO对象，并返回分页结果信息
        List<ArticlesVO> articlesVOS = BeanUtil.copyToList(articles, ArticlesVO.class);
        PageVO pageVO = new PageVO(articlesVOS, articlesCount);

        //

        return ResponseResult.okResult(pageVO);
    }

    /**
     * 热门文章
     * TODO 1、热门推荐算法
     *          按照一定时间阅读数?我觉得对于小网站不合理，对于大网站很合理
     * @return
     */
    @Override
    public ResponseResult GetHotArticle() {
        return null;
    }

    /**
     * 文章详情查询
     * @param id 唯一标识
     * @return
     */
    @Override
    public ResponseResult GetArticleById(Long id) {
        //1、根据Id查询redis里面是否有缓存数据
        String articleJSON = stringRedisTemplate.opsForValue().get(ARTICLE_PAGE + id);

        Articles articles;

        //2、以及有缓存就查缓存，没有查询数据库并添加到缓存中，设置DDL
        if(articleJSON != null && !articleJSON.isEmpty()){
            articles = JSONUtil.toBean(articleJSON, Articles.class);
        }else {
            articles = articlesDao.GetArticleById(id);
            String articleToJSON = JSONUtil.toJsonPrettyStr(articles);
            stringRedisTemplate.opsForValue().set(ARTICLE_INFO + id,articleToJSON
                    ,EXPIRE_DAY, TimeUnit.DAYS);
        }

        //封装VO对象
        ArticlesVO articlesVO = BeanUtil.copyProperties(articles, ArticlesVO.class);

        return ResponseResult.okResult(articlesVO);
    }

    /**
     * 文章修改
     * @param articles 文章实体
     * @return
     */
    @Override
    public ResponseResult UpdateArticle(Articles articles) {
        return null;
    }



}
