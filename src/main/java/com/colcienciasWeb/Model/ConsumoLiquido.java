/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.colcienciasWeb.Model;

/**
 *dd
 * @author Aux Software
 */
public class ConsumoLiquido {

    private String categoriaVacuno;
    private Double cantidad;

    public ConsumoLiquido() {
    }

    public ConsumoLiquido(String categoriaVacuno, Double cantidad) {
        this.categoriaVacuno = categoriaVacuno;
        this.cantidad = cantidad;
    }

    public String getCategoriaVacuno() {
        return categoriaVacuno;
    }

    public void setCategoriaVacuno(String categoriaVacuno) {
        this.categoriaVacuno = categoriaVacuno;
    }

    public Double getCantidad() {
        return cantidad;
    }

    public void setCantidad(Double cantidad) {
        this.cantidad = cantidad;
    }
    
    
}
