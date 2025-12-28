package com.ticketapp.backend.pattern.state;

import com.ticketapp.backend.domain.Koltuk;
import com.ticketapp.backend.domain.KoltukDurumu;

public class KoltukDoluState implements KoltukState {

    @Override
    public void satinAl(Koltuk koltuk) {
        throw new IllegalStateException("Dolu koltuk satın alınamaz!");
    }

    @Override
    public void rezerveEt(Koltuk koltuk) {
        throw new IllegalStateException("Dolu koltuk rezerve edilemez!");
    }

    @Override
    public void serbestBirak(Koltuk koltuk) {
        System.out.println("Koltuk serbest bırakıldı (iade).");
        koltuk.setDurum(KoltukDurumu.BOS);
    }
}
