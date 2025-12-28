package com.ticketapp.backend.pattern.state;

import com.ticketapp.backend.domain.Koltuk;
import com.ticketapp.backend.domain.KoltukDurumu;

public class KoltukRezerveState implements KoltukState {

    @Override
    public void satinAl(Koltuk koltuk) {
        System.out.println("Rezerve koltuk satın alındı.");
        koltuk.setDurum(KoltukDurumu.DOLU);
    }

    @Override
    public void rezerveEt(Koltuk koltuk) {
        System.out.println("Koltuk zaten rezerve.");
    }

    @Override
    public void serbestBirak(Koltuk koltuk) {
        System.out.println("Rezervasyon iptal edildi, koltuk boş.");
        koltuk.setDurum(KoltukDurumu.BOS);
    }
}
