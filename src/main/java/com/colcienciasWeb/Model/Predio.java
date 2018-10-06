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
    int idTipoAlimentacion;
    String descTipoAlimentacion;
    int idTipoTerreno;
    String descTipoTerreno;
    int idFinca;
    String descFinca;
    int cantidadMax;

    public Predio() {
    }

    public Predio(int ID, String descripcion, int idTipoAlimentacion, String descTipoAlimentacion, int idTipoTerreno, String descTipoTerreno, int idFinca,
            String descFinca, int cantidadMax) {
        this.ID = ID;
        this.descripcion = descripcion;
        this.idTipoAlimentacion = idTipoAlimentacion;
        this.descTipoAlimentacion = descTipoAlimentacion;
        this.idTipoTerreno = idTipoTerreno;
        this.descTipoTerreno = descTipoTerreno;
        this.idFinca = idFinca;
        this.descFinca = descFinca;
        this.cantidadMax = cantidadMax;
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

    public int getIdTipoAlimentacion() {
        return idTipoAlimentacion;
    }

    public void setIdTipoAlimentacion(int idTipoAlimentacion) {
        this.idTipoAlimentacion = idTipoAlimentacion;
    }

    public String getDescTipoAlimentacion() {
        return descTipoAlimentacion;
    }

    public void setDescTipoAlimentacion(String descTipoAlimentacion) {
        this.descTipoAlimentacion = descTipoAlimentacion;
    }

    public int getIdTipoTerreno() {
        return idTipoTerreno;
    }

    public void setIdTipoTerreno(int idTipoTerreno) {
        this.idTipoTerreno = idTipoTerreno;
    }

    public String getDescTipoTerreno() {
        return descTipoTerreno;
    }

    public void setDescTipoTerreno(String descTipoTerreno) {
        this.descTipoTerreno = descTipoTerreno;
    }

    public int getIdFinca() {
        return idFinca;
    }

    public void setIdFinca(int idFinca) {
        this.idFinca = idFinca;
    }

    public String getDescFinca() {
        return descFinca;
    }

    public void setDescFinca(String descFinca) {
        this.descFinca = descFinca;
    }

    public int getCantidadMax() {
        return cantidadMax;
    }

    public void setCantidadMax(int cantidadMax) {
        this.cantidadMax = cantidadMax;
    }

    
    

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 41 * hash + this.ID;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Predio other = (Predio) obj;
        if (this.ID != other.ID) {
            return false;
        }
        return true;
    }
    
   

}
