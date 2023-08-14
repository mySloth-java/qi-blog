package com.cg.job;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 定时同步redis浏览量数据到sql中
 */
@Component
public class ArticleViewToSql {
    //TODO 同步sql

    @Scheduled(cron = "0/2 * * * * ?")
    public void Test(){

    }
}
