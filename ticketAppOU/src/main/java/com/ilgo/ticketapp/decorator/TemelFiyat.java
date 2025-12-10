package com.ilgo.ticketapp.decorator;

/**
 * Temel bilet fiyatını temsil eden sınıf.
 * Decorator desenindeki "Concrete Component".
 */
public class TemelFiyat implements FiyatHesaplayici {

    private final double tabanFiyat;

    public TemelFiyat(double tabanFiyat) {
        this.tabanFiyat = tabanFiyat;
    }

    @Override
    public double hesapla() {
        return tabanFiyat;
    }

    @Override
    public String aciklama() {
        return "Temel bilet fiyatı (" + tabanFiyat + " TL)";
    }

    public double getTabanFiyat() {
        return tabanFiyat;
    }
}

