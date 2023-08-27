package com.cg.filter;

import cn.hutool.json.JSONUtil;
import com.cg.enums.HttpCode;
import com.cg.util.WebUtil;
import com.cg.vo.ResponseResult;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 认证失败处理器
 */
@Component
public class AuthenticationErrorPointer implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        authException.printStackTrace();
        ResponseResult errorResult = ResponseResult.errorResult(HttpCode.IDENTIFY_ERROR);
        String error = JSONUtil.toJsonPrettyStr(errorResult);
        WebUtil.renderString(response,error);

    }
}
