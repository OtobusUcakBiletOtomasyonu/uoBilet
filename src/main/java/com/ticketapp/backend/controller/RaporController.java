package com.ticketapp.backend.controller;

import com.ticketapp.backend.dto.response.ApiResponse;
import com.ticketapp.backend.dto.response.RaporDTO;
import com.ticketapp.backend.service.RaporService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/rapor")
public class RaporController {

    private final RaporService raporService;

    public RaporController(RaporService raporService) {
        this.raporService = raporService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<RaporDTO>> getSatisRaporu(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime baslangic,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime bitis) {

        return ResponseEntity.ok(ApiResponse.ok(raporService.alSatisRaporu(baslangic, bitis)));
    }
}
