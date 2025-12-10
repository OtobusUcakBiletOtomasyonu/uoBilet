package com.ilgo.ticketapp.repository;

import com.ilgo.ticketapp.entity.FirmaCalisani;

import java.util.ArrayList;

import java.util.List;

public class FirmaCalisaniRepository {

    private List<FirmaCalisani> calisanlar = new ArrayList<>();

    public void save(FirmaCalisani fc) {

        calisanlar.add(fc);

    }

    public FirmaCalisani findById(String id) {

        for (FirmaCalisani fc : calisanlar) {

            if (fc.getId().equals(id)) {

                return fc;

            }

        }

        return null;

    }

    public List<FirmaCalisani> findAll() {

        return calisanlar;

    }

    public void delete(String id) {

        calisanlar.removeIf(fc -> fc.getId().equals(id));

    }

}
