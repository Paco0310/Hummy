package com.vantuit.hummy.models;

import androidx.annotation.NonNull;

public class Domicilio {
    private String CALLE, NUM_INTERNO,NUM_EXTERNO,COLONIA,CP,REFERENCIA,ALIAS,ID_USUARIO;

    public Domicilio() {

    }

    public Domicilio(String CALLE, String NUM_INTERNO, String NUM_EXTERNO, String COLONIA, String CP, String REFERENCIA, String ALIAS, String ID_USUARIO) {
        this.CALLE = CALLE;
        this.NUM_INTERNO = NUM_INTERNO;
        this.NUM_EXTERNO = NUM_EXTERNO;
        this.COLONIA = COLONIA;
        this.CP = CP;
        this.REFERENCIA = REFERENCIA;
        this.ALIAS = ALIAS;
        this.ID_USUARIO = ID_USUARIO;
    }

    public String getCALLE() {
        return CALLE;
    }

    public void setCALLE(String CALLE) {
        this.CALLE = CALLE;
    }

    public String getNUM_INTERNO() {
        return NUM_INTERNO;
    }

    public void setNUM_INTERNO(String NUM_INTERNO) {
        this.NUM_INTERNO = NUM_INTERNO;
    }

    public String getNUM_EXTERNO() {
        return NUM_EXTERNO;
    }

    public void setNUM_EXTERNO(String NUM_EXTERNO) {
        this.NUM_EXTERNO = NUM_EXTERNO;
    }

    public String getCOLONIA() {
        return COLONIA;
    }

    public void setCOLONIA(String COLONIA) {
        this.COLONIA = COLONIA;
    }

    public String getCP() {
        return CP;
    }

    public void setCP(String CP) {
        this.CP = CP;
    }

    public String getREFERENCIA() {
        return REFERENCIA;
    }

    public void setREFERENCIA(String REFERENCIA) {
        this.REFERENCIA = REFERENCIA;
    }

    public String getALIAS() {
        return ALIAS;
    }

    public void setALIAS(String ALIAS) {
        this.ALIAS = ALIAS;
    }

    public String getID_USUARIO() {
        return ID_USUARIO;
    }

    public void setID_USUARIO(String ID_USUARIO) {
        this.ID_USUARIO = ID_USUARIO;
    }
}
