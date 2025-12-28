package com.ticketapp.backend.strategy;

import com.ticketapp.backend.domain.Odeme;
import org.springframework.stereotype.Component;

@Component("HAVALE")
public class HavaleOdemeStrategy implements OdemeStrategy {
    @Override
    public void ode(Odeme odeme) {
        System.out.println("Havale ile ödeme alındı: " + odeme.getTutar());
        odeme.setOdemeTipi("HAVALE");
        // Havale kontrol islemleri
    }
}
