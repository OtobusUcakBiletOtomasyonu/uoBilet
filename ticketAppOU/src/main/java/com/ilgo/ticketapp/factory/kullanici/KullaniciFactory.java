package com.ilgo.ticketapp.factory.kullanici;

import com.ilgo.ticketapp.entity.Kullanici;

public abstract class KullaniciFactory {

    public Kullanici createKullanici(String id,

            String adSoyad,

            String email,

            String sifre) {

        if (adSoyad == null || adSoyad.isEmpty())

            throw new RuntimeException("Ad soyad boş olamaz!");

        if (email == null || email.isEmpty())

            throw new RuntimeException("Email boş olamaz!");

        return buildKullanici(id, adSoyad, email, sifre);

    }

    protected abstract Kullanici buildKullanici(String id,

            String adSoyad,

            String email,

            String sifre);

}
