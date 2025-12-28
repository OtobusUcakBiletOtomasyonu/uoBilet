package com.ilgo.ticketapp.builder;

import com.ilgo.ticketapp.entity.Koltuk;
import com.ilgo.ticketapp.entity.Rezervasyon;
import com.ilgo.ticketapp.entity.Sefer;
import com.ilgo.ticketapp.entity.Yolcu;

import java.util.Date;

/**
 * Rezervasyon nesnesini adım adım oluşturan builder.
 */
public class RezervasyonBuilder {

    private int rezervasyonId;
    private Yolcu yolcu;
    private Sefer sefer;
    private Koltuk koltuk;
    private String durum;
    private Date tarih;

    public RezervasyonBuilder setRezervasyonId(int id) {
        this.rezervasyonId = id;
        return this;
    }

    public RezervasyonBuilder setYolcu(Yolcu yolcu) {
        this.yolcu = yolcu;
        return this;
    }

    public RezervasyonBuilder setSefer(Sefer sefer) {
        this.sefer = sefer;
        return this;
    }

    public RezervasyonBuilder setKoltuk(Koltuk koltuk) {
        this.koltuk = koltuk;
        return this;
    }

    public RezervasyonBuilder setDurum(String durum) {
        this.durum = durum;
        return this;
    }

    public RezervasyonBuilder setTarih(Date tarih) {
        this.tarih = tarih;
        return this;
    }

    private void validate() {
        if (yolcu == null) throw new RuntimeException("Rezervasyon için yolcu gerekli!");
        if (sefer == null) throw new RuntimeException("Rezervasyon için sefer gerekli!");
        if (koltuk == null) throw new RuntimeException("Rezervasyon için koltuk gerekli!");
    }

    public Rezervasyon build() {
        validate();
        Rezervasyon r = new Rezervasyon();
        r.setRezervasyonId(rezervasyonId);
        r.setYolcu(yolcu);
        r.setSefer(sefer);
        r.setKoltuk(koltuk);
        r.setDurum(durum != null ? durum : "AKTIF");
        r.setTarih(tarih != null ? tarih : new Date());
        System.out.println("[BUILDER] Rezervasyon oluşturuldu");
        return r;
    }
}

