package com.ilgo.ticketapp.builder;

import com.ilgo.ticketapp.entity.Bilet;
import com.ilgo.ticketapp.entity.Koltuk;
import com.ilgo.ticketapp.entity.Sefer;
import com.ilgo.ticketapp.entity.Yolcu;

import java.util.Date;

/**
 * Bilet nesnesinin daha düzenli ve kontrollü oluşturulmasını sağlayan builder
 * sınıfı.
 */
public class BiletBuilder {

    private String id;
    private Yolcu yolcu;
    private Sefer sefer;
    private Koltuk koltuk;
    private double fiyat;
    private String durum;
    private String tarih;

    public BiletBuilder setId(String id) {
        this.id = id;
        return this;
    }

    public BiletBuilder setYolcu(Yolcu yolcu) {
        this.yolcu = yolcu;
        return this;
    }

    public BiletBuilder setSefer(Sefer sefer) {
        this.sefer = sefer;
        return this;
    }

    public BiletBuilder setKoltuk(Koltuk koltuk) {
        this.koltuk = koltuk;
        return this;
    }

    public BiletBuilder setFiyat(double fiyat) {
        this.fiyat = fiyat;
        return this;
    }

    public BiletBuilder setDurum(String durum) {
        this.durum = durum;
        return this;
    }

    public BiletBuilder setTarih(String tarih) {
        this.tarih = tarih;
        return this;
    }

    private void validate() {
        if (yolcu == null)
            throw new RuntimeException("Yolcu bilgisi boş olamaz!");
        if (sefer == null)
            throw new RuntimeException("Sefer boş olamaz!");
        if (koltuk == null)
            throw new RuntimeException("Koltuk seçilmelidir!");
        if (fiyat <= 0)
            throw new RuntimeException("Fiyat geçersizdir!");
    }

    public Bilet build() {
        validate();
        Bilet bilet = new Bilet();
        bilet.setId(id);
        bilet.setYolcu(yolcu);
        bilet.setSefer(sefer);
        bilet.setKoltuk(koltuk);
        bilet.setFiyat(fiyat);
        bilet.setDurum(durum != null ? durum : "AKTIF");
        // Tarih null ise şu anki tarihi string olarak ata (basitçe)
        bilet.setTarih(
                tarih != null ? tarih : new java.text.SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date()));
        System.out.println("[BUILDER] Yeni bilet oluşturuldu: " + yolcu.getAdSoyad());
        return bilet;
    }
}
