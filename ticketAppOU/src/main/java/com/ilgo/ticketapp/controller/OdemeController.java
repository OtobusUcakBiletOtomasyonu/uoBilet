package com.ilgo.ticketapp.controller;

import com.ilgo.ticketapp.entity.Odeme;
import com.ilgo.ticketapp.service.OdemeService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/odeme")
public class OdemeController {

    private final OdemeService service;

    public OdemeController(OdemeService service) {
        this.service = service;
    }

    @PostMapping("/yap")
    public Odeme odemeYap(@RequestParam String biletId,
            @RequestParam double tutar,
            @RequestParam String tur) {

        return service.odemeYap(biletId, tutar, tur);
    }

    @GetMapping("/{id}")
    public Odeme getir(@PathVariable String id) {
        return service.getir(id);
    }
}
