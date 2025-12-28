package com.ilgo.ticketapp.controller;

import com.ilgo.ticketapp.entity.Bilet;
import com.ilgo.ticketapp.service.BiletService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bilet")
public class BiletController {

    private final BiletService service;

    public BiletController(BiletService service) {
        this.service = service;
    }

    @PostMapping("/al")
    public Bilet al(@RequestParam String yolcuId,
            @RequestParam String seferId,
            @RequestParam String koltukId,
            @RequestParam(defaultValue = "false") boolean vip,
            @RequestParam(defaultValue = "false") boolean sigorta,
            @RequestParam(defaultValue = "false") boolean ikram,
            @RequestParam(defaultValue = "false") boolean bagaj,
            @RequestParam(defaultValue = "false") boolean hizliCheckIn) {

        return service.biletAl(yolcuId, seferId, koltukId,
                vip, sigorta, ikram, bagaj, hizliCheckIn);
    }

    @DeleteMapping("/iptal/{id}")
    public String iptal(@PathVariable String id) {
        service.biletIptal(id);
        return "Bilet iptal edildi.";
    }

    @GetMapping("/{id}")
    public Bilet getir(@PathVariable String id) {
        return service.getir(id);
    }

    @GetMapping("/liste")
    public List<Bilet> liste() {
        return service.listele();
    }
}
