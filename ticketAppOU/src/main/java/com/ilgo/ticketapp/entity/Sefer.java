package com.ilgo.ticketapp.entity;

import java.util.ArrayList;

import java.util.Date;

import java.util.List;

public class Sefer {

    private String id;

    private String kalkisYeri;

    private String varisYeri;

    private String tarih;

    private String saat;

    private double fiyat;

    private List<Koltuk> koltukListesi = new ArrayList<>();

    public Sefer() {

    }

    public Sefer(String id, String kalkisYeri, String varisYeri,

            String tarih, String saat, double fiyat) {

        this.id = id;

        this.kalkisYeri = kalkisYeri;

        this.varisYeri = varisYeri;

        this.tarih = tarih;

        this.saat = saat;

        this.fiyat = fiyat;

    }

    public String getId() {

        return id;

    }

    public void setId(String id) {

        this.id = id;

    }

    public String getKalkisYeri() {

        return kalkisYeri;

    }

    public void setKalkisYeri(String kalkisYeri) {

        this.kalkisYeri = kalkisYeri;

    }

    public String getVarisYeri() {

        return varisYeri;

    }

    public void setVarisYeri(String varisYeri) {

        this.varisYeri = varisYeri;

    }

    public String getTarih() {

        return tarih;

    }

    public void setTarih(String tarih) {

        this.tarih = tarih;

    }

    public String getSaat() {

        return saat;

    }

    public void setSaat(String saat) {

        this.saat = saat;

    }

    public double getFiyat() {

        return fiyat;

    }

    public void setFiyat(double fiyat) {

        this.fiyat = fiyat;

    }

    public List<Koltuk> getKoltukListesi() {

        return koltukListesi;

    }

    public void setKoltukListesi(List<Koltuk> koltukListesi) {

        this.koltukListesi = koltukListesi;

    }

    // Helper methods for user's code compatibility if needed
    public String getKalkis() {
        return kalkisYeri;
    }

    public String getVaris() {
        return varisYeri;
    }
}
