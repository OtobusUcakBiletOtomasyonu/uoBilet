package com.ticketapp.backend.repository;

import com.ticketapp.backend.domain.Koltuk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface KoltukRepository extends JpaRepository<Koltuk, Long> {
    List<Koltuk> findBySeferId(Long seferId);

    Optional<Koltuk> findBySeferIdAndNumara(Long seferId, String numara);
}
