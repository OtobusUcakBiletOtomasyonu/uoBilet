package com.ilgo.ticketapp.controller;

import com.ilgo.ticketapp.entity.Kullanici;

import com.ilgo.ticketapp.service.KullaniciService;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller

@RequestMapping("/yonetici")

public class YoneticiController {

    private KullaniciService kullaniciService;

    public YoneticiController(KullaniciService service) {

        this.kullaniciService = service;

    }

    @GetMapping("/panel")

    public String panel() {

        return "admin-panel"; // admin-panel.html

    }

    @GetMapping("/kullanicilar")

    @ResponseBody

    public List<Kullanici> tumKullanicilar() {

        return kullaniciService.tumKullanicilariGetir();

    }

}

