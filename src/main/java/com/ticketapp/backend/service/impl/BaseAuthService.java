package com.ticketapp.backend.service.impl;

import com.ticketapp.backend.domain.Kullanici;
import com.ticketapp.backend.repository.KullaniciRepository;
import com.ticketapp.backend.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("baseAuthService")
public class BaseAuthService implements AuthService {

    @Autowired
    private KullaniciRepository kullaniciRepository;

    @Override
    public Kullanici register(Kullanici kullanici) {
        return kullaniciRepository.save(kullanici);
    }

    @Override
    public String login(String email, String password) {
        Optional<Kullanici> kullaniciOpt = kullaniciRepository.findByEmail(email);
        if (kullaniciOpt.isPresent() && kullaniciOpt.get().getSifre().equals(password)) {
            // Basitlestirilmis, token donebilir
            return "SUCCESS_TOKEN_" + kullaniciOpt.get().getId();
        }
        throw new RuntimeException("Giriş başarısız!");
    }

    @Override
    public Kullanici findByEmail(String email) {
        return kullaniciRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("Kullanıcı bulunamadı"));
    }
}
