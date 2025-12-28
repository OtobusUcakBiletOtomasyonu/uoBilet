package com.ilgo.ticketapp.service.impl;

import com.ilgo.ticketapp.config.IdGenerator;
import com.ilgo.ticketapp.entity.Yolcu;
import com.ilgo.ticketapp.repository.YolcuRepository;
import com.ilgo.ticketapp.service.YolcuService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Yolcu servisinin gerçek işleyişi buradadır.
 * Repository ve Config buraya bağlanır.
 */
@Service
public class YolcuServiceImpl implements YolcuService {

    private final YolcuRepository repository;
    private final IdGenerator idGenerator;

    public YolcuServiceImpl(YolcuRepository repository, IdGenerator idGenerator) {
        this.repository = repository;
        this.idGenerator = idGenerator;
    }

    @Override
    public void kaydet(Yolcu yolcu) {
        if (yolcu.getAdSoyad() == null || yolcu.getAdSoyad().isEmpty()) {
            throw new RuntimeException("Yolcu adı boş olamaz!");
        }

        yolcu.setId(idGenerator.generateId());
        repository.save(yolcu);
    }

    @Override
    public Yolcu getir(String id) {
        Yolcu yolcu = repository.findById(id);
        if (yolcu == null) {
            throw new RuntimeException("Yolcu bulunamadı: " + id);
        }
        return yolcu;
    }

    @Override
    public List<Yolcu> listele() {
        return repository.findAll();
    }

    @Override
    public void sil(String id) {
        Yolcu yolcu = repository.findById(id);
        if (yolcu == null) {
            throw new RuntimeException("Silinecek yolcu yok: " + id);
        }
        repository.delete(id);
    }
}
