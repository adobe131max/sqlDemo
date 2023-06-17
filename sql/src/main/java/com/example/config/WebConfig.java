package com.example.config;

import com.example.interceptor.LoginCheck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private LoginCheck loginCheck;

    @Override
    public void addInterceptors(InterceptorRegistry registry) { // 注册拦截器
        registry.addInterceptor(loginCheck).addPathPatterns("/**").excludePathPatterns("/login");
    }
}
