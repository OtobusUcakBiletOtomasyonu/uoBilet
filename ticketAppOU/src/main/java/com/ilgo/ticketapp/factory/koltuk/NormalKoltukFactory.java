package com.ilgo.ticketapp.factory.koltuk;

import com.ilgo.ticketapp.entity.Koltuk;

public class NormalKoltukFactory extends KoltukFactory {

    @Override

    protected Koltuk buildKoltuk(int no) {

        return new Koltuk(null, null, no, "BOS");

    }

}
