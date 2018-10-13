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
public class Simulacion {
    Integer idpredio;
    String descripcionPredio;
    String descTipoTerreno;
    Integer totalMamones;
    Integer totalDestetados;
    Integer totalNovillos;
    Integer totalVacas;
    Integer cantidadBovinos;
    String tipoAlimento;
    double proteinaCruda;
    double proteinaDigestiva;
    double fibraCruda;
    double carbohidrato;
    double extractorEtero;
    double consAguaMamones;
    double consAguaDestetados;
    double consAguaNovillos;
    double consAguaVacas;
    double consAlimentoMamones;
    double consAlimentDestetados;
    double consAlimentoNovillos;
    double consAlimentoVacas;
    double orinaMamones;
    double orinaDestetados;
    double orinaNovillos;
    double orinaVacas;
    double contaminacionMamones;
    double contaminacionDestetados;
    double contaminacionNovillos;
    double contaminacionVacas;

    public Simulacion() {
    }

    public Simulacion(Integer idpredio, String descripcionPredio, String descTipoTerreno, Integer totalMamones, Integer totalDestetados, Integer totalNovillos, Integer totalVacas, Integer cantidadBovinos, String tipoAlimento, double proteinaCruda, double proteinaDigestiva, double fibraCruda, double carbohidrato, double extractorEtero, double consAguaMamones, double consAguaDestetados, double consAguaNovillos, double consAguaVacas, double consAlimentoMamones, double consAlimentDestetados, double consAlimentoNovillos, double consAlimentoVacas, double orinaMamones, double orinaDestetados, double orinaNovillos, double orinaVacas, double contaminacionMamones, double contaminacionDestetados, double contaminacionNovillos, double contaminacionVacas) {
        this.idpredio = idpredio;
        this.descripcionPredio = descripcionPredio;
        this.descTipoTerreno = descTipoTerreno;
        this.totalMamones = totalMamones;
        this.totalDestetados = totalDestetados;
        this.totalNovillos = totalNovillos;
        this.totalVacas = totalVacas;
        this.cantidadBovinos = cantidadBovinos;
        this.tipoAlimento = tipoAlimento;
        this.proteinaCruda = proteinaCruda;
        this.proteinaDigestiva = proteinaDigestiva;
        this.fibraCruda = fibraCruda;
        this.carbohidrato = carbohidrato;
        this.extractorEtero = extractorEtero;
        this.consAguaMamones = consAguaMamones;
        this.consAguaDestetados = consAguaDestetados;
        this.consAguaNovillos = consAguaNovillos;
        this.consAguaVacas = consAguaVacas;
        this.consAlimentoMamones = consAlimentoMamones;
        this.consAlimentDestetados = consAlimentDestetados;
        this.consAlimentoNovillos = consAlimentoNovillos;
        this.consAlimentoVacas = consAlimentoVacas;
        this.orinaMamones = orinaMamones;
        this.orinaDestetados = orinaDestetados;
        this.orinaNovillos = orinaNovillos;
        this.orinaVacas = orinaVacas;
        this.contaminacionMamones = contaminacionMamones;
        this.contaminacionDestetados = contaminacionDestetados;
        this.contaminacionNovillos = contaminacionNovillos;
        this.contaminacionVacas = contaminacionVacas;
    }

   

    public Integer getIdpredio() {
        return idpredio;
    }

    public void setIdpredio(Integer idpredio) {
        this.idpredio = idpredio;
    }

    public String getDescripcionPredio() {
        return descripcionPredio;
    }

    public void setDescripcionPredio(String descripcionPredio) {
        this.descripcionPredio = descripcionPredio;
    }

    public String getDescTipoTerreno() {
        return descTipoTerreno;
    }

    public void setDescTipoTerreno(String descTipoTerreno) {
        this.descTipoTerreno = descTipoTerreno;
    }

    public Integer getTotalMamones() {
        return totalMamones;
    }

    public void setTotalMamones(Integer totalMamones) {
        this.totalMamones = totalMamones;
    }

    public Integer getTotalNovillos() {
        return totalNovillos;
    }

    public void setTotalNovillos(Integer totalNovillos) {
        this.totalNovillos = totalNovillos;
    }

    public Integer getTotalVacas() {
        return totalVacas;
    }

    public void setTotalVacas(Integer totalVacas) {
        this.totalVacas = totalVacas;
    }

    public Integer getCantidadBovinos() {
        return cantidadBovinos;
    }

    public void setCantidadBovinos(Integer cantidadBovinos) {
        this.cantidadBovinos = cantidadBovinos;
    }

