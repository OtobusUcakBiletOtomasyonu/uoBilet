package com.ilgo.ticketapp.factory.sefer;

import com.ilgo.ticketapp.entity.Sefer;

import com.ilgo.ticketapp.entity.UcakSeferi;

import java.util.Date;

/**
 * 
 * Uçak seferi oluşturan fabrika.
 * 
 */

public class UcakSeferiFactory extends SeferFactory {

    @Override

    protected Sefer buildSefer(String id,

            String kalkis,

            String varis,

            String tarih,

            String saat,

            double fiyat) {

        return new UcakSeferi(id, kalkis, varis, tarih, saat, fiyat, "Boeing 737");

    }

}
