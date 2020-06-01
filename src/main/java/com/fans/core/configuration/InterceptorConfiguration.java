package com.fans.core.configuration;

import com.fans.core.interceptors.PermissionInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * className: InterceptorConfiguration
 *
 * @author k
 * @version 1.0
 * @description 拦截器注册
 * @date 2020-06-01 22:10
 **/
@Configuration
public class InterceptorConfiguration implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new PermissionInterceptor());
    }
}
