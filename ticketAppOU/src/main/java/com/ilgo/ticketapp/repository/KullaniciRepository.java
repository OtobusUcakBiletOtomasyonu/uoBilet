package com.ilgo.ticketapp.repository;

import com.ilgo.ticketapp.entity.Kullanici;

import java.util.ArrayList;

import java.util.List;

public class KullaniciRepository {

    private List<Kullanici> kullanicilar = new ArrayList<>();

    public void save(Kullanici kullanici) {

        kullanicilar.add(kullanici);

    }

    public Kullanici findById(String id) {

        for (Kullanici k : kullanicilar) {

            if (k.getId().equals(id)) {

                return k;

            }

        }

        return null;

    }

    public List<Kullanici> findAll() {

        return kullanicilar;

    }

    public void delete(String id) {

        kullanicilar.removeIf(k -> k.getId().equals(id));

    }

}
