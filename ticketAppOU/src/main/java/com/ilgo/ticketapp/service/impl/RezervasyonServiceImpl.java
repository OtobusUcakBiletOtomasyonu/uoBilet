package com.ilgo.ticketapp.service.impl;

import com.ilgo.ticketapp.entity.Rezervasyon;

import com.ilgo.ticketapp.repository.RezervasyonRepository;

import com.ilgo.ticketapp.service.RezervasyonService;

import java.util.Date;

import java.util.List;

public class RezervasyonServiceImpl implements RezervasyonService {

    private RezervasyonRepository rezervasyonRepository;

    public RezervasyonServiceImpl(RezervasyonRepository repo) {

        this.rezervasyonRepository = repo;

    }

    @Override

    public Rezervasyon rezervasyonOlustur(String durum) {

        int id = rezervasyonRepository.findAll().size() + 1;

        Rezervasyon r = new Rezervasyon(id, durum, new Date());

        rezervasyonRepository.save(r);

        System.out.println("[REZERVASYON] Yeni rezervasyon: " + id);

        return r;

    }

    @Override

    public Rezervasyon rezervasyonGetir(int id) {

        return rezervasyonRepository.findById(id);

    }

    @Override

    public void rezervasyonIptal(int id) {

        Rezervasyon r = rezervasyonRepository.findById(id);

        if (r != null) {

            r.setDurum("IPTAL");

        }

    }

    @Override

    public List<Rezervasyon> tumRezervasyonlar() {

        return rezervasyonRepository.findAll();

    }

}

