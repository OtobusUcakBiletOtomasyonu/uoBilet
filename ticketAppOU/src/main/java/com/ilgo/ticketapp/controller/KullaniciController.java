package com.ilgo.ticketapp.controller;

import com.ilgo.ticketapp.entity.Kullanici;

import com.ilgo.ticketapp.service.KullaniciService;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller

@RequestMapping("/kullanici")

public class KullaniciController {

    private KullaniciService kullaniciService;

    public KullaniciController(KullaniciService service) {

        this.kullaniciService = service;

    }

    @GetMapping("/giris")

    public String girisSayfasi() {

        return "login"; // login.html

    }

    @PostMapping("/giris")

    @ResponseBody

    public String girisYap(@RequestParam String email,

            @RequestParam String sifre) {

        Kullanici k = kullaniciService.girisYap(email, sifre);

        if (k == null) {

            return "Hatalı giriş!";

        }

        return "Hoşgeldiniz: " + k.getAdSoyad();

    }

    @PostMapping("/sifre-degistir")

    @ResponseBody

    public String sifreDegistir(@RequestParam String id,

            @RequestParam String eskiSifre,

            @RequestParam String yeniSifre) {

        kullaniciService.sifreDegistir(id, eskiSifre, yeniSifre);

        return "Şifre değiştirme işlemi tamamlandı.";

    }

    @GetMapping("/liste")

    @ResponseBody

    public List<Kullanici> tumKullanicilar() {

        return kullaniciService.tumKullanicilariGetir();

    }

}
