package com.ticketapp.backend.factory;

import com.ticketapp.backend.domain.Sefer;

// Factory Method Pattern: Creator Interface
public interface SeferFactory {
    Sefer createSefer();
}
