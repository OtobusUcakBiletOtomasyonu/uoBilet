package com.ilgo.ticketapp.service;

import com.ilgo.ticketapp.entity.Firma;

import java.util.List;

public interface FirmaService {

    Firma firmaEkle(String ad);

    Firma firmaGetir(int id);

    List<Firma> tumFirmalar();

}

