package com.ilgo.ticketapp.factory.odeme;

import com.ilgo.ticketapp.entity.Odeme;

public class HavaleOdemeFactory implements OdemeFactory {

    @Override
    public Odeme odemeOlustur(double tutar, String biletId) {

        Odeme odeme = new Odeme();
        odeme.setOdemeTuru("HAVALE");
        odeme.setTutar(tutar);
        odeme.setBiletId(biletId);
        odeme.setSonuc("Havale işlemi başarıyla gerçekleştirildi.");
        odeme.setTarih(java.time.LocalDateTime.now().toString());

        return odeme;
    }
}
