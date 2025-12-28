package com.ilgo.ticketapp.repository;

import com.ilgo.ticketapp.entity.Rezervasyon;

import java.util.ArrayList;

import java.util.List;

public class RezervasyonRepository {

    private List<Rezervasyon> rezervasyonlar = new ArrayList<>();

    public void save(Rezervasyon r) {

        rezervasyonlar.add(r);

    }

    public Rezervasyon findById(int id) {

        for (Rezervasyon r : rezervasyonlar) {

            if (r.getRezervasyonId() == id) {

                return r;

            }

        }

        return null;

    }

    public List<Rezervasyon> findAll() {

        return rezervasyonlar;

    }

    public void delete(int id) {

        rezervasyonlar.removeIf(r -> r.getRezervasyonId() == id);

    }

}

