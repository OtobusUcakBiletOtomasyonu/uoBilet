package com.ilgo.ticketapp.factory.koltuk;

import com.ilgo.ticketapp.entity.Koltuk;

public abstract class KoltukFactory {

    public Koltuk createKoltuk(int no) {

        if (no <= 0)

            throw new RuntimeException("Koltuk numarası geçersiz!");

        return buildKoltuk(no);

    }

    protected abstract Koltuk buildKoltuk(int no);

}

