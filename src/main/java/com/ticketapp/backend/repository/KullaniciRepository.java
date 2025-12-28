package com.ticketapp.backend.repository;

import com.ticketapp.backend.domain.Kullanici;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface KullaniciRepository extends JpaRepository<Kullanici, Long> {
    Optional<Kullanici> findByEmail(String email);

    boolean existsByEmail(String email);
}
