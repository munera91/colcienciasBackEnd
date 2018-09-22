/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.colcienciasWeb.Model;

import java.util.Objects;

/**
 *
 * @author Usuario
 */
public class Finca {
    
    private int ID;
    private String nombre;
    private Double hectareas;
    private String direccion;
    private String nombrePropietario;
    private int idMunicipio;
    private String descripcionMunicipio; 

    public Finca() {
    }

    public Finca(int ID, String nombre, Double hectareas, String direccion, String nombrePropietario, int idMunicipio, String descripcionMunicipio) {
        this.ID = ID;
        this.nombre = nombre;
        this.hectareas = hectareas;
        this.direccion = direccion;
        this.nombrePropietario = nombrePropietario;
        this.idMunicipio = idMunicipio;
        this.descripcionMunicipio = descripcionMunicipio;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getHectareas() {
        return hectareas;
    }

    public void setHectareas(Double hectareas) {
        this.hectareas = hectareas;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getNombrePropietario() {
        return nombrePropietario;
    }

    public void setNombrePropietario(String nombrePropietario) {
        this.nombrePropietario = nombrePropietario;
    }

    public int getIdMunicipio() {
        return idMunicipio;
    }

    public void setIdMunicipio(int idMunicipio) {
        this.idMunicipio = idMunicipio;
    }

    public String getDescripcionMunicipio() {
        return descripcionMunicipio;
    }

    public void setDescripcionMunicipio(String descripcionMunicipio) {
        this.descripcionMunicipio = descripcionMunicipio;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + this.ID;
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
        final Finca other = (Finca) obj;
        if (this.ID != other.ID) {
            return false;
        }
        return true;
    }

    
   

    
}
