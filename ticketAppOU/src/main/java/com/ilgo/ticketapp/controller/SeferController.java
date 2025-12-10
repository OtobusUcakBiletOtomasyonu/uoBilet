package com.ilgo.ticketapp.controller;

import com.ilgo.ticketapp.entity.Sefer;
import com.ilgo.ticketapp.service.SeferService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sefer")
public class SeferController {

    private final SeferService service;

    public SeferController(SeferService service) {
        this.service = service;
    }

    @PostMapping("/ekle")
    public String ekle(@RequestBody Sefer sefer) {
        service.kaydet(sefer);
        return "Sefer başarıyla eklendi.";
    }

    @GetMapping("/{id}")
    public Sefer getir(@PathVariable String id) {
        return service.getir(id);
    }

    @GetMapping("/liste")
    public List<Sefer> liste() {
        return service.listele();
    }

    @DeleteMapping("/sil/{id}")
    public String sil(@PathVariable String id) {
        service.sil(id);
        return "Sefer silindi.";
    }

    @GetMapping("/ara")
    public List<Sefer> ara(@RequestParam String kalkis,
            @RequestParam String varis,
            @RequestParam String tarih) {
        return service.ara(kalkis, varis, tarih);
    }
}
