package com.ticketapp.backend.controller;

import com.ticketapp.backend.domain.Bilet;
import com.ticketapp.backend.dto.request.BiletSatinAlRequest;
import com.ticketapp.backend.dto.response.ApiResponse;
import com.ticketapp.backend.service.BiletService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/biletler")
public class BiletController {

    private final BiletService biletService;

    public BiletController(BiletService biletService) {
        this.biletService = biletService;
    }

    @PostMapping("/satin-al")
    public ResponseEntity<ApiResponse<Long>> satinAl(@RequestBody BiletSatinAlRequest request) {
        System.out.println("Satin Al Request: YolcuID=" + request.getYolcuId() + ", KoltukID=" + request.getKoltukId());
        Bilet bilet = biletService.satinAl(request.getYolcuId(), request.getKoltukId(),
                request.getOdemeStrategy(), request.getTutar());
        System.out.println("Bilet Olusturuldu ID: " + bilet.getId());
        return ResponseEntity.ok(ApiResponse.ok("Bilet başarıyla alındı", bilet.getId()));
    }

    @PostMapping("/{id}/iptal")
    public ResponseEntity<ApiResponse<String>> iptalEt(@PathVariable Long id) {
        biletService.iptalEt(id);
        return ResponseEntity.ok(ApiResponse.ok("Bilet iptal edildi", "OK"));
    }

    @GetMapping("/yolcu/{yolcuId}")
    public ResponseEntity<ApiResponse<java.util.List<Bilet>>> getBiletlerByYolcu(@PathVariable Long yolcuId) {
        System.out.println("Biletleri Getir YolcuID: " + yolcuId);
        java.util.List<Bilet> biletler = biletService.getBiletlerByYolcu(yolcuId);
        System.out.println("Bulunan Bilet Sayisi: " + biletler.size());
        biletler.forEach(b -> System.out.println(
                "Bilet: " + b.getId() + ", Koltuk: " + (b.getKoltuk() != null ? b.getKoltuk().getNumara() : "null")));
        return ResponseEntity.ok(ApiResponse.ok(biletler));
    }
}
