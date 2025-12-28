package com.ilgo.ticketapp.service;

import com.ilgo.ticketapp.entity.Kullanici;

import java.util.List;

public interface KullaniciService {

    Kullanici girisYap(String email, String sifre);

    void cikisYap(String kullaniciId);

    void sifreDegistir(String kullaniciId, String eskiSifre, String yeniSifre);

    Kullanici kullaniciGetir(String id);

    List<Kullanici> tumKullanicilariGetir();

    void kullaniciKaydet(Kullanici kullanici);

}
