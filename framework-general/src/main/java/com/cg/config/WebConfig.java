package com.cg.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    //跨域配置
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        //设置允许跨域的路径
        registry.addMapping("/**")
            .allowedOriginPatterns("*")//设置跨域请求的域名
            .allowCredentials(true)//是否允许cookie
            .allowedMethods("GET","POST","DELETE","PUT")//设置允许的请求方式
            .allowedHeaders("*")//设置允许的Header属性
            .maxAge(3600);//跨域允许时间
    }
}
