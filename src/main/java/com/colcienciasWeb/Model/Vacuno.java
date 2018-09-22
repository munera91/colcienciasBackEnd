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
    
    private String ID;
    private String raza;
    private Integer numeroPartos;
    private Double peso;
    private String finca;

    public Vacuno(String ID, String raza, Integer numeroPartos, Double peso, String finca) {
        this.ID = ID;
        this.raza = raza;
        this.numeroPartos = numeroPartos;
        this.peso = peso;
        this.finca = finca;
    }

    public Vacuno() {
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public Integer getNumeroPartos() {
        return numeroPartos;
    }

    public void setNumeroPartos(Integer numeroPartos) {
        this.numeroPartos = numeroPartos;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public String getFinca() {
        return finca;
    }

    public void setFinca(String finca) {
        this.finca = finca;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + Objects.hashCode(this.ID);
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
        if (!Objects.equals(this.ID, other.ID)) {
            return false;
        }
        return true;
    }

    
    
    
}
