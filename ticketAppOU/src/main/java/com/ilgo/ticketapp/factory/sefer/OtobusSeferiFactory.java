package com.ilgo.ticketapp.factory.sefer;

import com.ilgo.ticketapp.entity.Sefer;

import com.ilgo.ticketapp.entity.OtobusSeferi;

import java.util.Date;

/**
 * 
 * Otobüs seferi oluşturan fabrika.
 * 
 */

public class OtobusSeferiFactory extends SeferFactory {

    @Override

    protected Sefer buildSefer(String id,

            String kalkis,

            String varis,

            String tarih,

            String saat,

            double fiyat) {

        return new OtobusSeferi(id, kalkis, varis, tarih, saat, fiyat, true);

    }

}
