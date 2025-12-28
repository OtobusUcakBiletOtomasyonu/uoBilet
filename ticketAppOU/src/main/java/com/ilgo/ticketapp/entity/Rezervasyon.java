package com.ilgo.ticketapp.entity;

import java.util.Date;

public class Rezervasyon {

    private int rezervasyonId;

    private String durum;           // BEKLEMEDE / ONAYLI / IPTAL

    private Date tarih;

    private Yolcu yolcu;

    private Sefer sefer;

    private Koltuk koltuk;

    public Rezervasyon() {

    }

    public Rezervasyon(int rezervasyonId, String durum, Date tarih) {

        this.rezervasyonId = rezervasyonId;

        this.durum = durum;

        this.tarih = tarih;

    }

    public int getRezervasyonId() {

        return rezervasyonId;

    }

    public void setRezervasyonId(int rezervasyonId) {

        this.rezervasyonId = rezervasyonId;

    }

    public String getDurum() {

        return durum;

    }

    public void setDurum(String durum) {

        this.durum = durum;

    }

    public Date getTarih() {

        return tarih;

    }

    public void setTarih(Date tarih) {

        this.tarih = tarih;

    }

    public Yolcu getYolcu() {

        return yolcu;

    }

    public void setYolcu(Yolcu yolcu) {

        this.yolcu = yolcu;

    }

    public Sefer getSefer() {

        return sefer;

    }

    public void setSefer(Sefer sefer) {

        this.sefer = sefer;

    }

    public Koltuk getKoltuk() {

        return koltuk;

    }

    public void setKoltuk(Koltuk koltuk) {

        this.koltuk = koltuk;

    }

}

