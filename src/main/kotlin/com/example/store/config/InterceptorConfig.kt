package com.example.store.config

import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

class InterceptorConfig: WebMvcConfigurer {


    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(AuthCheckInterceptor())
            .addPathPatterns("/**")
            .excludePathPatterns("/", "/account/login", "account/logout", "/account/register", "/products/**")
    }
}