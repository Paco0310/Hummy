package com.vantuit.hummy.models;

public class Usuario {
    private String ID,CORREO,NOMBRE,DIRECCION,TELEFONO,FOTO;

    public Usuario() {

    }

    public Usuario(String ID, String CORREO, String NOMBRE, String DIRECCION, String TELEFONO, String FOTO) {
        this.ID = ID;
        this.CORREO = CORREO;
        this.NOMBRE = NOMBRE;
        this.DIRECCION = DIRECCION;
        this.TELEFONO = TELEFONO;
        this.FOTO = FOTO;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getCORREO() {
        return CORREO;
    }

    public void setCORREO(String CORREO) {
        this.CORREO = CORREO;
    }

    public String getNOMBRE() {
        return NOMBRE;
    }

    public void setNOMBRE(String NOMBRE) {
        this.NOMBRE = NOMBRE;
    }

    public String getDIRECCION() {
        return DIRECCION;
    }

    public void setDIRECCION(String DIRECCION) {
        this.DIRECCION = DIRECCION;
    }

    public String getTELEFONO() {
        return TELEFONO;
    }

    public void setTELEFONO(String TELEFONO) {
        this.TELEFONO = TELEFONO;
    }

    public String getFOTO() {
        return FOTO;
    }

    public void setFOTO(String FOTO) {
        this.FOTO = FOTO;
    }
}
