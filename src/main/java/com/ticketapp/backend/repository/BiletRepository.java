package com.ticketapp.backend.repository;

import com.ticketapp.backend.domain.Bilet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BiletRepository extends JpaRepository<Bilet, Long> {
    List<Bilet> findByYolcuId(Long yolcuId);

    List<Bilet> findByKoltuk_Sefer_Id(Long seferId);
}
