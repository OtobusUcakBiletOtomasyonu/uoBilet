package com.ilgo.ticketapp.service.impl;

import com.ilgo.ticketapp.entity.Rapor;

import com.ilgo.ticketapp.repository.RaporRepository;

import com.ilgo.ticketapp.service.RaporService;

import java.util.Date;

import java.util.List;

public class RaporServiceImpl implements RaporService {

    private RaporRepository raporRepository;

    public RaporServiceImpl(RaporRepository repo) {

        this.raporRepository = repo;

    }

    @Override

    public Rapor raporOlustur(String icerik) {

        int id = raporRepository.findAll().size() + 1;

        Rapor r = new Rapor(id, new Date(), icerik);

        raporRepository.save(r);

        return r;

    }

    @Override

    public List<Rapor> tumRaporlar() {

        return raporRepository.findAll();

    }

    @Override

    public Rapor raporGetir(int id) {

        return raporRepository.findById(id);

    }

}

