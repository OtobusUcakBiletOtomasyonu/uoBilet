package com.ticketapp.backend.controller;

import com.ticketapp.backend.domain.Firma;
import com.ticketapp.backend.dto.response.ApiResponse;
import com.ticketapp.backend.service.FirmaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/firmalar")
public class FirmaController {

    private final FirmaService firmaService;

    public FirmaController(FirmaService firmaService) {
        this.firmaService = firmaService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Firma>> createFirma(@RequestParam String ad) {
        return ResponseEntity.ok(ApiResponse.ok(firmaService.createFirma(ad)));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Firma>>> getAll() {
        return ResponseEntity.ok(ApiResponse.ok(firmaService.getAllFirmalar()));
    }
}
