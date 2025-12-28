package com.ticketapp.backend.pattern.state;

import com.ticketapp.backend.domain.Rezervasyon;

public class OnayliState implements RezervasyonState {

    @Override
    public void onayla(Rezervasyon rezervasyon) {
        System.out.println("Zaten onaylı.");
    }

    @Override
    public void iptalEt(Rezervasyon rezervasyon) {
        System.out.println("Onaylı rezervasyon iptal ediliyor (İade prosedürü)...");
        // Onayli, yani biletlesmis olabilir veya sadece rezervasyon onaylanmis
        // olabilir.
        // Bu senaryoda rezervasyon onaylandiginda bilete donusmesi beklenir ama burada
        // state gosterimi var.
    }
}
