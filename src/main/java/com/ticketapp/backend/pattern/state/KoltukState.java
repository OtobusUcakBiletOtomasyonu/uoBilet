package com.ticketapp.backend.pattern.state;

import com.ticketapp.backend.domain.Koltuk;

public interface KoltukState {
    void satinAl(Koltuk koltuk);

    void rezerveEt(Koltuk koltuk);

    void serbestBirak(Koltuk koltuk);
}
