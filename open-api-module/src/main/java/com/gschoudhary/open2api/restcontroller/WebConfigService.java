package com.gschoudhary.open2api.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfigService implements WebMvcConfigurer {

    private final ApiRequestInterceptor requestInterceptor;

    @Autowired
    public WebConfigService(ApiRequestInterceptor requestInterceptor) {
        this.requestInterceptor = requestInterceptor;
    }

//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(requestInterceptor)
//                .addPathPatterns("/**"); // Apply interceptor to all endpoints
//    }
}
