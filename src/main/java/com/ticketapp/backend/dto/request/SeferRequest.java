package com.ticketapp.backend.dto.request;

import com.ticketapp.backend.domain.SeferType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class SeferRequest {
    private String kalkisYeri;
    private String varisYeri;
    private LocalDateTime kalkisZamani;
    private double fiyat;
    private SeferType tip; // OTOBUS, UCAK
    private Long firmaId;
    private String koltukDuzeni; // "2+1" or "2+2"
}
