package com.ilgo.ticketapp.controller;

import com.ilgo.ticketapp.entity.Koltuk;
import com.ilgo.ticketapp.service.KoltukService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/koltuk")
public class KoltukController {

    private final KoltukService service;

    public KoltukController(KoltukService service) {
        this.service = service;
    }

    @PostMapping("/ekle")
    public String ekle(@RequestBody Koltuk koltuk) {
        service.ekle(koltuk);
        return "Koltuk eklendi.";
    }

    @GetMapping("/{id}")
    public Koltuk getir(@PathVariable String id) {
        return service.getir(id);
    }

    @GetMapping("/sefer/{seferId}")
    public List<Koltuk> seferKoltuklari(@PathVariable String seferId) {
        return service.seferKoltuklariniGetir(seferId);
    }

    @PostMapping("/durum/{id}")
    public String durumDegistir(@PathVariable String id, @RequestParam String durum) {
        service.durumDegistir(id, durum);
        return "Koltuk durumu güncellendi.";
    }

    @DeleteMapping("/sil/{id}")
    public String sil(@PathVariable String id) {
        service.sil(id);
        return "Koltuk silindi.";
    }
}
