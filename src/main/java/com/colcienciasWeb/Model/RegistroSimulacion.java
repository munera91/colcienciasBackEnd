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
public class RegistroSimulacion {

    private int idSimulacion;
    private Fecha fechaSimulacion;
    private String descPredio;
    private int numVacunos;
    private String descTipoAlimento;

    public RegistroSimulacion() {
    }

    public RegistroSimulacion(int idSimulacion, Fecha fechaSimulacion, String descPredio, int numVacunos, String descTipoAlimento) {
        this.idSimulacion = idSimulacion;
        this.fechaSimulacion = fechaSimulacion;
        this.descPredio = descPredio;
        this.numVacunos = numVacunos;
        this.descTipoAlimento = descTipoAlimento;
    }

    public int getIdSimulacion() {
        return idSimulacion;
    }

    public void setIdSimulacion(int idSimulacion) {
        this.idSimulacion = idSimulacion;
    }

    public Fecha getFechaSimulacion() {
        return fechaSimulacion;
    }

    public void setFechaSimulacion(Fecha fechaSimulacion) {
        this.fechaSimulacion = fechaSimulacion;
    }

    public String getDescPredio() {
        return descPredio;
    }

    public void setDescPredio(String descPredio) {
        this.descPredio = descPredio;
    }

    public int getNumVacunos() {
        return numVacunos;
    }

    public void setNumVacunos(int numVacunos) {
        this.numVacunos = numVacunos;
    }

    public String getDescTipoAlimento() {
        return descTipoAlimento;
    }

    public void setDescTipoAlimento(String descTipoAlimento) {
        this.descTipoAlimento = descTipoAlimento;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + this.idSimulacion;
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
        final RegistroSimulacion other = (RegistroSimulacion) obj;
        if (this.idSimulacion != other.idSimulacion) {
            return false;
        }
        return true;
    }

}
