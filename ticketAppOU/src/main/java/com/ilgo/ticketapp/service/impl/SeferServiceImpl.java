package com.ilgo.ticketapp.service.impl;

import com.ilgo.ticketapp.config.IdGenerator;
import com.ilgo.ticketapp.entity.Sefer;
import com.ilgo.ticketapp.repository.SeferRepository;
import com.ilgo.ticketapp.service.SeferService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Sefer ile ilgili iş mantığının yazıldığı servis sınıfı.
 */
@Service
public class SeferServiceImpl implements SeferService {

    private final SeferRepository repository;
    private final IdGenerator idGenerator;

    public SeferServiceImpl(SeferRepository repository, IdGenerator idGenerator) {
        this.repository = repository;
        this.idGenerator = idGenerator;
    }

    @Override
    public void kaydet(Sefer sefer) {

        if (sefer.getKalkis() == null || sefer.getVaris() == null) {
            throw new RuntimeException("Kalkış ve varış bilgisi boş olamaz.");
        }

        sefer.setId(idGenerator.generateId());
        repository.save(sefer);
    }

    @Override
    public Sefer getir(String id) {
        Sefer sefer = repository.findById(id);

        if (sefer == null) {
            throw new RuntimeException("Sefer bulunamadı: " + id);
        }

        return sefer;
    }

    @Override
    public List<Sefer> listele() {
        return repository.findAll();
    }

    @Override
    public void sil(String id) {
        Sefer sefer = repository.findById(id);

        if (sefer == null) {
            throw new RuntimeException("Silinecek sefer bulunamadı!");
        }

        repository.delete(id);
    }

    @Override
    public List<Sefer> ara(String kalkis, String varis, String tarih) {
        return repository.search(kalkis, varis, tarih);
    }
}
