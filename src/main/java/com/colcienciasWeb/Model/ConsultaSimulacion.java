/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.colcienciasWeb.Model;

import java.util.Date;

/**
 *
 * @author Usuario
 */
public class ConsultaSimulacion {
    
    private int idFinca;
    private java.util.Date fechaInicio;
    private java.util.Date fechaFin;

    public ConsultaSimulacion() {
        
    }

    public ConsultaSimulacion(int idFinca, Date fechaInicio, Date fechaFin) {
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

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
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
