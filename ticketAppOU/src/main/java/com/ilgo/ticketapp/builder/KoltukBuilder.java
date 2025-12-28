package com.ilgo.ticketapp.builder;

import com.ilgo.ticketapp.entity.Koltuk;

/**
 * Koltuk nesnesini daha okunabilir şekilde oluşturmaya yarayan builder.
 */
public class KoltukBuilder {

    private String id;
    private String seferId;
    private int koltukNo;
    private String tip;
    private String durum;

    public KoltukBuilder setId(String id) {
        this.id = id;
        return this;
    }

    public KoltukBuilder setSeferId(String seferId) {
        this.seferId = seferId;
        return this;
    }

    public KoltukBuilder setKoltukNo(int koltukNo) {
        this.koltukNo = koltukNo;
        return this;
    }

    public KoltukBuilder setTip(String tip) {
        this.tip = tip;
        return this;
    }

    public KoltukBuilder setDurum(String durum) {
        this.durum = durum;
        return this;
    }

    private void validate() {
        if (koltukNo <= 0)
            throw new RuntimeException("Geçersiz koltuk numarası!");
        if (tip == null)
            throw new RuntimeException("Koltuk tipi boş olamaz!");
    }

    public Koltuk build() {
        validate();
        Koltuk k = new Koltuk();
        k.setId(id);
        k.setSeferId(seferId);
        k.setKoltukNo(koltukNo);
        k.setTip(tip);
        k.setDurum(durum != null ? durum : "BOS");
        System.out.println("[BUILDER] Yeni koltuk üretildi → No: " + koltukNo);
        return k;
    }
}
