package com.dj.movie.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 拦截器配置
 */
//@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    @Autowired
    private MyInterceptor myInterceptor;

    /**
     * 新增拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //向容器注册拦截器
        InterceptorRegistration interceptorRegistration = registry.addInterceptor(myInterceptor);
        //拦截请求
        interceptorRegistration.addPathPatterns("/**");
        //放过请求
        interceptorRegistration.excludePathPatterns("/user/toLogin");
        interceptorRegistration.excludePathPatterns("/user/login");
        interceptorRegistration.excludePathPatterns("/user/toEmail");
        interceptorRegistration.excludePathPatterns("/user/toRegister");
        interceptorRegistration.excludePathPatterns("/user/toHome");
        interceptorRegistration.excludePathPatterns("/user/findUserByName");
        interceptorRegistration.excludePathPatterns("/user/register");
        interceptorRegistration.excludePathPatterns("/user/sendEmail");
        interceptorRegistration.excludePathPatterns("/user/codeLogin");
        interceptorRegistration.excludePathPatterns("/user/out");

    }
}
