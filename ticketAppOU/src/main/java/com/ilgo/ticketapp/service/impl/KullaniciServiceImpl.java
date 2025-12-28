package com.ilgo.ticketapp.service.impl;

import com.ilgo.ticketapp.entity.Kullanici;

import com.ilgo.ticketapp.repository.KullaniciRepository;

import com.ilgo.ticketapp.service.KullaniciService;

import java.util.List;

public class KullaniciServiceImpl implements KullaniciService {

    private KullaniciRepository kullaniciRepository;

    public KullaniciServiceImpl(KullaniciRepository repo) {

        this.kullaniciRepository = repo;

    }

    @Override

    public Kullanici girisYap(String email, String sifre) {

        for (Kullanici k : kullaniciRepository.findAll()) {

            if (k.getEmail().equalsIgnoreCase(email) && k.getSifre().equals(sifre)) {

                System.out.println("[LOGIN] Başarılı giriş: " + k.getAdSoyad());

                return k;

            }

        }

        System.out.println("[LOGIN] Hatalı email veya şifre!");

        return null;

    }

    @Override

    public void cikisYap(String kullaniciId) {

        Kullanici k = kullaniciRepository.findById(kullaniciId);

        if (k != null) {

            System.out.println("[LOGOUT] Çıkış yapıldı: " + k.getAdSoyad());

        }

    }

    @Override

    public void sifreDegistir(String kullaniciId, String eskiSifre, String yeniSifre) {

        Kullanici k = kullaniciRepository.findById(kullaniciId);

        if (k == null) {

            System.out.println("[ŞİFRE] Kullanıcı bulunamadı.");

            return;

        }

        if (!k.getSifre().equals(eskiSifre)) {

            System.out.println("[ŞİFRE] Eski şifre yanlış.");

            return;

        }

        k.setSifre(yeniSifre);

        System.out.println("[ŞİFRE] Şifre değiştirildi: " + k.getAdSoyad());

    }

    @Override

    public Kullanici kullaniciGetir(String id) {

        return kullaniciRepository.findById(id);

    }

    @Override

    public List<Kullanici> tumKullanicilariGetir() {

        return kullaniciRepository.findAll();

    }

    @Override

    public void kullaniciKaydet(Kullanici kullanici) {

        kullaniciRepository.save(kullanici);

    }

}
