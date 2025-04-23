package com.sermaluc.prueba.apiuser.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.sermaluc.prueba.apiuser.security.TokenAccess;


@Configuration
public class SecurityConfig implements WebMvcConfigurer {

    @Autowired
    private TokenAccess tokenFilter;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(tokenFilter);
    }
}
