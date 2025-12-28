package com.ilgo.ticketapp.repository;

import com.ilgo.ticketapp.entity.Yonetici;

import java.util.ArrayList;

import java.util.List;

public class YoneticiRepository {

    private List<Yonetici> yoneticiler = new ArrayList<>();

    public void save(Yonetici y) {

        yoneticiler.add(y);

    }

    public Yonetici findById(String id) {

        for (Yonetici y : yoneticiler) {

            if (y.getId().equals(id)) {

                return y;

            }

        }

        return null;

    }

    public List<Yonetici> findAll() {

        return yoneticiler;

    }

    public void delete(String id) {

        yoneticiler.removeIf(y -> y.getId().equals(id));

    }

}
