package com.ilgo.ticketapp.entity;

import java.util.Date;

public class OtobusSeferi extends Sefer {

    private boolean ikramDurumu;

    public OtobusSeferi() {

        super();

    }

    public OtobusSeferi(String id, String kalkisYeri, String varisYeri,

            String tarih, String saat, double fiyat,

            boolean ikramDurumu) {

        super(id, kalkisYeri, varisYeri, tarih, saat, fiyat);

        this.ikramDurumu = ikramDurumu;

    }

    public boolean isIkramDurumu() {

        return ikramDurumu;

    }

    public void setIkramDurumu(boolean ikramDurumu) {

        this.ikramDurumu = ikramDurumu;

    }

}
