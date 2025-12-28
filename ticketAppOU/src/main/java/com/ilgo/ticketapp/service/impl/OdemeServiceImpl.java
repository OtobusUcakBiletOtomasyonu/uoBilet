package com.ilgo.ticketapp.service.impl;

import com.ilgo.ticketapp.config.IdGenerator;
import com.ilgo.ticketapp.entity.Odeme;
import com.ilgo.ticketapp.factory.odeme.HavaleOdemeFactory;
import com.ilgo.ticketapp.factory.odeme.KrediKartiOdemeFactory;
import com.ilgo.ticketapp.factory.odeme.OdemeFactory;
import com.ilgo.ticketapp.repository.OdemeRepository;
import com.ilgo.ticketapp.service.OdemeService;
import org.springframework.stereotype.Service;

@Service
public class OdemeServiceImpl implements OdemeService {

    private final OdemeRepository repository;
    private final IdGenerator idGenerator;

    public OdemeServiceImpl(OdemeRepository repository, IdGenerator idGenerator) {
        this.repository = repository;
        this.idGenerator = idGenerator;
    }

    @Override
    public Odeme odemeYap(String biletId, double tutar, String odemeTuru) {

        OdemeFactory factory;

        switch (odemeTuru.toUpperCase()) {
            case "KREDI_KARTI":
                factory = new KrediKartiOdemeFactory();
                break;

            case "HAVALE":
                factory = new HavaleOdemeFactory();
                break;

            default:
                throw new RuntimeException("Geçersiz ödeme türü: " + odemeTuru);
        }

        Odeme odeme = factory.odemeOlustur(tutar, biletId);
        odeme.setId(idGenerator.generateId());

        repository.save(odeme);

        return odeme;
    }

    @Override
    public Odeme getir(String id) {
        return repository.findById(id);
    }
}
