package com.ticketapp.backend.dto.request;

import com.ticketapp.backend.domain.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequest {
    private String email;
    private String sifre;
    private String ad;
    private String soyad;
    private String telefon; // Yolcu icin
    private Role role; // YOLCU, YONETICI
}
