package com.ilgo.ticketapp.service;

import com.ilgo.ticketapp.entity.Bilet;

import java.util.List;

public interface BiletService {

    Bilet biletAl(String yolcuId, String seferId, String koltukId,
            boolean vip, boolean sigorta, boolean ikram,
            boolean bagaj, boolean hizliCheckIn);

    void biletIptal(String biletId);

    Bilet getir(String id);

    List<Bilet> listele();

    // Keeping this for compatibility if other parts use it
    default List<Bilet> yolcuBiletleri(String yolcuId) {
        return List.of();
    }
}
