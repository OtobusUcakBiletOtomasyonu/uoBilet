package com.ticketapp.backend.service;

import com.ticketapp.backend.domain.Sefer;
import com.ticketapp.backend.domain.SeferType;
import com.ticketapp.backend.factory.SeferFactory;
import com.ticketapp.backend.repository.SeferRepository;
import com.ticketapp.backend.repository.FirmaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SeferService {

    private final SeferRepository seferRepository;
    private final java.util.Map<String, SeferFactory> factories;
    private final FirmaRepository firmaRepository;
    private final KoltukService koltukService;
    private final com.ticketapp.backend.repository.BiletRepository biletRepository;

    public SeferService(SeferRepository seferRepository, java.util.Map<String, SeferFactory> factories,
            FirmaRepository firmaRepository, KoltukService koltukService,
            com.ticketapp.backend.repository.BiletRepository biletRepository) {
        this.seferRepository = seferRepository;
        this.factories = factories;
        this.firmaRepository = firmaRepository;
        this.koltukService = koltukService;
        this.biletRepository = biletRepository;
    }
    // ... (omitting other methods for brevity, targeting the changed method and
    // constructor)

    public List<com.ticketapp.backend.dto.response.KoltukDurumDTO> getKoltukDurumlari(Long seferId) {
        List<com.ticketapp.backend.domain.Koltuk> koltuklar = koltukService.getKoltuklarBySefer(seferId);

        // Fetch tickets for this trip to get passenger gender
        List<com.ticketapp.backend.domain.Bilet> tickets = biletRepository.findByKoltuk_Sefer_Id(seferId);
        java.util.Map<String, String> seatGenderMap = new java.util.HashMap<>();

        for (com.ticketapp.backend.domain.Bilet bilet : tickets) {
            if (bilet.getKoltuk() != null && bilet.getYolcu() != null && bilet.getYolcu().getCinsiyet() != null) {
                seatGenderMap.put(bilet.getKoltuk().getNumara(), bilet.getYolcu().getCinsiyet().name());
            }
        }

        return koltuklar.stream()
                .map(k -> new com.ticketapp.backend.dto.response.KoltukDurumDTO(
                        k.getNumara(),
                        k.getDurum(),
                        seatGenderMap.getOrDefault(k.getNumara(), null)))
                .collect(java.util.stream.Collectors.toList());
    }

    public Sefer createSefer(SeferType type, String kalkis, String varis, LocalDateTime zaman, double fiyat,
            String koltukDuzeni, Long firmaId) {
        // Factory Selection
        SeferFactory factory = factories.get(type.name() + "_FACTORY");
        if (factory == null) {
            throw new IllegalArgumentException("Bilinmeyen sefer tipi: " + type);
        }

        Sefer sefer = factory.createSefer();
        sefer.setKalkisYeri(kalkis);
        sefer.setVarisYeri(varis);
        sefer.setKalkisZamani(zaman);
        sefer.setFiyat(fiyat);

        if (sefer instanceof com.ticketapp.backend.domain.OtobusSeferi && koltukDuzeni != null) {
            ((com.ticketapp.backend.domain.OtobusSeferi) sefer).setKoltukDuzeni(koltukDuzeni);
        }

        // Firma setlenmeli
        com.ticketapp.backend.domain.Firma firma;
        if (firmaId != null) {
            firma = firmaRepository.findById(firmaId).orElseThrow(() -> new RuntimeException("Firma bulunamadı"));
        } else {
            // Default Firma for Demo
            firma = firmaRepository.findAll().stream().findFirst().orElseGet(() -> {
                com.ticketapp.backend.domain.Firma newFirma = new com.ticketapp.backend.domain.Firma();
                newFirma.setAd("TicketApp Turizm");
                return firmaRepository.save(newFirma);
            });
        }
        sefer.setFirma(firma);

        Sefer savedSefer = seferRepository.save(sefer);

        // Otomatik Koltuk Olustur (1-40) - Basitlik icin
        for (int i = 1; i <= 40; i++) {
            try {
                koltukService.createKoltuk(savedSefer.getId(), String.valueOf(i));
            } catch (Exception e) {
                // Ignore duplicate or error during auto-gen
            }
        }
        return savedSefer;
    }

    public List<Sefer> searchSefer(String kalkis, String varis, LocalDateTime zaman) {
        LocalDateTime startOfDay = zaman.toLocalDate().atStartOfDay();
        LocalDateTime endOfDay = zaman.toLocalDate().atTime(23, 59, 59);
        return seferRepository.findByKalkisYeriIgnoreCaseAndVarisYeriIgnoreCaseAndKalkisZamaniBetween(kalkis, varis,
                startOfDay, endOfDay);
    }

    public List<Sefer> getAllSeferler() {
        return seferRepository.findAll();
    }

    public Sefer getSeferById(Long id) {
        return seferRepository.findById(id).orElseThrow(() -> new RuntimeException("Sefer bulunamadı"));
    }

    public void deleteSefer(Long id) {
        seferRepository.deleteById(id);
    }

}
