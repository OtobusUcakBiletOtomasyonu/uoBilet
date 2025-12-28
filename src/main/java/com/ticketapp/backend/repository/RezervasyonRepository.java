package com.ticketapp.backend.repository;

import com.ticketapp.backend.domain.Rezervasyon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RezervasyonRepository extends JpaRepository<Rezervasyon, Long> {
    List<Rezervasyon> findByYolcuId(Long yolcuId);
}
