package com.ticketapp.backend.pattern.state;

import com.ticketapp.backend.domain.KoltukDurumu;
import com.ticketapp.backend.domain.Rezervasyon;
import com.ticketapp.backend.domain.RezervasyonDurumu;

public class BeklemedeState implements RezervasyonState {

    @Override
    public void onayla(Rezervasyon rezervasyon) {
        System.out.println("Rezervasyon onaylanıyor (Satışa dönüyor)...");
        rezervasyon.setDurum(RezervasyonDurumu.ONAYLI);
        // Koltuk durumu State uzerinden yonetilebilir ama burada direk set ediyoruz
        // orneklerdeki gibi
        rezervasyon.getKoltuk().setDurum(KoltukDurumu.DOLU);
    }

    @Override
    public void iptalEt(Rezervasyon rezervasyon) {
        System.out.println("Rezervasyon iptal edildi.");
        rezervasyon.setDurum(RezervasyonDurumu.IPTAL);
        rezervasyon.getKoltuk().setDurum(KoltukDurumu.BOS);
    }
}
