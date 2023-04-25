package com.graduate.backend.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@ComponentScan
@Configuration
public class FileConfig implements WebMvcConfigurer {
    public final static String pathPatterns = "/file/**"; //访问前缀
    public final static String location ="C:/backend/file/";//本地路径
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(pathPatterns)
                .addResourceLocations("file:"+location);
    }
}
