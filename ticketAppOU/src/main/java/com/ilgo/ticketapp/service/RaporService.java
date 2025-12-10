package com.ilgo.ticketapp.service;

import com.ilgo.ticketapp.entity.Rapor;

import java.util.List;

public interface RaporService {

    Rapor raporOlustur(String icerik);

    List<Rapor> tumRaporlar();

    Rapor raporGetir(int id);

}

