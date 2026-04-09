package com.wath.thymeleafdemo.configration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Path;
import java.nio.file.Paths;


@Configuration
public class ImageUploadConfig implements WebMvcConfigurer {

    @Value("${file.server-path}")
    String serverPath;

    @Value("${file.client-path}")
    String clientPath;


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        Path uploadPath = Paths.get(serverPath).toAbsolutePath().normalize();
        registry.addResourceHandler(clientPath)
                .addResourceLocations(uploadPath.toUri().toString());
    }
}
