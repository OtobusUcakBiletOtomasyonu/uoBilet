package com.ticketapp.backend.controller;

import com.ticketapp.backend.domain.Kullanici;
import com.ticketapp.backend.domain.Role;
import com.ticketapp.backend.domain.Yolcu;
import com.ticketapp.backend.domain.Yonetici;
import com.ticketapp.backend.dto.request.LoginRequest;
import com.ticketapp.backend.dto.request.RegisterRequest;
import com.ticketapp.backend.dto.response.ApiResponse;
import com.ticketapp.backend.dto.response.LoginResponse;
import com.ticketapp.backend.pattern.singleton.SessionManager;
import com.ticketapp.backend.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService; // Decorated Service
    private final SessionManager sessionManager;

    @Autowired
    public AuthController(AuthService decoratedAuthService, SessionManager sessionManager) {
        this.authService = decoratedAuthService;
        this.sessionManager = sessionManager;
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<Long>> register(@RequestBody RegisterRequest request) {
        // Factory pattern burada da kullanilabilir ama basitce switch-case yapiyoruz
        // entity olustururken
        // Auto-detect role for Demo purposes
        Role role = request.getRole();
        if (role == null) {
            if (request.getEmail().startsWith("admin")) {
                role = Role.YONETICI;
            } else {
                role = Role.YOLCU;
            }
        }

        Kullanici kullanici;
        if (role == Role.YOLCU) {
            Yolcu yolcu = new Yolcu();
            yolcu.setTelefon(request.getTelefon());
            kullanici = yolcu;
        } else {
            kullanici = new Yonetici();
        }

        kullanici.setEmail(request.getEmail());
        kullanici.setSifre(request.getSifre());
        kullanici.setAd(request.getAd());
        kullanici.setSoyad(request.getSoyad());
        kullanici.setRole(role);

        Kullanici created = authService.register(kullanici);
        return ResponseEntity.ok(ApiResponse.ok("Kayıt başarılı", created.getId()));
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<LoginResponse>> login(@RequestBody LoginRequest request) {
        String token = authService.login(request.getEmail(), request.getPassword());

        // Retrieve User Details (Ideally authService should return the user or a DTO)
        // For strict layered arch, we should add a method getUser(email) or make login
        // return DTO
        // Here we'll do a quick lookup via AuthService or Repository if accessible.
        // But AuthService doesn't expose User.
        // Let's modify AuthService to return user details or fetch it here using a
        // trick?
        // No, better to extend AuthService.

        // Singleton SessionManager'a kayit
        sessionManager.addSession(token, request.getEmail());

        // REFACTOR START: Get user by email to populate DTO
        // We need a way to get the user. Assuming we can inject UserRepository here or
        // use AuthService
        // Since we are refactoring, let's inject UserRepository directly for now or add
        // "getUserByEmail" to service.
        // Let's assume we can add a method to AuthService.
        Kullanici user = authService.findByEmail(request.getEmail());

        LoginResponse response = LoginResponse.builder()
                .token(token)
                .id(user.getId())
                .ad(user.getAd())
                .soyad(user.getSoyad())
                .email(user.getEmail())
                .role(user.getRole())
                .telefon(user instanceof Yolcu ? ((Yolcu) user).getTelefon() : null)
                .build();

        return ResponseEntity.ok(ApiResponse.ok("Giriş başarılı", response));
    }
}
