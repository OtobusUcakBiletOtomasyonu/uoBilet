package com.ticketapp.backend.service;

import com.ticketapp.backend.domain.Koltuk;
import com.ticketapp.backend.domain.KoltukDurumu;
import com.ticketapp.backend.domain.Sefer;
import com.ticketapp.backend.repository.KoltukRepository;
import com.ticketapp.backend.repository.SeferRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KoltukService {

    private final KoltukRepository koltukRepository;
    private final SeferRepository seferRepository;

    public KoltukService(KoltukRepository koltukRepository, SeferRepository seferRepository) {
        this.koltukRepository = koltukRepository;
        this.seferRepository = seferRepository;
    }

    public Koltuk createKoltuk(Long seferId, String numara) {
        Sefer sefer = seferRepository.findById(seferId).orElseThrow(() -> new RuntimeException("Sefer bulunamadı"));

        // Ayni numarali koltuk var mi kontrol et
        if (koltukRepository.findBySeferIdAndNumara(seferId, numara).isPresent()) {
            throw new RuntimeException("Bu numaralı koltuk zaten var.");
        }

        Koltuk koltuk = new Koltuk();
        koltuk.setSefer(sefer);
        koltuk.setNumara(numara);
        koltuk.setDurum(KoltukDurumu.BOS);

        return koltukRepository.save(koltuk);
    }

    public List<Koltuk> getKoltuklarBySefer(Long seferId) {
        return koltukRepository.findBySeferId(seferId);
    }
}
