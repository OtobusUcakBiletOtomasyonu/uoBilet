package com.ticketapp.backend.controller;

import com.ticketapp.backend.domain.Koltuk;
import com.ticketapp.backend.dto.response.ApiResponse;
import com.ticketapp.backend.dto.response.KoltukDTO;
import com.ticketapp.backend.service.KoltukService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/koltuklar")
public class KoltukController {

    private final KoltukService koltukService;

    public KoltukController(KoltukService koltukService) {
        this.koltukService = koltukService;
    }

    @PostMapping("/{seferId}")
    public ResponseEntity<ApiResponse<String>> createKoltuklar(@PathVariable Long seferId) {
        // Otomatik 1-40 arasi koltuk olustur
        for (int i = 1; i <= 40; i++) {
            try {
                koltukService.createKoltuk(seferId, String.valueOf(i));
            } catch (Exception ignored) {
            } // Zaten varsa gec
        }
        return ResponseEntity.ok(ApiResponse.ok("Koltuklar oluşturuldu", "40 koltuk eklendi"));
    }

    @GetMapping("/sefer/{seferId}")
    public ResponseEntity<ApiResponse<List<KoltukDTO>>> getKoltuklarBySefer(@PathVariable Long seferId) {
        List<Koltuk> koltuklar = koltukService.getKoltuklarBySefer(seferId);
        List<KoltukDTO> dtos = koltuklar.stream().map(k -> {
            KoltukDTO dto = new KoltukDTO();
            dto.setId(k.getId());
            dto.setNumara(k.getNumara());
            dto.setDurum(k.getDurum());
            dto.setSeferId(k.getSefer().getId());
            return dto;
        }).collect(Collectors.toList());

        return ResponseEntity.ok(ApiResponse.ok(dtos));
    }
}
