package com.ticketapp.backend.pattern.state;

import com.ticketapp.backend.domain.Bilet;
import com.ticketapp.backend.domain.KoltukDurumu;
import com.ticketapp.backend.domain.Odeme;

public class AktifBiletState implements BiletState {

    @Override
    public void iptalEt(Bilet bilet) {
        System.out.println("Bilet iptal ediliyor...");
        // State Pattern: Context state transition
        bilet.setState(new IptalBiletState());

        bilet.getKoltuk().setDurum(KoltukDurumu.BOS);

        // Iade mantigi burada isletilebilir
        Odeme odeme = bilet.getOdeme();
        if (odeme != null) {
            System.out.println(odeme.getTutar() + " tutarı iade edilecek.");
        }
    }

    @Override
    public void aktifEt(Bilet bilet) {
        System.out.println("Bilet zaten aktif.");
    }
}
