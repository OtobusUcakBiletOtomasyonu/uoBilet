package com.ticketapp.backend.dto.response;

import com.ticketapp.backend.domain.KoltukDurumu;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class KoltukDTO {
    private Long id;
    private String numara;
    private KoltukDurumu durum;
    private Long seferId;
}
