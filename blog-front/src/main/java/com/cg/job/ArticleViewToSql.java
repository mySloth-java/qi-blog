package com.cg.job;

import com.cg.mapper.ArticlesDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Map;

import static com.cg.util.SystemConstants.ARTICLE_VIEW;

/**
 * 定时同步redis浏览量数据到sql中
 */
@Component
public class ArticleViewToSql {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private ArticlesDao articlesDao;
    /**
     * 15Min同步一次浏览量
     */
    @Scheduled(cron = "0 0/5 * * * ?")
    public void UpdatePreviewRedisToSql(){
        //1、从redis中获取数据
        Map<Object, Object> previewMap = stringRedisTemplate.opsForHash().entries(ARTICLE_VIEW);

        Map<Long,Integer> map = null;
        //2、根据id添加
        for (Map.Entry<Object,Object> entry:
                previewMap.entrySet()) {
            map.put(Long.parseLong(entry.getKey().toString()),
                    Integer.parseInt(entry.getValue().toString())
            );
        }

        //3、TODO 批量更新

    }
}
