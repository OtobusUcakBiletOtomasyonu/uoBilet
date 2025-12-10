package com.ilgo.ticketapp.entity;

public class Koltuk {

    private String id;

    private String seferId;

    private int koltukNo;

    private String tip;

    private String durum; // BOS / DOLU / REZERVE vs.

    public Koltuk() {

    }

    public Koltuk(String id, String seferId, int koltukNo, String durum) {

        this.id = id;

        this.seferId = seferId;

        this.koltukNo = koltukNo;

        this.tip = "NORMAL";

        this.durum = durum;

    }

    public Koltuk(String id, String seferId, int koltukNo, String tip, String durum) {

        this.id = id;

        this.seferId = seferId;

        this.koltukNo = koltukNo;

        this.tip = tip;

        this.durum = durum;

    }

    public String getId() {

        return id;

    }

    public void setId(String id) {

        this.id = id;

    }

    public String getSeferId() {

        return seferId;

    }

    public void setSeferId(String seferId) {

        this.seferId = seferId;

    }

    public int getKoltukNo() {

        return koltukNo;

    }

    public void setKoltukNo(int koltukNo) {

        this.koltukNo = koltukNo;

    }

    public String getTip() {

        return tip;

    }

    public void setTip(String tip) {

        this.tip = tip;

    }

    public String getDurum() {

        return durum;

    }

    public void setDurum(String durum) {

        this.durum = durum;

    }

}
