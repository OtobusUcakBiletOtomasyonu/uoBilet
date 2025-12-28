package com.ticketapp.backend.config;

import com.ticketapp.backend.pattern.decorator.LoggingAuthDecorator;
import com.ticketapp.backend.pattern.decorator.ValidationAuthDecorator;
import com.ticketapp.backend.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Autowired
    @Qualifier("baseAuthService")
    private AuthService baseAuthService;

    @Bean
    public AuthService decoratedAuthService() {
        // Decorator Chain: Validation -> Logging -> Base
        // Disaridan cagrildiginda once Validation, sonra Logging calisir.
        return new ValidationAuthDecorator(
                new LoggingAuthDecorator(baseAuthService));
    }

    @Bean
    public org.springframework.web.servlet.config.annotation.WebMvcConfigurer corsConfigurer() {
        return new org.springframework.web.servlet.config.annotation.WebMvcConfigurer() {
            @Override
            public void addCorsMappings(org.springframework.web.servlet.config.annotation.CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("http://localhost:5173")
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        .allowedHeaders("*")
                        .allowCredentials(true);
            }
        };
    }
}
