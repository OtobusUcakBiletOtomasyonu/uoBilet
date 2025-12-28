package com.ticketapp.backend.service;

import com.ticketapp.backend.domain.Kullanici;

public interface AuthService {
    Kullanici register(Kullanici kullanici);

    String login(String email, String password);

    Kullanici findByEmail(String email);
}
