package com.ticketapp.backend.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "ucak_seferi")
public class UcakSeferi extends Sefer {
    private String kapiNo;
    private String ucusKodu;
}
