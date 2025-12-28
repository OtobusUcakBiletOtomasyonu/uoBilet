package com.ticketapp.backend.service;

import com.ticketapp.backend.domain.*;
import com.ticketapp.backend.repository.BiletRepository;
import com.ticketapp.backend.repository.KoltukRepository;
import com.ticketapp.backend.repository.OdemeRepository;
import com.ticketapp.backend.repository.YolcuRepository;
import com.ticketapp.backend.strategy.OdemeStrategy;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BiletService {

    private final BiletRepository biletRepository;
    private final KoltukRepository koltukRepository;
    private final YolcuRepository yolcuRepository;
    private final OdemeRepository odemeRepository;
    private final ApplicationContext context;

    public BiletService(BiletRepository biletRepository, KoltukRepository koltukRepository,
            YolcuRepository yolcuRepository, OdemeRepository odemeRepository,
            ApplicationContext context) {
        this.biletRepository = biletRepository;
        this.koltukRepository = koltukRepository;
        this.yolcuRepository = yolcuRepository;
        this.odemeRepository = odemeRepository;
        this.context = context;
    }

    @Transactional
    public Bilet satinAl(Long yolcuId, Long koltukId, String odemeStrategyName, double tutar) {
        Yolcu yolcu = yolcuRepository.findById(yolcuId).orElseThrow(() -> new RuntimeException("Yolcu bulunamadı"));
        Koltuk koltuk = koltukRepository.findById(koltukId)
                .orElseThrow(() -> new RuntimeException("Koltuk bulunamadı"));

        // State Check: Koltuk BOS mu?
        if (koltuk.getDurum() != KoltukDurumu.BOS && koltuk.getDurum() != KoltukDurumu.REZERVE) {
            // Rezerve ise kontrol et: Ayni kullanici mi? (Basitlik icin rezerve satisina
            // izin verelim veya dolu diyelim)
            throw new RuntimeException("Koltuk uygun değil: " + koltuk.getDurum());
        }

        // Strategy Pattern: Context Usage
        com.ticketapp.backend.pattern.strategy.OdemeContext odemeContext = new com.ticketapp.backend.pattern.strategy.OdemeContext();

        OdemeStrategy strategy;
        try {
            strategy = context.getBean(odemeStrategyName, OdemeStrategy.class);
        } catch (Exception e) {
            throw new RuntimeException("Geçersiz ödeme yöntemi: " + odemeStrategyName);
        }
        odemeContext.setStrategy(strategy);

        // Odeme Olustur
        Odeme odeme = new Odeme();
        odeme.setTutar(tutar);
        odeme.setOdemeTarihi(LocalDateTime.now());

        // Context uzerinden odeme yap
        odemeContext.odemeYap(odeme);

        // Bilet Olustur
        Bilet bilet = new Bilet();
        bilet.setYolcu(yolcu);
        bilet.setKoltuk(koltuk);
        bilet.setSatinAlmaTarihi(LocalDateTime.now());
        // Init State
        bilet.setState(new com.ticketapp.backend.pattern.state.AktifBiletState());

        odeme.setBilet(bilet);
        bilet.setOdeme(odeme);

        // Koltuk State Update -> DOLU
        koltuk.setDurum(KoltukDurumu.DOLU);
        koltukRepository.save(koltuk);

        // odemeRepository.save(odeme); // Removed: Cascaded via Bilet
        return biletRepository.save(bilet);
    }

    @Transactional
    public void iptalEt(Long biletId) {
        Bilet bilet = biletRepository.findById(biletId).orElseThrow(() -> new RuntimeException("Bilet bulunamadı"));

        // State Pattern: Delegate to Context
        bilet.iptalEt();

        // Koltuk logic is now inside State, but for persistence sake we might need to
        // save dependencies
        // Actually, AktifBiletState changes Koltuk status in our logic above.
        // We just need to save the related entities.

        koltukRepository.save(bilet.getKoltuk());
        biletRepository.save(bilet);
    }

    public List<Bilet> getBiletlerByYolcu(Long yolcuId) {
        return biletRepository.findByYolcuId(yolcuId);
    }
}
