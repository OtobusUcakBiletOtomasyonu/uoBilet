package com.ticketapp.backend.pattern.state;

import com.ticketapp.backend.domain.Bilet;

public interface BiletState {
    void iptalEt(Bilet bilet);

    void aktifEt(Bilet bilet);
}
