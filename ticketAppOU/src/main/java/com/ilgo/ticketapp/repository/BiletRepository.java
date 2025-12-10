package com.ilgo.ticketapp.repository;

import com.ilgo.ticketapp.entity.Bilet;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class BiletRepository {

    private final List<Bilet> biletler = new ArrayList<>();

    public void save(Bilet bilet) {
        biletler.add(bilet);
    }

    public Bilet findById(String id) {
        return biletler.stream()
                .filter(b -> b.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public List<Bilet> findAll() {
        return biletler;
    }

    public void delete(String id) {
        biletler.removeIf(b -> b.getId().equals(id));
    }

    // Added for compatibility with previous code if needed, but user didn't
    // explicitly ask for it here.
    // However, BiletServiceImpl might need findByYolcu if I added it before.
    // The user's provided BiletRepository code DOES NOT have findByYolcu.
    // But BiletServiceImpl provided by user DOES NOT use findByYolcu.
    // Wait, previous BiletServiceImpl DID use findByYolcu.
    // The user provided a NEW BiletServiceImpl. Let's check if it uses findByYolcu.
    // User's new BiletServiceImpl:
    // listele() -> findAll()
    // getir() -> findById()
    // biletIptal() -> findById(), delete()
    // biletAl() -> save()
    // It does NOT seem to use findByYolcu.
    // BUT, I should probably keep it if I added it before for other reasons, or
    // just stick to the user's code.
    // I'll stick to the user's code for now, but I'll add findByYolcu if I see
    // compilation errors later.
    // Actually, looking at my previous context, I *did* update BiletRepository to
    // have findByYolcu.
    // If I overwrite it now, I might break other things if they rely on it.
    // The user's request says "1️⃣ BiletRepository ... Biletleri RAM’de tutan
    // yapı".
    // I should probably include findByYolcu just in case, as it's a useful method
    // and was there before.

    public List<Bilet> findByYolcu(String yolcuId) {
        return biletler.stream()
                .filter(b -> b.getYolcu().getId().equals(yolcuId))
                .toList();
    }
}
