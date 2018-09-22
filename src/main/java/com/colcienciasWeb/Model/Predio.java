/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.colcienciasWeb.Model;

/**
 *
 * @author Usuario
 */
public class Predio {

    int ID;
    String descripcion;
    int tipoAlimentacion;
    int tipoTerreno;
    int finca;

    public Predio(int ID, String descripcion, int tipoAlimentacion, int tipoTerreno, int finca) {
        this.ID = ID;
        this.descripcion = descripcion;
        this.tipoAlimentacion = tipoAlimentacion;
        this.tipoTerreno = tipoTerreno;
        this.finca = finca;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getTipoAlimentacion() {
        return tipoAlimentacion;
    }

    public void setTipoAlimentacion(int tipoAlimentacion) {
        this.tipoAlimentacion = tipoAlimentacion;
    }

    public int getTipoTerreno() {
        return tipoTerreno;
    }

    public void setTipoTerreno(int tipoTerreno) {
        this.tipoTerreno = tipoTerreno;
    }

    public int getFinca() {
        return finca;
    }

    public void setFinca(int finca) {
        this.finca = finca;
    }

}
