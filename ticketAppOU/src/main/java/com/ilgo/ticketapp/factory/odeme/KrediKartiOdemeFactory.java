package com.ilgo.ticketapp.factory.odeme;

import com.ilgo.ticketapp.entity.Odeme;

public class KrediKartiOdemeFactory implements OdemeFactory {

    @Override
    public Odeme odemeOlustur(double tutar, String biletId) {

        Odeme odeme = new Odeme();
        odeme.setOdemeTuru("KREDI_KARTI");
        odeme.setTutar(tutar);
        odeme.setBiletId(biletId);
        odeme.setSonuc("Ödeme işlemi simülasyonu başarılı.");
        odeme.setTarih(java.time.LocalDateTime.now().toString());

        return odeme;
    }
}
