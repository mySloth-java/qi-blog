package com.cg.filter;

import cn.hutool.json.JSONUtil;
import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTUtil;
import com.cg.enums.HttpCode;
import com.cg.util.GlobalException;
import com.cg.util.WebUtil;
import com.cg.vo.LoginUser;
import com.cg.vo.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

import static com.cg.util.SystemConstants.USER_INFO;

/**
 * Token验证过滤器
 */
@Component
public class AuthenticationTokenFilter extends OncePerRequestFilter {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //1、从前端的请求头获取token
        String token = request.getHeader("token");
        //检查给定的字符串是否不为空且至少包含一个非空白字符
        if(!StringUtils.hasText(token)){
            filterChain.doFilter(request,response);
            return;
        }

        //2、解析token，获取里面的用户Id
        JWT jwt = null;
        try {
            jwt = JWTUtil.parseToken(token);
        } catch (Exception e) {
            e.printStackTrace();
            //创建返回体，转为JSON字符串并调用工具类写入前端
            ResponseResult errorResult = ResponseResult.errorResult(HttpCode.NOT_LOGIN);
            String error = JSONUtil.toJsonPrettyStr(errorResult);
            WebUtil.renderString(response,error);
            return;
        }
        //TODO 验证密钥以及有效期
        String id = (String)jwt.getPayload("id");


        //3、根据用户id从redis取出用户数据，反序列化存入SecurityHolder中
        String info = stringRedisTemplate.opsForValue().get(USER_INFO + id);
        LoginUser loginUser = JSONUtil.toBean(info, LoginUser.class);
        if(Objects.isNull(loginUser.getUser())){
            throw new GlobalException(HttpCode.NOT_LOGIN);
        }

        //必须使用3个参数构造函数，里面调用了父类的set方法，设置为已认证
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginUser, null, null);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);


        filterChain.doFilter(request,response);
    }
}
