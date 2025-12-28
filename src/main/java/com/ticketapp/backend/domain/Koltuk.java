package com.ticketapp.backend.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "koltuk")
public class Koltuk {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "sefer_id", nullable = false)
    private Sefer sefer;

    @Column(nullable = false)
    private String numara;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private KoltukDurumu durum = KoltukDurumu.BOS;
}
