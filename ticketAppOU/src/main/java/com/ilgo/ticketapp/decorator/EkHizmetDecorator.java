package com.ilgo.ticketapp.decorator;

/**
 * Ek hizmet (ikram, yemek, içecek vb.) için decorator.
 */
public class EkHizmetDecorator extends FiyatDecorator {

    private final double ekstraTutar;

    public EkHizmetDecorator(FiyatHesaplayici icFiyatHesaplayici) {
        this(icFiyatHesaplayici, 100.0);
    }

    public EkHizmetDecorator(FiyatHesaplayici icFiyatHesaplayici, double ekstraTutar) {
        super(icFiyatHesaplayici);
        this.ekstraTutar = ekstraTutar;
    }

    @Override
    public double hesapla() {
        return icFiyatHesaplayici.hesapla() + ekstraTutar;
    }

    @Override
    public String aciklama() {
        return icFiyatHesaplayici.aciklama() + " + Ek Hizmet (" + ekstraTutar + " TL)";
    }
}

