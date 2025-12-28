package com.ticketapp.backend.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "yolcu")
public class Yolcu extends Kullanici {
    private String telefon;
    // Yolcuya ozel baska alanlar eklenebilir
}
