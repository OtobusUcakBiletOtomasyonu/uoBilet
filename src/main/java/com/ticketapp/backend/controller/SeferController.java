package com.ticketapp.backend.controller;

import com.ticketapp.backend.domain.Sefer;
import com.ticketapp.backend.dto.request.SeferRequest;
import com.ticketapp.backend.dto.response.ApiResponse;
import com.ticketapp.backend.dto.response.SeferDTO;
import com.ticketapp.backend.service.SeferService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/seferler")
public class SeferController {

    private final SeferService seferService;

    public SeferController(SeferService seferService) {
        this.seferService = seferService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Long>> createSefer(@RequestBody SeferRequest request) {
        Sefer sefer = seferService.createSefer(request.getTip(), request.getKalkisYeri(),
                request.getVarisYeri(), request.getKalkisZamani(),
                request.getFiyat(), request.getKoltukDuzeni(), request.getFirmaId());
        return ResponseEntity.ok(ApiResponse.ok("Sefer oluşturuldu", sefer.getId()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteSefer(@PathVariable Long id) {
        seferService.deleteSefer(id);
        return ResponseEntity.ok(ApiResponse.ok("Sefer silindi", null));
    }

    @GetMapping("/search")
    public ResponseEntity<ApiResponse<List<SeferDTO>>> search(
            @RequestParam String kalkis,
            @RequestParam String varis,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime zaman) {

        List<Sefer> seferler = seferService.searchSefer(kalkis, varis, zaman);
        List<SeferDTO> dtos = seferler.stream().map(this::mapToDTO).collect(Collectors.toList());

        return ResponseEntity.ok(ApiResponse.ok(dtos));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<SeferDTO>>> getAll() {
        List<SeferDTO> dtos = seferService.getAllSeferler().stream().map(this::mapToDTO).collect(Collectors.toList());
        return ResponseEntity.ok(ApiResponse.ok(dtos));
    }

    private SeferDTO mapToDTO(Sefer sefer) {
        SeferDTO dto = new SeferDTO();
        dto.setId(sefer.getId());
        dto.setKalkisYeri(sefer.getKalkisYeri());
        dto.setVarisYeri(sefer.getVarisYeri());
        dto.setKalkisZamani(sefer.getKalkisZamani());
        dto.setFiyat(sefer.getFiyat());
        dto.setTip(sefer.getTip());
        dto.setFirmaAdi(sefer.getFirma() != null ? sefer.getFirma().getAd() : "Bilinmiyor");
        if (sefer instanceof com.ticketapp.backend.domain.OtobusSeferi) {
            dto.setKoltukDuzeni(((com.ticketapp.backend.domain.OtobusSeferi) sefer).getKoltukDuzeni());
        }
        return dto;
    }

    @GetMapping("/{id}/koltuklar")
    public ResponseEntity<ApiResponse<List<com.ticketapp.backend.dto.response.KoltukDurumDTO>>> getKoltuklar(
            @PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.ok(seferService.getKoltukDurumlari(id)));
    }
}
