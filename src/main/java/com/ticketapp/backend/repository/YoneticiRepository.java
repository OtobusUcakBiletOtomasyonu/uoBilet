package com.ticketapp.backend.repository;

import com.ticketapp.backend.domain.Yonetici;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface YoneticiRepository extends JpaRepository<Yonetici, Long> {
}
