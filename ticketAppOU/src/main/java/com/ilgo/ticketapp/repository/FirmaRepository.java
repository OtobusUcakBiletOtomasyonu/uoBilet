package com.ilgo.ticketapp.repository;

import com.ilgo.ticketapp.entity.Firma;

import java.util.ArrayList;

import java.util.List;

public class FirmaRepository {

    private List<Firma> firmalar = new ArrayList<>();

    public void save(Firma firma) {

        firmalar.add(firma);

    }

    public Firma findById(int id) {

        for (Firma f : firmalar) {

            if (f.getFirmaId() == id) {

                return f;

            }

        }

        return null;

    }

    public List<Firma> findAll() {

        return firmalar;

    }

    public void delete(int id) {

        firmalar.removeIf(f -> f.getFirmaId() == id);

    }

}

