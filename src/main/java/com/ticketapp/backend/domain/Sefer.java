package com.ticketapp.backend.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
@Table(name = "sefer")
public abstract class Sefer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "firma_id", nullable = false)
    private Firma firma;

    @Column(nullable = false)
    private String kalkisYeri;

    @Column(nullable = false)
    private String varisYeri;

    @Column(nullable = false)
    private LocalDateTime kalkisZamani;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SeferType tip;

    @Column(nullable = false)
    private double fiyat;
}
