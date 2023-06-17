package com.example.filter;

import com.alibaba.fastjson.JSONObject;
import com.example.pojo.Result;
import com.example.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
//@WebFilter(urlPatterns = "/*")
public class LoginCheckFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("过滤器收到请求");
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String url = request.getRequestURL().toString();
        log.info("url:{}", url);
        if (url.contains("login")) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        String jwt = request.getHeader("token");    // 获取 JWT 令牌
        if (!StringUtils.hasLength(jwt)) {
            Result error = Result.error("NOT_LOGIN");
            String notLogin = JSONObject.toJSONString(error);
            response.getWriter().write(notLogin);   // 响应浏览器
            return;
        }
        try {
            JwtUtils.parseJWT(jwt);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("解析失败");
            Result error = Result.error("NOT_LOGIN");
            String notLogin = JSONObject.toJSONString(error);
            response.getWriter().write(notLogin);   // 响应浏览器
            return;
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
