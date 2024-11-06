package com.example.demo;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOriginPatterns("https://oracle-ecommerce-app.vercel.app",
                        "https://oracle-ecommerce-app-git-main-lucas-projects-f61d5cb5.vercel.app",
                        "https://oracle-ecommerce-k7omklaqi-lucas-projects-f61d5cb5.vercel.app") // Your frontend URL
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")

                .maxAge(3600); // Cache the CORS config for 1 hour
    }
}
