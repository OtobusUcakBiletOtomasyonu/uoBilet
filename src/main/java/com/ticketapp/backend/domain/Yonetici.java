package com.ticketapp.backend.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "yonetici")
public class Yonetici extends Kullanici {
    private boolean aktif = true;
}
