package com.ilgo.ticketapp.decorator;

/**
 * Özel ikram / yemek paketi için decorator.
 */
public class IkramDecorator extends FiyatDecorator {

    private final double ekstraTutar;

    public IkramDecorator(FiyatHesaplayici icFiyatHesaplayici) {
        this(icFiyatHesaplayici, 90.0);
    }

    public IkramDecorator(FiyatHesaplayici icFiyatHesaplayici, double ekstraTutar) {
        super(icFiyatHesaplayici);
        this.ekstraTutar = ekstraTutar;
    }

    @Override
    public double hesapla() {
        return icFiyatHesaplayici.hesapla() + ekstraTutar;
    }

    @Override
    public String aciklama() {
        return icFiyatHesaplayici.aciklama() + " + İkram Paketi (" + ekstraTutar + " TL)";
    }
}

