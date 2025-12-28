package com.ilgo.ticketapp.builder;

import com.ilgo.ticketapp.entity.Odeme;

import java.util.Date;

/**
 * Ödeme nesnesinin kontrollü şekilde oluşturulmasını sağlayan builder
 * sınıfıdır.
 */
public class OdemeBuilder {

    private String odemeId;
    private double tutar;
    private String tarih;
    private String tip;
    private String durum;

    public OdemeBuilder setOdemeId(String odemeId) {
        this.odemeId = odemeId;
        return this;
    }

    public OdemeBuilder setTutar(double tutar) {
        this.tutar = tutar;
        return this;
    }

    public OdemeBuilder setTarih(String tarih) {
        this.tarih = tarih;
        return this;
    }

    public OdemeBuilder setTip(String tip) {
        this.tip = tip;
        return this;
    }

    public OdemeBuilder setDurum(String durum) {
        this.durum = durum;
        return this;
    }

    private void validate() {
        if (tutar <= 0)
            throw new RuntimeException("Tutar 0’dan büyük olmalıdır!");
        if (tip == null)
            throw new RuntimeException("Ödeme tipi boş olamaz!");
    }

    public Odeme build() {
        validate();
        Odeme odeme = new Odeme();
        odeme.setId(odemeId);
        odeme.setTutar(tutar);
        odeme.setTarih(tarih != null ? tarih : new Date().toString());
        odeme.setOdemeTuru(tip);
        odeme.setSonuc(durum != null ? durum : "BASARILI");
        System.out.println("[BUILDER] Yeni ödeme oluşturuldu → " + tutar + " TL");
        return odeme;
    }
}
