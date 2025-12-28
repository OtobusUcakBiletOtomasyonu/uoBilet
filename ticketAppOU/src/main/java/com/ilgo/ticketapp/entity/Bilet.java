package com.ilgo.ticketapp.entity;

import java.util.Date;

public class Bilet {

    private String id;

    private Yolcu yolcu;

    private Sefer sefer;

    private Koltuk koltuk;

    private double fiyat;

    private String durum; // AKTIF / IPTAL

    private String tarih;

    public Bilet() {

    }

    public Bilet(String id, Yolcu yolcu, Sefer sefer,

            Koltuk koltuk, double fiyat, String durum, String tarih) {

        this.id = id;

        this.yolcu = yolcu;

        this.sefer = sefer;

        this.koltuk = koltuk;

        this.fiyat = fiyat;

        this.durum = durum;

        this.tarih = tarih;

    }

    public String getId() {

        return id;

    }

    public void setId(String id) {

        this.id = id;

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

    public double getFiyat() {

        return fiyat;

    }

    public void setFiyat(double fiyat) {

        this.fiyat = fiyat;

    }

    public String getDurum() {

        return durum;

    }

    public void setDurum(String durum) {

        this.durum = durum;

    }

    public String getTarih() {

        return tarih;

    }

    public void setTarih(String tarih) {

        this.tarih = tarih;

    }

    // Compatibility method if needed
    public int getBiletId() {
        return 0;
    } // Deprecated or removed ideally

}
