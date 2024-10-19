package com.example.movie_list_app.config;

import java.util.concurrent.TimeUnit;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer{
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**") // Serve resources under "/static/"
                .addResourceLocations("classpath:/static/") // Location of static resources
                .setCacheControl(CacheControl.maxAge(30, TimeUnit.DAYS).cachePublic()); // Cache for 30 days
    }
}
