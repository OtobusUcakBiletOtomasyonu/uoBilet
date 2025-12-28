package com.ticketapp.backend.service;

import com.ticketapp.backend.domain.Bilet;
import com.ticketapp.backend.dto.response.RaporDTO;
import com.ticketapp.backend.repository.BiletRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RaporService {

    private final BiletRepository biletRepository;

    public RaporService(BiletRepository biletRepository) {
        this.biletRepository = biletRepository;
    }

    public RaporDTO alSatisRaporu(LocalDateTime baslangic, LocalDateTime bitis) {
        List<Bilet> tumBiletler = biletRepository.findAll();

        List<Bilet> aralikTAKIBiletler = tumBiletler.stream()
                .filter(b -> b.getSatinAlmaTarihi().isAfter(baslangic) && b.getSatinAlmaTarihi().isBefore(bitis))
                .collect(Collectors.toList());

        RaporDTO rapor = new RaporDTO();
        rapor.setToplamBiletSayisi(aralikTAKIBiletler.size());

        double ciro = aralikTAKIBiletler.stream()
                .mapToDouble(b -> b.getOdeme() != null ? b.getOdeme().getTutar() : 0)
                .sum();

        rapor.setToplamCiro(ciro);
        return rapor;
    }
}
