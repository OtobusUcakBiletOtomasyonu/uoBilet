package com.ticketapp.backend.factory;

import com.ticketapp.backend.domain.Sefer;
import com.ticketapp.backend.domain.SeferType;
import com.ticketapp.backend.domain.UcakSeferi;
import org.springframework.stereotype.Component;

@Component("UCAK_FACTORY")
public class UcakSeferiFactory implements SeferFactory {

    @Override
    public Sefer createSefer() {
        UcakSeferi sefer = new UcakSeferi();
        sefer.setTip(SeferType.UCAK);
        return sefer;
    }
}
