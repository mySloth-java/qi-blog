package com.cg.runner;

import org.springframework.boot.CommandLineRunner;

/**
 * 项目初启动同步sql数据到缓存中
 */
public class ArticleViewRunner implements CommandLineRunner {
    //TODO 同步redis
    @Override
    public void run(String... args) throws Exception {
        System.out.println("aa");
    }
}
