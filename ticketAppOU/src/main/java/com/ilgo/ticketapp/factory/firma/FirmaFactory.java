package com.ilgo.ticketapp.factory.firma;

import com.ilgo.ticketapp.entity.Firma;

public abstract class FirmaFactory {

    public Firma createFirma(int id, String ad) {

        if (ad == null || ad.isEmpty())

            throw new RuntimeException("Firma adı boş olamaz!");

        return buildFirma(id, ad);

    }

    protected abstract Firma buildFirma(int id, String ad);

}

