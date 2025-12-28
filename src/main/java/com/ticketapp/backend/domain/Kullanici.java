package com.ticketapp.backend.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
@Table(name = "kullanici")
public abstract class Kullanici {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String sifre;

    @Column(nullable = false)
    private String ad;

    @Column(nullable = false)
    private String soyad;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Enumerated(EnumType.STRING)
    @Column(name = "cinsiyet")
    private Cinsiyet cinsiyet;
}
