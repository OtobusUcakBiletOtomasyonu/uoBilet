package com.ticketapp.backend.strategy;

import com.ticketapp.backend.domain.Odeme;
import org.springframework.stereotype.Component;

@Component("BAKIYE")
public class BakiyeOdemeStrategy implements OdemeStrategy {
    @Override
    public void ode(Odeme odeme) {
        System.out.println("Cüzdan bakiyesi ile ödeme alındı: " + odeme.getTutar());
        odeme.setOdemeTipi("BAKIYE");
        // Bakiye dusurme islemleri
    }
}
