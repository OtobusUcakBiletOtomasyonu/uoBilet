package com.ticketapp.backend.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BiletSatinAlRequest {
    private Long yolcuId;
    private Long koltukId;
    private String odemeStrategy; // KREDI_KARTI, HAVALE, BAKIYE
    private double tutar;
}
