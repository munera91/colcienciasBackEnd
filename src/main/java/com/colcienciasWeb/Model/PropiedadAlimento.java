/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.colcienciasWeb.Model;

/**
 *
 * @author Aux Software
 */
public class PropiedadAlimento {

    private String tipoAlimento;
    private Double proteinaCruda;
    private Double proteinaDigestiva;
    private Double fibraCruda;
    private Double carbohidrato;
    private Double extractorEtero;

    public PropiedadAlimento() {
    }

    public PropiedadAlimento(String tipoAlimento, Double proteinaCruda, Double proteinaDigestiva, Double fibraCruda, Double carbohidrato, Double extractorEtero) {
        this.tipoAlimento = tipoAlimento;
        this.proteinaCruda = proteinaCruda;
        this.proteinaDigestiva = proteinaDigestiva;
        this.fibraCruda = fibraCruda;
        this.carbohidrato = carbohidrato;
        this.extractorEtero = extractorEtero;
    }

    public String getTipoAlimento() {
        return tipoAlimento;
    }

    public void setTipoAlimento(String tipoAlimento) {
        this.tipoAlimento = tipoAlimento;
    }

    public Double getProteinaCruda() {
        return proteinaCruda;
    }

    public void setProteinaCruda(Double proteinaCruda) {
        this.proteinaCruda = proteinaCruda;
    }

    public Double getProteinaDigestiva() {
        return proteinaDigestiva;
    }

    public void setProteinaDigestiva(Double proteinaDigestiva) {
        this.proteinaDigestiva = proteinaDigestiva;
    }

    public Double getFibraCruda() {
        return fibraCruda;
    }

    public void setFibraCruda(Double fibraCruda) {
        this.fibraCruda = fibraCruda;
    }

    public Double getCarbohidrato() {
        return carbohidrato;
    }

    public void setCarbohidrato(Double carbohidrato) {
        this.carbohidrato = carbohidrato;
    }

    public Double getExtractorEtero() {
        return extractorEtero;
    }

    public void setExtractorEtero(Double extractorEtero) {
        this.extractorEtero = extractorEtero;
    }
    
    
    
}
