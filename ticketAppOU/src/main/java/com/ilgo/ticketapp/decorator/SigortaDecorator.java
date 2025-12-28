package com.ilgo.ticketapp.decorator;

/**
 * Seyahat sigortası için decorator.
 */
public class SigortaDecorator extends FiyatDecorator {

    private final double ekstraTutar;

    public SigortaDecorator(FiyatHesaplayici icFiyatHesaplayici) {
        this(icFiyatHesaplayici, 50.0);
    }

    public SigortaDecorator(FiyatHesaplayici icFiyatHesaplayici, double ekstraTutar) {
        super(icFiyatHesaplayici);
        this.ekstraTutar = ekstraTutar;
    }

    @Override
    public double hesapla() {
        return icFiyatHesaplayici.hesapla() + ekstraTutar;
    }

    @Override
    public String aciklama() {
        return icFiyatHesaplayici.aciklama() + " + Sigorta (" + ekstraTutar + " TL)";
    }
}

