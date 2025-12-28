package com.ilgo.ticketapp.service;

import com.ilgo.ticketapp.entity.Rezervasyon;

import java.util.List;

public interface RezervasyonService {

    Rezervasyon rezervasyonOlustur(String durum);

    Rezervasyon rezervasyonGetir(int id);

    void rezervasyonIptal(int id);

    List<Rezervasyon> tumRezervasyonlar();

}

