package com.cg.util;

import cn.hutool.core.date.DateTime;

/**
 * 系统全局常量
 */
public final class SystemConstants {
    //redis文章分页
    public static final String ARTICLE_PAGE = "article:page:";
    //redis文章详情
    public static final String ARTICLE_INFO = "article:info:";
    //redis文章点赞数
    public static final String ARTICLE_LIKE = "article:like:";
    //redis文章浏览量
    public static final String ARTICLE_VIEW = "article:view:";
    //redis用户信息
    public static final String USER_INFO = "user:info:";

    //博客默认昵称前缀
    public static final String USER_NAME = "venBlog-";

    //过期时间
    public static final Integer EXPIRE_DAY = 7;


}
