package com.ticketapp.backend.pattern.state;

import com.ticketapp.backend.domain.Rezervasyon;

public interface RezervasyonState {
    void onayla(Rezervasyon rezervasyon);

    void iptalEt(Rezervasyon rezervasyon);
}
