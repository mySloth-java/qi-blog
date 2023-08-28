package com.cg.runner;

import com.cg.entity.Articles;
import com.cg.mapper.ArticlesDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.cg.util.SystemConstants.ARTICLE_LIKE;
import static com.cg.util.SystemConstants.ARTICLE_VIEW;

/**
 * 项目初启动同步sql数据到缓存中
 */
@Component
public class ArticleViewRunner implements CommandLineRunner {
    @Autowired
    private ArticlesDao articlesDao;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public void run(String... args) throws Exception {
        //1、查询所有文章信息
        List<Articles> articles = articlesDao.GetArticles();

        //2、使用stream流收集为map集合，以文章id为key，浏览量数为map
        Map<String, String> Preview = articles.stream()
                .collect(Collectors.toMap(article -> String.valueOf(article.getId())
                        , article -> article.getPreviewNumber().toString()));
        Map<String, String > Like = articles.stream()
                .collect(Collectors.toMap(article -> String.valueOf(article.getId()),
                        article -> article.getLikeNumber().toString()));

        //3、将map集合存储到redis中
        stringRedisTemplate.opsForHash().putAll(ARTICLE_VIEW,Preview);
        stringRedisTemplate.opsForHash().putAll(ARTICLE_LIKE,Like);
    }
}
