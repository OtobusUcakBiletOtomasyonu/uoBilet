package com.ilgo.ticketapp.controller;

import com.ilgo.ticketapp.entity.Firma;

import com.ilgo.ticketapp.service.FirmaService;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller

@RequestMapping("/firma")

public class FirmaController {

    private FirmaService firmaService;

    public FirmaController(FirmaService service) {

        this.firmaService = service;

    }

    @PostMapping("/ekle")

    @ResponseBody

    public String ekle(@RequestParam String ad) {

        firmaService.firmaEkle(ad);

        return "Firma eklendi.";

    }

    @GetMapping("/liste")

    @ResponseBody

    public List<Firma> liste() {

        return firmaService.tumFirmalar();

    }

}

