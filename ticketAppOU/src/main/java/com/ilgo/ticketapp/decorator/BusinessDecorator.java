package com.ilgo.ticketapp.decorator;

/**
 * Business Class geçişi için decorator.
 */
public class BusinessDecorator extends FiyatDecorator {

    private final double ekstraTutar;

    public BusinessDecorator(FiyatHesaplayici icFiyatHesaplayici) {
        this(icFiyatHesaplayici, 300.0);
    }

    public BusinessDecorator(FiyatHesaplayici icFiyatHesaplayici, double ekstraTutar) {
        super(icFiyatHesaplayici);
        this.ekstraTutar = ekstraTutar;
    }

    @Override
    public double hesapla() {
        return icFiyatHesaplayici.hesapla() + ekstraTutar;
    }

    @Override
    public String aciklama() {
        return icFiyatHesaplayici.aciklama() + " + Business Class (" + ekstraTutar + " TL)";
    }
}

