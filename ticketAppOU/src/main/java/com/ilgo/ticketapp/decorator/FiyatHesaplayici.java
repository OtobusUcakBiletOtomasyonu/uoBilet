package com.ilgo.ticketapp.decorator;

/**
 * Fiyat hesaplama için temel arayüz.
 */
public interface FiyatHesaplayici {

    /**
     * Toplam fiyatı hesaplar.
     */
    double hesapla();

    /**
     * Uygulanan fiyat kalemlerinin açıklamasını döner.
     */
    String aciklama();
}

