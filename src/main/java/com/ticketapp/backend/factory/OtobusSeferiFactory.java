package com.ticketapp.backend.factory;

import com.ticketapp.backend.domain.OtobusSeferi;
import com.ticketapp.backend.domain.Sefer;
import com.ticketapp.backend.domain.SeferType;
import org.springframework.stereotype.Component;

@Component("OTOBUS_FACTORY")
public class OtobusSeferiFactory implements SeferFactory {

    @Override
    public Sefer createSefer() {
        OtobusSeferi sefer = new OtobusSeferi();
        sefer.setTip(SeferType.OTOBUS);
        return sefer;
    }
}
