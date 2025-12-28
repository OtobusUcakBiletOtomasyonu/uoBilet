package com.ticketapp.backend.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "bilet")
public class Bilet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "yolcu_id", nullable = false)
    private Yolcu yolcu;

    @OneToOne
    @JoinColumn(name = "koltuk_id", nullable = false, unique = true)
    private Koltuk koltuk;

    @OneToOne(mappedBy = "bilet", cascade = CascadeType.ALL)
    private Odeme odeme;

    @Column(nullable = false)
    private LocalDateTime satinAlmaTarihi;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BiletDurumu durum = BiletDurumu.AKTIF;

    @Transient
    @com.fasterxml.jackson.annotation.JsonIgnore
    private com.ticketapp.backend.pattern.state.BiletState state;

    @PostLoad
    public void init() {
        // DB'den okunduktan sonra Enum'a gore State'i yukle
        switch (this.durum) {
            case AKTIF:
                this.state = new com.ticketapp.backend.pattern.state.AktifBiletState();
                break;
            case IPTAL:
                this.state = new com.ticketapp.backend.pattern.state.IptalBiletState();
                break;
        }
    }

    public void iptalEt() {
        if (this.state == null)
            init();
        this.state.iptalEt(this);
    }

    // State class'indan cagirilir
    public void setState(com.ticketapp.backend.pattern.state.BiletState state) {
        this.state = state;
        // Enum senkronizasyonu
        if (state instanceof com.ticketapp.backend.pattern.state.AktifBiletState) {
            this.durum = BiletDurumu.AKTIF;
        } else if (state instanceof com.ticketapp.backend.pattern.state.IptalBiletState) {
            this.durum = BiletDurumu.IPTAL;
        }
    }
}
