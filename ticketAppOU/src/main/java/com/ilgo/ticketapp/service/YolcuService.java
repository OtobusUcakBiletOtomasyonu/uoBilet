package com.ilgo.ticketapp.service;

import com.ilgo.ticketapp.entity.Yolcu;
import java.util.List;

/**
 * Yolcu için iş kurallarının tanımlandığı arayüz.
 */
public interface YolcuService {

    void kaydet(Yolcu yolcu);

    Yolcu getir(String id);

    List<Yolcu> listele();

    void sil(String id);
}
