package com.ilgo.ticketapp.factory.kullanici;

import com.ilgo.ticketapp.entity.Yonetici;

public class YoneticiFactory extends KullaniciFactory {

    @Override

    protected Yonetici buildKullanici(String id,

            String adSoyad,

            String email,

            String sifre) {

        return new Yonetici(id, adSoyad, email, sifre);

    }

}
