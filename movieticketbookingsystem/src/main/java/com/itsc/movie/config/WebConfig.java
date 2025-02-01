package com.itsc.movie.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Serve JS files from the /js folder in the webapp directory
        registry.addResourceHandler("/js/**")
                .addResourceLocations("file:src/main/webapp/js/");

        // Serve HTML pages from the /pages folder in the webapp directory
        registry.addResourceHandler("/pages/**")
                .addResourceLocations("file:src/main/webapp/pages/");
    }
}
