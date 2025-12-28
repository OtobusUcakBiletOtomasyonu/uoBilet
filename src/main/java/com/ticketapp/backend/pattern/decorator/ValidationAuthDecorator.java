package com.ticketapp.backend.pattern.decorator;

import com.ticketapp.backend.domain.Kullanici;
import com.ticketapp.backend.service.AuthService;

public class ValidationAuthDecorator extends AuthServiceDecorator {

    public ValidationAuthDecorator(AuthService authService) {
        super(authService);
    }

    @Override
    public Kullanici register(Kullanici kullanici) {
        if (kullanici.getEmail() == null || !kullanici.getEmail().contains("@")) {
            throw new IllegalArgumentException("Geçersiz email adresi!");
        }
        if (kullanici.getSifre() == null || kullanici.getSifre().length() < 6) {
            throw new IllegalArgumentException("Şifre en az 6 karakter olmalı!");
        }
        return super.register(kullanici);
    }
}
