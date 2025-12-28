package com.ticketapp.backend.pattern.strategy;

import com.ticketapp.backend.domain.Odeme;
import com.ticketapp.backend.strategy.OdemeStrategy;

// Strategy Pattern: Context Class
public class OdemeContext {
    private OdemeStrategy strategy;

    public void setStrategy(OdemeStrategy strategy) {
        this.strategy = strategy;
    }

    public void odemeYap(Odeme odeme) {
        if (strategy == null) {
            throw new IllegalStateException("Ödeme stratejisi seçilmedi!");
        }
        strategy.ode(odeme);
    }
}
