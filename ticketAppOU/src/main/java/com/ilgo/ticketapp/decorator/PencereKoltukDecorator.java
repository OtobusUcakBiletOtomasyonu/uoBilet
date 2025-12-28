package com.ilgo.ticketapp.decorator;

/**
 * Pencere kenarı koltuk seçimi için decorator.
 */
public class PencereKoltukDecorator extends FiyatDecorator {

    private final double ekstraTutar;

    public PencereKoltukDecorator(FiyatHesaplayici icFiyatHesaplayici) {
        this(icFiyatHesaplayici, 30.0);
    }

    public PencereKoltukDecorator(FiyatHesaplayici icFiyatHesaplayici, double ekstraTutar) {
        super(icFiyatHesaplayici);
        this.ekstraTutar = ekstraTutar;
    }

    @Override
    public double hesapla() {
        return icFiyatHesaplayici.hesapla() + ekstraTutar;
    }

    @Override
    public String aciklama() {
        return icFiyatHesaplayici.aciklama() + " + Pencere Kenarı Koltuk (" + ekstraTutar + " TL)";
    }
}

