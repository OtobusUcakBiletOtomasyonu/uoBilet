package com.ilgo.ticketapp.controller;

import com.ilgo.ticketapp.entity.Yolcu;
import com.ilgo.ticketapp.service.YolcuService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Yolcu ile ilgili HTTP isteklerini karşılayan katman.
 */
@RestController
@RequestMapping("/yolcu")
public class YolcuController {

    private final YolcuService service;

    public YolcuController(YolcuService service) {
        this.service = service;
    }

    @PostMapping("/ekle")
    public String ekle(@RequestBody Yolcu yolcu) {
        service.kaydet(yolcu);
        return "Yolcu başarıyla eklendi.";
    }

    @GetMapping("/{id}")
    public Yolcu getir(@PathVariable String id) {
        return service.getir(id);
    }

    @GetMapping("/liste")
    public List<Yolcu> listele() {
        return service.listele();
    }

    @DeleteMapping("/sil/{id}")
    public String sil(@PathVariable String id) {
        service.sil(id);
        return "Yolcu silindi.";
    }
}
