package com.ds.mall.auth.server.config;

import com.ds.mall.auth.server.interceptor.ClientsFeignInterceptor;
import com.ds.mall.common.exception.AuthExceptionHanlder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author tb
 * @date 2019/1/11 14:14
 */
@Configuration
@Primary
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(getServiceAuthRestInterceptor()).addPathPatterns("/service/**");
//        registry.addInterceptor(getUserAuthRestInterceptor()).addPathPatterns("/service/**");
    }

    @Bean
    public AuthExceptionHanlder exceptionHanlder(){
        return new AuthExceptionHanlder();
    }


    @Bean
    public ClientsFeignInterceptor feignInterceptor(){
        return new ClientsFeignInterceptor();
    }
}
