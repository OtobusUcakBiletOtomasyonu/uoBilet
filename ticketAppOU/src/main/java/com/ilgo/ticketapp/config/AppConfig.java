package com.ilgo.ticketapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Uygulamanın genel yapılandırma sınıfı.
 */
@Configuration
public class AppConfig {

    @Bean
    public IdGenerator idGenerator() {
        return new IdGenerator();
    }
}

