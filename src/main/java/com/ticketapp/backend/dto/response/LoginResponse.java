package com.ticketapp.backend.dto.response;

import com.ticketapp.backend.domain.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class LoginResponse {
    private String token;
    private Long id;
    private String ad;
    private String soyad;
    private String email;
    private Role role;
    private String telefon; // Useful for autofill
}
