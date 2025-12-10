package com.ilgo.ticketapp.factory.firma;

import com.ilgo.ticketapp.entity.Firma;

public class HavaYoluFirmasiFactory extends FirmaFactory {

    @Override

    protected Firma buildFirma(int id, String ad) {

        return new Firma(id, ad + " (Havayolu)");

    }

}

