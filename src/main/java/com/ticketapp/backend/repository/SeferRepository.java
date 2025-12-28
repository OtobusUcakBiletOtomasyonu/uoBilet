package com.ticketapp.backend.repository;

import com.ticketapp.backend.domain.Sefer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface SeferRepository extends JpaRepository<Sefer, Long> {
    List<Sefer> findByKalkisYeriIgnoreCaseAndVarisYeriIgnoreCaseAndKalkisZamaniBetween(String kalkisYeri,
            String varisYeri,
            LocalDateTime baslangic, LocalDateTime bitis);
}
