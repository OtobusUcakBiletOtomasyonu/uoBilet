package com.ticketapp.backend.repository;

import com.ticketapp.backend.domain.Odeme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OdemeRepository extends JpaRepository<Odeme, Long> {
}
