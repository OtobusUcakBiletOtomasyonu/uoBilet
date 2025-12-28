package com.ticketapp.backend.pattern.decorator;

import com.ticketapp.backend.domain.Kullanici;
import com.ticketapp.backend.service.AuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggingAuthDecorator extends AuthServiceDecorator {

    private static final Logger logger = LoggerFactory.getLogger(LoggingAuthDecorator.class);

    public LoggingAuthDecorator(AuthService authService) {
        super(authService);
    }

    @Override
    public Kullanici register(Kullanici kullanici) {
        logger.info("Register işlemi başladı: " + kullanici.getEmail());
        Kullanici result = super.register(kullanici);
        logger.info("Register işlemi tamamlandı: " + result.getId());
        return result;
    }

    @Override
    public String login(String email, String password) {
        logger.info("Login denemesi: " + email);
        try {
            String result = super.login(email, password);
            logger.info("Login başarılı: " + email);
            return result;
        } catch (Exception e) {
            logger.error("Login hatası: " + e.getMessage());
            throw e;
        }
    }
}
