package com.ilgo.ticketapp.service.impl;

import com.ilgo.ticketapp.config.IdGenerator;
import com.ilgo.ticketapp.entity.Koltuk;
import com.ilgo.ticketapp.repository.KoltukRepository;
import com.ilgo.ticketapp.service.KoltukService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KoltukServiceImpl implements KoltukService {

    private final KoltukRepository repository;
    private final IdGenerator idGenerator;

    public KoltukServiceImpl(KoltukRepository repository, IdGenerator idGenerator) {
        this.repository = repository;
        this.idGenerator = idGenerator;
    }

    @Override
    public void ekle(Koltuk koltuk) {
        koltuk.setId(idGenerator.generateId());
        if (koltuk.getDurum() == null || koltuk.getDurum().isEmpty()) {
            koltuk.setDurum("BOS"); // varsayılan durum
        }
        repository.save(koltuk);
    }

    @Override
    public Koltuk getir(String id) {
        Koltuk koltuk = repository.findById(id);
        if (koltuk == null) {
            throw new RuntimeException("Koltuk bulunamadı: " + id);
        }
        return koltuk;
    }

    @Override
    public List<Koltuk> seferKoltuklariniGetir(String seferId) {
        return repository.findBySeferId(seferId);
    }

    @Override
    public void durumDegistir(String id, String yeniDurum) {
        Koltuk koltuk = getir(id);

        if (!yeniDurum.equals("BOS") && !yeniDurum.equals("DOLU")) {
            throw new RuntimeException("Geçersiz durum: " + yeniDurum);
        }

        koltuk.setDurum(yeniDurum);
        repository.update(koltuk);
    }

    @Override
    public void sil(String id) {
        repository.delete(id);
    }
}
