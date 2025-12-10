package com.ilgo.ticketapp.repository;

import com.ilgo.ticketapp.entity.Yolcu;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Yolcuları hafızada tutan repository.
 * Hazır JPA yok, her şeyi elle yazıyoruz.
 */
@Repository
public class YolcuRepository {

    private final List<Yolcu> yolcular = new ArrayList<>();

    public void save(Yolcu yolcu) {
        yolcular.add(yolcu);
    }

    public Yolcu findById(String id) {
        return yolcular.stream()
                .filter(y -> y.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public List<Yolcu> findAll() {
        return yolcular;
    }

    public void delete(String id) {
        yolcular.removeIf(y -> y.getId().equals(id));
    }
}
