package com.ilgo.ticketapp.entity;

public class FirmaCalisani extends Kullanici {

    private Firma calistigiFirma;

    public FirmaCalisani() {

        super();

    }

    public FirmaCalisani(String id, String adSoyad, String email, String sifre,

            Firma calistigiFirma) {

        super(id, adSoyad, email, sifre);

        this.calistigiFirma = calistigiFirma;

    }

    public Firma getCalistigiFirma() {

        return calistigiFirma;

    }

    public void setCalistigiFirma(Firma calistigiFirma) {

        this.calistigiFirma = calistigiFirma;

    }

}
