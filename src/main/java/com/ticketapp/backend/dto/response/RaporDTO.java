package com.ticketapp.backend.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RaporDTO {
    private int toplamBiletSayisi;
    private double toplamCiro;
    // Detayli kirilim eklenebilir
}
