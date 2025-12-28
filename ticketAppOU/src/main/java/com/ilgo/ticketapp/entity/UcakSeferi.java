package com.ilgo.ticketapp.entity;

import java.util.Date;

public class UcakSeferi extends Sefer {

    private String ucakModeli;

    public UcakSeferi() {

        super();

    }

    public UcakSeferi(String id, String kalkisYeri, String varisYeri,

            String tarih, String saat, double fiyat,

            String ucakModeli) {

        super(id, kalkisYeri, varisYeri, tarih, saat, fiyat);

        this.ucakModeli = ucakModeli;

    }

    public String getUcakModeli() {

        return ucakModeli;

    }

    public void setUcakModeli(String ucakModeli) {

        this.ucakModeli = ucakModeli;

    }

}
