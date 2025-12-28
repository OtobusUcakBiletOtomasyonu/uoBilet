package com.ilgo.ticketapp.factory.sefer;

import com.ilgo.ticketapp.entity.Sefer;

import java.util.Date;

/**
 * 
 * Sefer üretmek için kullanılan soyut fabrika sınıfı.
 * 
 * Alt sınıflar sadece sefer tipini belirler (uçak, otobüs).
 * 
 */

public abstract class SeferFactory {

    public Sefer createSefer(String id,

            String kalkis,

            String varis,

            String tarih,

            String saat,

            double fiyat) {

        // Üretimden önce temel kontroller

        if (kalkis == null || kalkis.isEmpty())

            throw new RuntimeException("Kalkış yeri boş olamaz!");

        if (varis == null || varis.isEmpty())

            throw new RuntimeException("Varış yeri boş olamaz!");

        if (fiyat <= 0)

            throw new RuntimeException("Fiyat 0 veya negatif olamaz!");

        // Asıl nesnenin oluşturulduğu nokta (factory pattern)

        Sefer sefer = buildSefer(id, kalkis, varis, tarih, saat, fiyat);

        System.out.println("[FACTORY] Yeni sefer oluşturuldu: " +

                sefer.getKalkisYeri() + " → " + sefer.getVarisYeri());

        return sefer;

    }

    /**
     * 
     * Alt sınıflar bu metodu override ederek kendi sefer tiplerini oluşturur.
     * 
     */

    protected abstract Sefer buildSefer(String id,

            String kalkis,

            String varis,

            String tarih,

            String saat,

            double fiyat);

}
