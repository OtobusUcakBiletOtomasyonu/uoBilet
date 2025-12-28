package com.ilgo.ticketapp.service;

import com.ilgo.ticketapp.entity.Sefer;
import java.util.List;

public interface SeferService {

    void kaydet(Sefer sefer);

    Sefer getir(String id);

    List<Sefer> listele();

    void sil(String id);

    List<Sefer> ara(String kalkis, String varis, String tarih);
}
