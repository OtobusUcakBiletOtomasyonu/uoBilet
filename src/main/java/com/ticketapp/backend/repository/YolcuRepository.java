package com.ticketapp.backend.repository;

import com.ticketapp.backend.domain.Yolcu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface YolcuRepository extends JpaRepository<Yolcu, Long> {
}
