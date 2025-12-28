package com.ticketapp.backend.pattern.decorator;

import com.ticketapp.backend.domain.Kullanici;
import com.ticketapp.backend.service.AuthService;

public abstract class AuthServiceDecorator implements AuthService {
    protected AuthService wrappedAuthService;

    public AuthServiceDecorator(AuthService authService) {
        this.wrappedAuthService = authService;
    }

    @Override
    public Kullanici register(Kullanici kullanici) {
        return wrappedAuthService.register(kullanici);
    }

    @Override
    public String login(String email, String password) {
        return wrappedAuthService.login(email, password);
    }

    @Override
    public Kullanici findByEmail(String email) {
        return wrappedAuthService.findByEmail(email);
    }
}
