package com.ilgo.ticketapp.repository;

import com.ilgo.ticketapp.entity.Sefer;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Seferleri RAM üzerinde tutan manuel repository sınıfı.
 */
@Repository
public class SeferRepository {

    private final List<Sefer> seferler = new ArrayList<>();

    public void save(Sefer sefer) {
        seferler.add(sefer);
    }

    public Sefer findById(String id) {
        return seferler.stream()
                .filter(s -> s.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public List<Sefer> findAll() {
        return seferler;
    }

    public void delete(String id) {
        seferler.removeIf(s -> s.getId().equals(id));
    }

    public List<Sefer> search(String kalkis, String varis, String tarih) {
        return seferler.stream()
                .filter(s -> s.getKalkis().equalsIgnoreCase(kalkis) &&
                        s.getVaris().equalsIgnoreCase(varis) &&
                        s.getTarih().equalsIgnoreCase(tarih))
                .toList();
    }
}
