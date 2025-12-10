package com.ilgo.ticketapp.service;

import com.ilgo.ticketapp.entity.Odeme;

public interface OdemeService {

    Odeme odemeYap(String biletId, double tutar, String odemeTuru);

    Odeme getir(String id);
}
