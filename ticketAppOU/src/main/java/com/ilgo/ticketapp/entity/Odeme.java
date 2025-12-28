package com.ilgo.ticketapp.entity;

public class Odeme {

    private String id;
    private String biletId;
    private double tutar;
    private String tarih;
    private String odemeTuru;
    private String sonuc;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBiletId() {
        return biletId;
    }

    public void setBiletId(String biletId) {
        this.biletId = biletId;
    }

    public double getTutar() {
        return tutar;
    }

    public void setTutar(double tutar) {
        this.tutar = tutar;
    }

    public String getTarih() {
        return tarih;
    }

    public void setTarih(String tarih) {
        this.tarih = tarih;
    }

    public String getOdemeTuru() {
        return odemeTuru;
    }

    public void setOdemeTuru(String odemeTuru) {
        this.odemeTuru = odemeTuru;
    }

    public String getSonuc() {
        return sonuc;
    }

    public void setSonuc(String sonuc) {
        this.sonuc = sonuc;
    }
}
