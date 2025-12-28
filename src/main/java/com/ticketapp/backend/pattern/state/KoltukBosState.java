package com.ticketapp.backend.pattern.state;

import com.ticketapp.backend.domain.Koltuk;
import com.ticketapp.backend.domain.KoltukDurumu;

public class KoltukBosState implements KoltukState {

    @Override
    public void satinAl(Koltuk koltuk) {
        System.out.println("Koltuk satın alındı.");
        koltuk.setDurum(KoltukDurumu.DOLU);
    }

    @Override
    public void rezerveEt(Koltuk koltuk) {
        System.out.println("Koltuk rezerve edildi.");
        koltuk.setDurum(KoltukDurumu.REZERVE);
    }

    @Override
    public void serbestBirak(Koltuk koltuk) {
        System.out.println("Koltuk zaten boş.");
    }
}
