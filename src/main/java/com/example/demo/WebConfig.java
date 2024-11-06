package com.example.demo;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Allow all endpoints
                .allowedOrigins(
                        "https://oracle-ecommerce-app.vercel.app",
                        "http://127.0.0.1:5173",
                        "https://ecommerce-backend-1-yn41.onrender.com"
                ) // Allow specified origins
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Allowed HTTP methods
                .allowedHeaders("*") // Allow all headers
                .allowCredentials(true) // Allow credentials if needed
                .maxAge(3600); // Set max age for caching CORS preflight responses (1 hour)
    }
}
