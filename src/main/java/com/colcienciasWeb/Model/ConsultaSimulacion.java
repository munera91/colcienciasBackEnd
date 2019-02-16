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
public class ConsultaSimulacion {
    
    private int idFinca;
    private Fecha fechaInicio;
    private Fecha fechaFin;

    public ConsultaSimulacion() {
        
    }

    public ConsultaSimulacion(int idFinca, Fecha fechaInicio, Fecha fechaFin) {
        this.idFinca = idFinca;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    public int getIdFinca() {
        return idFinca;
    }

    public void setIdFinca(int idFinca) {
        this.idFinca = idFinca;
    }

    public Fecha getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Fecha fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Fecha getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Fecha fechaFin) {
        this.fechaFin = fechaFin;
    }

    

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + this.idFinca;
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
        final ConsultaSimulacion other = (ConsultaSimulacion) obj;
        if (this.idFinca != other.idFinca) {
            return false;
        }
        return true;
    }
    
    
    
}
