package com.ilgo.ticketapp.factory.odeme;

import com.ilgo.ticketapp.entity.Odeme;

public interface OdemeFactory {
    Odeme odemeOlustur(double tutar, String biletId);
}
