package com.ticketapp.backend.service;

import com.ticketapp.backend.domain.Firma;
import com.ticketapp.backend.repository.FirmaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FirmaService {

    private final FirmaRepository firmaRepository;

    public FirmaService(FirmaRepository firmaRepository) {
        this.firmaRepository = firmaRepository;
    }

    public Firma createFirma(String ad) {
        Firma firma = new Firma();
        firma.setAd(ad);
        return firmaRepository.save(firma);
    }

    public List<Firma> getAllFirmalar() {
        return firmaRepository.findAll();
    }
}
