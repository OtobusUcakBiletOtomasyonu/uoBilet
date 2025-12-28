package com.ticketapp.backend.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "otobus_seferi")
public class OtobusSeferi extends Sefer {
    private String peronNo;
    private String otobusPlaka;
    private String koltukDuzeni; // "2+1" or "2+2"
}
