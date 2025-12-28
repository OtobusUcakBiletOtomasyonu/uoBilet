package com.ilgo.ticketapp.repository;

import com.ilgo.ticketapp.entity.Koltuk;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Seferlerin koltuklarını yöneten manuel repository.
 */
@Repository
public class KoltukRepository {

    private final List<Koltuk> koltuklar = new ArrayList<>();

    public void save(Koltuk koltuk) {
        koltuklar.add(koltuk);
    }

    public Koltuk findById(String id) {
        return koltuklar.stream()
                .filter(k -> k.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public List<Koltuk> findBySeferId(String seferId) {
        return koltuklar.stream()
                .filter(k -> k.getSeferId().equals(seferId))
                .toList();
    }

    public void update(Koltuk koltuk) {
        delete(koltuk.getId());
        koltuklar.add(koltuk);
    }

    public void delete(String id) {
        koltuklar.removeIf(k -> k.getId().equals(id));
    }
}
