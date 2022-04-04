package com.sparta.team5finalproject.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration

public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://springwatch.shop")
                .allowedMethods("")
                .allowedHeaders("")
                .exposedHeaders("")
                .allowCredentials(true);
    }
}
