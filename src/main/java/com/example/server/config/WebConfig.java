package com.example.server.config;

import com.example.server.service.IStorageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private IStorageService storageService;
    @Value("${upload.accessPath}")
    public String accessPath;
    @Value("${upload.localPath}")
    public String localPath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(accessPath+"**")
                .addResourceLocations("file:"+localPath);
    }
}
