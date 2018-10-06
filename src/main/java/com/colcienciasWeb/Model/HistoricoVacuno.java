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
public class HistoricoVacuno {
    
    int idVacuno;
    int mes;
    int anio;
    Double peso;
    String idTipoAlimento;
    String descripcionTipoAlimento;
    int idPredio;
    String descripcionPredio;

    public HistoricoVacuno(int idVacuno, int mes, int anio, Double peso, String idTipoAlimento, String descripcionTipoAlimento, int idPredio, String descripcionPredio) {
        this.idVacuno = idVacuno;
        this.mes = mes;
        this.anio = anio;
        this.peso = peso;
        this.idTipoAlimento = idTipoAlimento;
        this.descripcionTipoAlimento = descripcionTipoAlimento;
        this.idPredio = idPredio;
        this.descripcionPredio = descripcionPredio;
    }

    public HistoricoVacuno() {
    }

    public int getIdVacuno() {
        return idVacuno;
    }

    public void setIdVacuno(int idVacuno) {
        this.idVacuno = idVacuno;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public String getIdTipoAlimento() {
        return idTipoAlimento;
    }

    public void setIdTipoAlimento(String idTipoAlimento) {
        this.idTipoAlimento = idTipoAlimento;
    }

    public String getDescripcionTipoAlimento() {
        return descripcionTipoAlimento;
    }

    public void setDescripcionTipoAlimento(String descripcionTipoAlimento) {
        this.descripcionTipoAlimento = descripcionTipoAlimento;
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
        final HistoricoVacuno other = (HistoricoVacuno) obj;
        if (this.idVacuno != other.idVacuno) {
            return false;
        }
        return true;
    }
    
}
