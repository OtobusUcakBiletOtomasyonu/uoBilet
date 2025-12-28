package com.ticketapp.backend.strategy;

import com.ticketapp.backend.domain.Odeme;
import org.springframework.stereotype.Component;

@Component("KREDI_KARTI")
public class KrediKartiOdemeStrategy implements OdemeStrategy {
    @Override
    public void ode(Odeme odeme) {
        System.out.println("Kredi kartı ile ödeme alındı: " + odeme.getTutar());
        odeme.setOdemeTipi("KREDI_KARTI");
        // Burada banka entegrasyonu simule edilebilir
    }
}
