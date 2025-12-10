package com.ilgo.ticketapp.config;

import org.springframework.stereotype.Component;

/**
 * Spring Security olmadan basit doğrulama.
 */
@Component
public class SecurityConfig {

    /**
     * Basit kullanıcı doğrulama.
     */
    public boolean validateUser(String username, String password) {
        return "admin".equals(username) && "1234".equals(password);
    }

    /**
     * Basit token doğrulama.
     */
    public boolean validateToken(String token) {
        return token != null && "ILGO-TOKEN".equals(token);
    }
}

