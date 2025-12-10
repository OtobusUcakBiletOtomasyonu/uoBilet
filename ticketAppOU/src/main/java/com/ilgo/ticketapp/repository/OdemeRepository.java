package com.ilgo.ticketapp.repository;

import com.ilgo.ticketapp.entity.Odeme;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class OdemeRepository {

    private final List<Odeme> odemeler = new ArrayList<>();

    public void save(Odeme odeme) {
        odemeler.add(odeme);
    }

    public Odeme findById(String id) {
        return odemeler.stream()
                .filter(o -> o.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public List<Odeme> findAll() {
        return odemeler;
    }
}
