package com.ticketapp.backend.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "rezervasyon")
public class Rezervasyon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "yolcu_id", nullable = false)
    private Yolcu yolcu;

    @OneToOne
    @JoinColumn(name = "koltuk_id", nullable = false, unique = true)
    private Koltuk koltuk;

    @Column(nullable = false)
    private LocalDateTime rezervasyonTarihi;

    @Column(nullable = false)
    private LocalDateTime sonGecerlilikTarihi;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RezervasyonDurumu durum = RezervasyonDurumu.BEKLEMEDE;
}
