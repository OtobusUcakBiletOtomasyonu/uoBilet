package com.ilgo.ticketapp.decorator;

/**
 * Ek bagaj hakkı için decorator.
 */
public class BagajDecorator extends FiyatDecorator {

    private final double ekstraTutar;

    public BagajDecorator(FiyatHesaplayici icFiyatHesaplayici) {
        this(icFiyatHesaplayici, 70.0);
    }

    public BagajDecorator(FiyatHesaplayici icFiyatHesaplayici, double ekstraTutar) {
        super(icFiyatHesaplayici);
        this.ekstraTutar = ekstraTutar;
    }

    @Override
    public double hesapla() {
        return icFiyatHesaplayici.hesapla() + ekstraTutar;
    }

    @Override
    public String aciklama() {
        return icFiyatHesaplayici.aciklama() + " + Ek Bagaj (" + ekstraTutar + " TL)";
    }
}

