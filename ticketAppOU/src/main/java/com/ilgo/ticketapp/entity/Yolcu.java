package com.ilgo.ticketapp.entity;

import java.util.ArrayList;

import java.util.List;

public class Yolcu extends Kullanici {

    private double bakiye;

    private List<Firma> favoriFirmalar = new ArrayList<>();

    public Yolcu() {

        super();

    }

    public Yolcu(String id, String adSoyad, String email, String sifre,

            double bakiye) {

        super(id, adSoyad, email, sifre);

        this.bakiye = bakiye;

    }

    public double getBakiye() {

        return bakiye;

    }

    public void setBakiye(double bakiye) {

        this.bakiye = bakiye;

    }

    public List<Firma> getFavoriFirmalar() {

        return favoriFirmalar;

    }

    public void setFavoriFirmalar(List<Firma> favoriFirmalar) {

        this.favoriFirmalar = favoriFirmalar;

    }

    public void favoriFirmaEkle(Firma firma) {

        if (!favoriFirmalar.contains(firma)) {

            favoriFirmalar.add(firma);

        }

    }

}
