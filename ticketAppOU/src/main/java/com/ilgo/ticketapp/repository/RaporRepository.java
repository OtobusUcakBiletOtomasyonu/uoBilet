package com.ilgo.ticketapp.repository;

import com.ilgo.ticketapp.entity.Rapor;

import java.util.ArrayList;

import java.util.List;

public class RaporRepository {

    private List<Rapor> raporlar = new ArrayList<>();

    public void save(Rapor r) {

        raporlar.add(r);

    }

    public Rapor findById(int id) {

        for (Rapor r : raporlar) {

            if (r.getRaporId() == id) {

                return r;

            }

        }

        return null;

    }

    public List<Rapor> findAll() {

        return raporlar;

    }

    public void delete(int id) {

        raporlar.removeIf(r -> r.getRaporId() == id);

    }

}

