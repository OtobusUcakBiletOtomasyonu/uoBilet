package com.ilgo.ticketapp.decorator;

/**
 * VIP koltuk seçimi için decorator.
 */
public class VipKoltukDecorator extends FiyatDecorator {

    private final double ekstraTutar;

    public VipKoltukDecorator(FiyatHesaplayici icFiyatHesaplayici) {
        this(icFiyatHesaplayici, 150.0);
    }

    public VipKoltukDecorator(FiyatHesaplayici icFiyatHesaplayici, double ekstraTutar) {
        super(icFiyatHesaplayici);
        this.ekstraTutar = ekstraTutar;
    }

    @Override
    public double hesapla() {
        return icFiyatHesaplayici.hesapla() + ekstraTutar;
    }

    @Override
    public String aciklama() {
        return icFiyatHesaplayici.aciklama() + " + VIP Koltuk (" + ekstraTutar + " TL)";
    }
}

