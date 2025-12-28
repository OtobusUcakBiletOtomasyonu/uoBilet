package com.ticketapp.backend.dto.response;

import com.ticketapp.backend.domain.SeferType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class SeferDTO {
    private Long id;
    private String kalkisYeri;
    private String varisYeri;
    private LocalDateTime kalkisZamani;
    private double fiyat;
    private SeferType tip;
    private String firmaAdi;
    private String koltukDuzeni;
}
