package com.ticketapp.backend.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "odeme")
public class Odeme {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @com.fasterxml.jackson.annotation.JsonIgnore
    @OneToOne
    @JoinColumn(name = "bilet_id", nullable = false)
    private Bilet bilet;

    @Column(nullable = false)
    private double tutar;

    @Column(nullable = false)
    private LocalDateTime odemeTarihi;

    // Strategy Pattern ile odeme tipi belirlenecek
    @Column(nullable = false)
    private String odemeTipi; // KREDI_KARTI, HAVALE, BAKIYE
}
