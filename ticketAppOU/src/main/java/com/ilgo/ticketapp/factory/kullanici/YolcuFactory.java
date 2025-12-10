package com.ilgo.ticketapp.factory.kullanici;

import com.ilgo.ticketapp.entity.Yolcu;

public class YolcuFactory extends KullaniciFactory {

    @Override

    protected Yolcu buildKullanici(String id,

            String adSoyad,

            String email,

            String sifre) {

        return new Yolcu(id, adSoyad, email, sifre, 0.0);

    }

}
