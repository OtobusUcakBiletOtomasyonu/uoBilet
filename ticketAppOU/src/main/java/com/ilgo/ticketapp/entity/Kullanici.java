package com.ilgo.ticketapp.entity;

public abstract class Kullanici {

    private String id;

    private String adSoyad;

    private String email;

    private String sifre;

    public Kullanici() {

    }

    public Kullanici(String id, String adSoyad, String email, String sifre) {

        this.id = id;

        this.adSoyad = adSoyad;

        this.email = email;

        this.sifre = sifre;

    }

    public String getId() {

        return id;

    }

    public void setId(String id) {

        this.id = id;

    }

    public String getAdSoyad() {

        return adSoyad;

    }

    public void setAdSoyad(String adSoyad) {

        this.adSoyad = adSoyad;

    }

    public String getEmail() {

        return email;

    }

    public void setEmail(String email) {

        this.email = email;

    }

    public String getSifre() {

        return sifre;

    }

    public void setSifre(String sifre) {

        this.sifre = sifre;

    }

}
