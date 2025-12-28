package com.ilgo.ticketapp.decorator;

/**
 * Hızlı check-in / öncelikli geçiş için decorator.
 */
public class HizliCheckInDecorator extends FiyatDecorator {

    private final double ekstraTutar;

    public HizliCheckInDecorator(FiyatHesaplayici icFiyatHesaplayici) {
        this(icFiyatHesaplayici, 40.0);
    }

    public HizliCheckInDecorator(FiyatHesaplayici icFiyatHesaplayici, double ekstraTutar) {
        super(icFiyatHesaplayici);
        this.ekstraTutar = ekstraTutar;
    }

    @Override
    public double hesapla() {
        return icFiyatHesaplayici.hesapla() + ekstraTutar;
    }

    @Override
    public String aciklama() {
        return icFiyatHesaplayici.aciklama() + " + Hızlı Check-in (" + ekstraTutar + " TL)";
    }
}

