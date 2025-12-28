package com.ilgo.ticketapp.service;

import com.ilgo.ticketapp.entity.Koltuk;
import java.util.List;

public interface KoltukService {

    void ekle(Koltuk koltuk);

    Koltuk getir(String id);

    List<Koltuk> seferKoltuklariniGetir(String seferId);

    void durumDegistir(String id, String yeniDurum);

    void sil(String id);
}
