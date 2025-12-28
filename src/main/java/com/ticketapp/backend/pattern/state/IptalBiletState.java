package com.ticketapp.backend.pattern.state;

import com.ticketapp.backend.domain.Bilet;

public class IptalBiletState implements BiletState {

    @Override
    public void iptalEt(Bilet bilet) {
        System.out.println("Bilet zaten iptal edilmiş.");
    }

    @Override
    public void aktifEt(Bilet bilet) {
        // Iptal edilen bilet tekrar aktif edilemez (veya kurala gore degisir)
        throw new IllegalStateException("İptal edilmiş bilet tekrar aktif edilemez.");
    }
}
