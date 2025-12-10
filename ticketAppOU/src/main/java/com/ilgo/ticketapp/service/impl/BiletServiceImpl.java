package com.ilgo.ticketapp.service.impl;

import com.ilgo.ticketapp.builder.BiletBuilder;
import com.ilgo.ticketapp.config.IdGenerator;
import com.ilgo.ticketapp.decorator.*;
import com.ilgo.ticketapp.entity.Bilet;
import com.ilgo.ticketapp.entity.Koltuk;
import com.ilgo.ticketapp.entity.Sefer;
import com.ilgo.ticketapp.entity.Yolcu;
import com.ilgo.ticketapp.repository.BiletRepository;
import com.ilgo.ticketapp.service.BiletService;
import com.ilgo.ticketapp.service.KoltukService;
import com.ilgo.ticketapp.service.SeferService;
import com.ilgo.ticketapp.service.YolcuService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BiletServiceImpl implements BiletService {

    private final BiletRepository biletRepository;
    private final SeferService seferService;
    private final KoltukService koltukService;
    private final YolcuService yolcuService;
    private final IdGenerator idGenerator;

    public BiletServiceImpl(BiletRepository biletRepository,
            SeferService seferService,
            KoltukService koltukService,
            YolcuService yolcuService,
            IdGenerator idGenerator) {

        this.biletRepository = biletRepository;
        this.seferService = seferService;
        this.koltukService = koltukService;
        this.yolcuService = yolcuService;
        this.idGenerator = idGenerator;
    }

    @Override
    public Bilet biletAl(String yolcuId, String seferId, String koltukId,
            boolean vip, boolean sigorta, boolean ikram,
            boolean bagaj, boolean hizliCheckIn) {

        // Yolcu - Sefer - Koltuk bilgilerini getir
        Yolcu yolcu = (Yolcu) yolcuService.getir(yolcuId); // Cast to Yolcu if getir returns Kullanici
        Sefer sefer = seferService.getir(seferId);
        Koltuk koltuk = koltukService.getir(koltukId);

        if (koltuk.getDurum().equals("DOLU")) {
            throw new RuntimeException("Bu koltuk dolu!");
        }

        // ----------------------
        // 1) DECORATOR: fiyat hesapla
        // ----------------------
        FiyatHesaplayici fiyat = new TemelFiyat(sefer.getFiyat());

        if (vip)
            fiyat = new VipKoltukDecorator(fiyat);
        if (sigorta)
            fiyat = new SigortaDecorator(fiyat);
        if (ikram)
            fiyat = new IkramDecorator(fiyat);
        if (bagaj)
            fiyat = new BagajDecorator(fiyat);
        if (hizliCheckIn)
            fiyat = new HizliCheckInDecorator(fiyat);

        double toplamFiyat = fiyat.hesapla();

        // ----------------------
        // 2) BUILDER: bilet nesnesi oluştur
        // ----------------------
        Bilet bilet = new BiletBuilder()
                .setId(idGenerator.generateId())
                .setYolcu(yolcu)
                .setSefer(sefer)
                .setKoltuk(koltuk)
                .setFiyat(toplamFiyat)
                .setDurum("AKTIF")
                .build();

        // Koltuğu doldur
        koltukService.durumDegistir(koltukId, "DOLU");

        // Bileti kaydet
        biletRepository.save(bilet);

        return bilet;
    }

    @Override
    public void biletIptal(String biletId) {
        Bilet bilet = biletRepository.findById(biletId);

        if (bilet == null) {
            throw new RuntimeException("Bilet bulunamadı!");
        }

        // Koltuğu boşalt
        koltukService.durumDegistir(bilet.getKoltuk().getId(), "BOS");

        // Bilet repository'den sil
        biletRepository.delete(biletId);
    }

    @Override
    public Bilet getir(String id) {
        return biletRepository.findById(id);
    }

    @Override
    public List<Bilet> listele() {
        return biletRepository.findAll();
    }

    @Override
    public List<Bilet> yolcuBiletleri(String yolcuId) {
        return biletRepository.findByYolcu(yolcuId);
    }
}
