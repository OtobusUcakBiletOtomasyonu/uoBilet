package com.ilgo.ticketapp.entity;

import java.util.Date;

public class Rapor {

    private int raporId;

    private Date tarih;

    private String icerik;

    public Rapor() {

    }

    public Rapor(int raporId, Date tarih, String icerik) {

        this.raporId = raporId;

        this.tarih = tarih;

        this.icerik = icerik;

    }

    public int getRaporId() {

        return raporId;

    }

    public void setRaporId(int raporId) {

        this.raporId = raporId;

    }

    public Date getTarih() {

        return tarih;

    }

    public void setTarih(Date tarih) {

        this.tarih = tarih;

    }

    public String getIcerik() {

        return icerik;

    }

    public void setIcerik(String icerik) {

        this.icerik = icerik;

    }

}

