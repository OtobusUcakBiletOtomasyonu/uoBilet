package com.ilgo.ticketapp.entity;

import java.util.ArrayList;

import java.util.List;

public class Firma {

    private int firmaId;

    private String firmaAdi;

    private List<Sefer> seferListesi = new ArrayList<>();

    public Firma() {

    }

    public Firma(int firmaId, String firmaAdi) {

        this.firmaId = firmaId;

        this.firmaAdi = firmaAdi;

    }

    public int getFirmaId() {

        return firmaId;

    }

    public void setFirmaId(int firmaId) {

        this.firmaId = firmaId;

    }

    public String getFirmaAdi() {

        return firmaAdi;

    }

    public void setFirmaAdi(String firmaAdi) {

        this.firmaAdi = firmaAdi;

    }

    public List<Sefer> getSeferListesi() {

        return seferListesi;

    }

    public void setSeferListesi(List<Sefer> seferListesi) {

        this.seferListesi = seferListesi;

    }

    public void seferEkle(Sefer sefer) {

        if (!seferListesi.contains(sefer)) {

            seferListesi.add(sefer);

        }

    }

}

