package com.ticketapp.backend.dto.response;

import com.ticketapp.backend.domain.KoltukDurumu;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class KoltukDurumDTO {
    private String numara;
    private KoltukDurumu durum;
    private String cinsiyet; // KADIN, ERKEK, BELIRTILMEMIS
}
