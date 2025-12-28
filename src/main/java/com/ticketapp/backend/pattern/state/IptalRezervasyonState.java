package com.ticketapp.backend.pattern.state;

import com.ticketapp.backend.domain.Rezervasyon;

public class IptalRezervasyonState implements RezervasyonState {

    @Override
    public void onayla(Rezervasyon rezervasyon) {
        throw new IllegalStateException("İptal edilmiş rezervasyon onaylanamaz.");
    }

    @Override
    public void iptalEt(Rezervasyon rezervasyon) {
        System.out.println("Zaten iptal edilmiş.");
    }
}
