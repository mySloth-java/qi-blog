package com.cg.config;

import com.cg.filter.AuthenticationErrorPointer;
import com.cg.filter.AuthenticationTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private AuthenticationTokenFilter authenticationTokenFilter;
    @Autowired
    private AuthenticationErrorPointer authenticationErrorPointer;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                //关闭crf
                .csrf().disable()
                //不通过session获取SecurityContext
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                //匿名访问
                .antMatchers("/user/login","/user/register").anonymous()
                //授权访问
                .antMatchers("/file/**","/article/punish","/article/likeNumber/**",
                        "/user/logout")
                                .authenticated()
                .antMatchers(HttpMethod.POST,"/article","/comment").authenticated()

                //所有用户都可以访问
                .anyRequest().permitAll()
        ;
        //此过滤器是根据token来验证，所以必须要保证此过滤器在登录过滤器之前
        http.addFilterBefore(authenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);

        //添加认证和授权错误处理器
        http.exceptionHandling()
                .authenticationEntryPoint(authenticationErrorPointer);
        //关闭默认注销功能
//        http.logout().disable();

    }

    @Bean
    public BCryptPasswordEncoder BCPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
