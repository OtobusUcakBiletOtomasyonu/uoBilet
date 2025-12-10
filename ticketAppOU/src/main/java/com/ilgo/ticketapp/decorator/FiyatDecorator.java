package com.ilgo.ticketapp.decorator;

/**
 * Decorator desenindeki soyut decorator sınıfı.
 */
public abstract class FiyatDecorator implements FiyatHesaplayici {

    /**
     * Süslenen iç hesaplayıcı (TemelFiyat veya başka bir decorator).
     */
    protected final FiyatHesaplayici icFiyatHesaplayici;

    protected FiyatDecorator(FiyatHesaplayici icFiyatHesaplayici) {
        this.icFiyatHesaplayici = icFiyatHesaplayici;
    }

    @Override
    public double hesapla() {
        return icFiyatHesaplayici.hesapla();
    }

    @Override
    public String aciklama() {
        return icFiyatHesaplayici.aciklama();
    }
}