    public String getTipoAlimento() {
        return tipoAlimento;
    }

    public void setTipoAlimento(String tipoAlimento) {
        this.tipoAlimento = tipoAlimento;
    }

    public double getProteinaCruda() {
        return proteinaCruda;
    }

    public void setProteinaCruda(double proteinaCruda) {
        this.proteinaCruda = proteinaCruda;
    }

    public double getProteinaDigestiva() {
        return proteinaDigestiva;
    }

    public void setProteinaDigestiva(double proteinaDigestiva) {
        this.proteinaDigestiva = proteinaDigestiva;
    }

    public double getFibraCruda() {
        return fibraCruda;
    }

    public void setFibraCruda(double fibraCruda) {
        this.fibraCruda = fibraCruda;
    }

    public double getCarbohidrato() {
        return carbohidrato;
    }

    public void setCarbohidrato(double carbohidrato) {
        this.carbohidrato = carbohidrato;
    }

    public double getExtractorEtero() {
        return extractorEtero;
    }

    public void setExtractorEtero(double extractorEtero) {
        this.extractorEtero = extractorEtero;
    }

    public double getConsAguaMamones() {
        return consAguaMamones;
    }

    public void setConsAguaMamones(double consAguaMamones) {
        this.consAguaMamones = consAguaMamones;
    }

    public double getConsAguaNovillos() {
        return consAguaNovillos;
    }

    public void setConsAguaNovillos(double consAguaNovillos) {
        this.consAguaNovillos = consAguaNovillos;
    }

    public double getConsAguaVacas() {
        return consAguaVacas;
    }

    public void setConsAguaVacas(double consAguaVacas) {
        this.consAguaVacas = consAguaVacas;
    }

    public double getConsAlimentoMamones() {
        return consAlimentoMamones;
    }

    public void setConsAlimentoMamones(double consAlimentoMamones) {
        this.consAlimentoMamones = consAlimentoMamones;
    }

    public double getConsAlimentoNovillos() {
        return consAlimentoNovillos;
    }

    public void setConsAlimentoNovillos(double consAlimentoNovillos) {
        this.consAlimentoNovillos = consAlimentoNovillos;
    }

    public double getConsAlimentoVacas() {
        return consAlimentoVacas;
    }

    public void setConsAlimentoVacas(double consAlimentoVacas) {
        this.consAlimentoVacas = consAlimentoVacas;
    }

    public double getOrinaMamones() {
        return orinaMamones;
    }

    public void setOrinaMamones(double orinaMamones) {
        this.orinaMamones = orinaMamones;
    }

    public double getOrinaNovillos() {
        return orinaNovillos;
    }

    public void setOrinaNovillos(double orinaNovillos) {
        this.orinaNovillos = orinaNovillos;
    }

    public double getOrinaVacas() {
        return orinaVacas;
    }

    public void setOrinaVacas(double orinaVacas) {
        this.orinaVacas = orinaVacas;
    }

    public double getContaminacionMamones() {
        return contaminacionMamones;
    }

    public void setContaminacionMamones(double contaminacionMamones) {
        this.contaminacionMamones = contaminacionMamones;
    }

    public double getContaminacionNovillos() {
        return contaminacionNovillos;
    }

    public void setContaminacionNovillos(double contaminacionNovillos) {
        this.contaminacionNovillos = contaminacionNovillos;
    }

    public double getContaminacionVacas() {
        return contaminacionVacas;
    }

    public void setContaminacionVacas(double contaminacionVacas) {
        this.contaminacionVacas = contaminacionVacas;
    }

    public Integer getTotalDestetados() {
        return totalDestetados;
    }

    public void setTotalDestetados(Integer totalDestetados) {
        this.totalDestetados = totalDestetados;
    }

    public double getConsAguaDestetados() {
        return consAguaDestetados;
    }

    public void setConsAguaDestetados(double consAguaDestetados) {
        this.consAguaDestetados = consAguaDestetados;
    }

    public double getConsAlimentDestetados() {
        return consAlimentDestetados;
    }

    public void setConsAlimentDestetados(double consAlimentDestetados) {
        this.consAlimentDestetados = consAlimentDestetados;
    }

    public double getOrinaDestetados() {
        return orinaDestetados;
    }

    public void setOrinaDestetados(double orinaDestetados) {
        this.orinaDestetados = orinaDestetados;
    }

    public double getContaminacionDestetados() {
        return contaminacionDestetados;
    }

    public void setContaminacionDestetados(double contaminacionDestetados) {
        this.contaminacionDestetados = contaminacionDestetados;
    }
    
    
    
    
    
}
