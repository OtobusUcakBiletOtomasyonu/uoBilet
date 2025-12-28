package com.ticketapp.backend.service;

import com.ticketapp.backend.domain.*;
import com.ticketapp.backend.repository.KoltukRepository;
import com.ticketapp.backend.repository.RezervasyonRepository;
import com.ticketapp.backend.repository.YolcuRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RezervasyonService {

    private final RezervasyonRepository rezervasyonRepository;
    private final KoltukRepository koltukRepository;
    private final YolcuRepository yolcuRepository;

    public RezervasyonService(RezervasyonRepository rezervasyonRepository, KoltukRepository koltukRepository,
            YolcuRepository yolcuRepository) {
        this.rezervasyonRepository = rezervasyonRepository;
        this.koltukRepository = koltukRepository;
        this.yolcuRepository = yolcuRepository;
    }

    @Transactional
    public Rezervasyon rezervasyonYap(Long yolcuId, Long koltukId) {
        Yolcu yolcu = yolcuRepository.findById(yolcuId).orElseThrow(() -> new RuntimeException("Yolcu bulunamadı"));
        Koltuk koltuk = koltukRepository.findById(koltukId)
                .orElseThrow(() -> new RuntimeException("Koltuk bulunamadı"));

        if (koltuk.getDurum() != KoltukDurumu.BOS) {
            throw new RuntimeException("Koltuk müsait değil (Dolu veya Rezerve)");
        }

        Rezervasyon rezervasyon = new Rezervasyon();
        rezervasyon.setYolcu(yolcu);
        rezervasyon.setKoltuk(koltuk);
        rezervasyon.setRezervasyonTarihi(LocalDateTime.now());
        rezervasyon.setSonGecerlilikTarihi(LocalDateTime.now().plusHours(24)); // 24 saat gecerli
        rezervasyon.setDurum(RezervasyonDurumu.BEKLEMEDE);

        // Koltuk Update -> REZERVE
        koltuk.setDurum(KoltukDurumu.REZERVE);
        koltukRepository.save(koltuk);

        return rezervasyonRepository.save(rezervasyon);
    }

    @Transactional
    public void iptalEt(Long rezervasyonId) {
        Rezervasyon rezervasyon = rezervasyonRepository.findById(rezervasyonId)
                .orElseThrow(() -> new RuntimeException("Rezervasyon bulunamadı"));

        rezervasyon.setDurum(RezervasyonDurumu.IPTAL);

        // Koltuk Update -> BOS
        rezervasyon.getKoltuk().setDurum(KoltukDurumu.BOS);
        koltukRepository.save(rezervasyon.getKoltuk());

        rezervasyonRepository.save(rezervasyon);
    }

    public List<Rezervasyon> getRezervasyonlarByYolcu(Long yolcuId) {
        return rezervasyonRepository.findByYolcuId(yolcuId);
    }
}
