package com.pps.pps.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:3000", "http://127.0.0.1:3000", "http://localhost:5500", "http://127.0.0.1:5500")  // Añade más puertos si es necesario
                .allowedMethods("GET", "POST", "PATCH", "PUT", "DELETE", "OPTIONS")  // Corregido PUTCH a PATCH y añadidos más métodos
                .allowedHeaders("*")
                .allowCredentials(true);
    }
}