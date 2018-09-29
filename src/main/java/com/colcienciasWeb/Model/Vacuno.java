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
public class Vacuno {
    
    private int ID;
    private String raza;
    private Double peso;
    private int idPredio;
    private String descripcionPredio;
    private int idCategoria;
    private String descripcionCategoria;

    public Vacuno(int ID, String raza, Double peso, int idPredio, String descripcionPredio, int idCategoria, String descripcionCategoria) {
        this.ID = ID;
        this.raza = raza;
        this.peso = peso;
        this.idPredio = idPredio;
        this.descripcionPredio = descripcionPredio;
        this.idCategoria = idCategoria;
        this.descripcionCategoria = descripcionCategoria;
    }

    public Vacuno() {
    }
    
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public int getIdPredio() {
        return idPredio;
    }

    public void setIdPredio(int idPredio) {
        this.idPredio = idPredio;
    }

    public String getDescripcionPredio() {
        return descripcionPredio;
    }

    public void setDescripcionPredio(String descripcionPredio) {
        this.descripcionPredio = descripcionPredio;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getDescripcionCategoria() {
        return descripcionCategoria;
    }

    public void setDescripcionCategoria(String descripcionCategoria) {
        this.descripcionCategoria = descripcionCategoria;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + this.ID;
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
        final Vacuno other = (Vacuno) obj;
        if (this.ID != other.ID) {
            return false;
        }
        return true;
    }

   
    
    
}
