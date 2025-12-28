package com.ilgo.ticketapp.service.impl;

import com.ilgo.ticketapp.entity.Firma;

import com.ilgo.ticketapp.repository.FirmaRepository;

import com.ilgo.ticketapp.service.FirmaService;

import java.util.List;

public class FirmaServiceImpl implements FirmaService {

    private FirmaRepository firmaRepository;

    public FirmaServiceImpl(FirmaRepository repo) {

        this.firmaRepository = repo;

    }

    @Override

    public Firma firmaEkle(String ad) {

        int id = firmaRepository.findAll().size() + 1;

        Firma f = new Firma(id, ad);

        firmaRepository.save(f);

        System.out.println("[FİRMA] Yeni firma: " + ad);

        return f;

    }

    @Override

    public Firma firmaGetir(int id) {

        return firmaRepository.findById(id);

    }

    @Override

    public List<Firma> tumFirmalar() {

        return firmaRepository.findAll();

    }

}

