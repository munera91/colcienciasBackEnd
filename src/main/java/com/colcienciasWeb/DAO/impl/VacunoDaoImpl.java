/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.colcienciasWeb.DAO.impl;

import com.colcienciasWeb.DAO.ColcienciasDao;
import com.colcienciasWeb.DAO.IVacunoDao;
import com.colcienciasWeb.Model.Vacuno;
import com.colcienciasWeb.Data.Data;
import com.colcienciasWeb.Model.HistoricoVacuno;
import com.colcienciasWeb.Model.Simulacion;
import com.colcienciasWeb.utilities.Utilities;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.stereotype.Repository;

/**
 *
 * @author sala305
 */
@Repository
public class VacunoDaoImpl implements IVacunoDao {
    
    ColcienciasDao dao = new ColcienciasDao();
    
    @Override
    public List<Vacuno> obtenerVacunos(String idPredio) {
        ArrayList<Vacuno> vacunos = new ArrayList<>();
        try {
            System.out.println("Entro DAO");
            vacunos = dao.getVacunos(idPredio);
        } catch (Exception ex) {
            Logger.getLogger(VacunoDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vacunos;
    }
    
    @Override
    public List<HistoricoVacuno> obtenerHistoricoVacuno(String idVacuno) {
        ArrayList<HistoricoVacuno> historicoVacuno = new ArrayList<>();
        System.out.println("Entro DAO Historico vacuno");
        historicoVacuno = dao.getHistoricoVacuno(idVacuno);
        return historicoVacuno;
    }
    
    @Override
    public Vacuno obtenerVacuno(String idVacuno) {
        Vacuno vacuno = null;
        try {
            vacuno = dao.getVacunoBYID(Integer.parseInt(idVacuno));
        } catch (Exception ex) {
            Logger.getLogger(VacunoDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vacuno;
    }
    
    @Override
    public String crearVacuno(Vacuno vacuno) {
        String respuesta = "";
        List<Vacuno> listado;
        try {
            vacuno.setIdCategoria(getCategoriaByPesoVacuno(vacuno.getPeso()));
            respuesta = dao.insertarVacuno(vacuno);
        } catch (Exception ex) {
            Logger.getLogger(FincaDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return respuesta;
    }
    
    public int getCategoriaByPesoVacuno(Double peso) {
        int categoriaVacuno = 0;
        if (peso <= 180.0) {
            categoriaVacuno = 1;
        } else if (peso <= 250.0) {
            categoriaVacuno = 2;
        } else if (peso <= 350.0) {
            categoriaVacuno = 3;
        } else {
            categoriaVacuno = 4;
        }
        return categoriaVacuno;
    }
    
    @Override
    public String eliminarVacuno(String idVacuno) {
        String respuesta = "";
        try {
            respuesta = dao.eliminarVacuno(idVacuno);
        } catch (Exception ex) {
            Logger.getLogger(FincaDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return respuesta;
    }
    
    @Override
    public String modificarVacuno(Vacuno vacuno) {
        String respuesta = "No se pudo actualizar el vacuno";
        vacuno.setIdCategoria(getCategoriaByPesoVacuno(vacuno.getPeso()));
        try {
            dao.actualizarVacuno(vacuno);
            respuesta = "Vacuno actualizado";
        } catch (Exception ex) {
            Logger.getLogger(VacunoDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return respuesta;
    }
    
    @Override
    public Simulacion simulacion(ArrayList<Vacuno> vacunos) {
        Simulacion simulacion = new Simulacion();
        if (vacunos.size() > 0) {
            simulacion = dao.getSimulacion(vacunos.get(0).getIdPredio());
        }
        double consAguaMamones = 0.0, consAguaDestetados = 0.0;
        double consAguaNovillos = 0.0, consAguaVacas = 0.0;
        double consAlimentoMamones = 0.0, consAlimentoDestetados = 0.0;
        double consAlimentoNovillos = 0.0, consAlimentoVacas = 0.0;
        double cantHecesMamones = 0.0, cantHecesDestetados = 0.0;
        double cantHecesNovillos = 0.0, cantHecesVacas = 0.0;
        double contaminacionMamones = 0.0, contaminacionDestetados = 0.0;
        double contaminacionNovillos = 0.0, contaminacionVacas = 0.0;
        double consAgua = 0.0, consAlimento = 0.0, consHeces = 0.0;
        double nutrientes = 0.0, totalHeces = 0.0, totalContaminacion = 0.0;
        double totalCO2 = 0.0, totalCH4 = 0.0, totalNO2 = 0.0;
        for (int i = 0; i < vacunos.size(); i++) {
            vacunos.get(i).setIdCategoria(getCategoriaByPesoVacuno(vacunos.get(i).getPeso()));
            consAgua = Utilities.redondear2Decimales(dao.getCantidadLiquido(vacunos.get(i)));
            consAlimento = Utilities.redondear2Decimales(dao.getCantidadAlimento(vacunos.get(i)));
            consHeces = Utilities.redondear2Decimales(dao.getCantidadHeces(vacunos.get(i)));
            switch (vacunos.get(i).getIdCategoria()) {
                case 1:
                    consAguaMamones += consAgua;
                    consAlimentoMamones += consAlimento;
                    cantHecesMamones += consHeces;
                    break;
                case 2:
                    consAguaDestetados += consAgua;
                    consAlimentoDestetados += consAlimento;
                    cantHecesDestetados += consHeces;
                    break;
                case 3:
                    consAguaNovillos += consAgua;
                    consAlimentoNovillos += consAlimento;
                    cantHecesNovillos += consHeces;
                    break;
                case 4:
                    consAguaVacas += (consAgua * 3);
                    consAlimentoVacas += consAlimento;
                    cantHecesVacas += consHeces;
                    break;
            }
        }
        simulacion.setConsAguaMamones(Utilities.redondear2Decimales(consAguaMamones));
        simulacion.setConsAguaDestetados(Utilities.redondear2Decimales(consAguaDestetados));
        simulacion.setConsAguaNovillos(Utilities.redondear2Decimales(consAguaNovillos));
        simulacion.setConsAguaVacas(Utilities.redondear2Decimales(consAguaVacas));
        simulacion.setConsAlimentoMamones(Utilities.redondear2Decimales(consAlimentoMamones));
        simulacion.setConsAlimentDestetados(Utilities.redondear2Decimales(consAlimentoDestetados));
        simulacion.setConsAlimentoNovillos(Utilities.redondear2Decimales(consAlimentoNovillos));
        simulacion.setConsAlimentoVacas(Utilities.redondear2Decimales(consAlimentoVacas));
        simulacion.setOrinaMamones(Utilities.redondear2Decimales(cantHecesMamones));
        simulacion.setOrinaDestetados(Utilities.redondear2Decimales(cantHecesDestetados));
        simulacion.setOrinaNovillos(Utilities.redondear2Decimales(cantHecesNovillos));
        simulacion.setOrinaVacas(Utilities.redondear2Decimales(cantHecesVacas));
        nutrientes = simulacion.getCarbohidrato() + simulacion.getExtractorEtero()
                + simulacion.getFibraCruda() + simulacion.getProteinaCruda()
                + simulacion.getProteinaDigestiva();
        totalHeces = cantHecesDestetados + cantHecesMamones + cantHecesNovillos
                + cantHecesVacas;
        totalContaminacion = totalHeces * nutrientes;
        contaminacionMamones = cantHecesMamones * nutrientes;
        contaminacionDestetados = cantHecesDestetados * nutrientes;
        contaminacionNovillos = cantHecesNovillos * nutrientes;
        contaminacionVacas = cantHecesVacas * nutrientes;
        //Calculo oxido de carbono
        totalCO2 = totalContaminacion * 0.7;
        //Calculo Gas metano
        totalCH4 = totalContaminacion * 0.2;
        //Calculo Oxido nitroso
        totalNO2 = totalContaminacion * 0.1;
        simulacion.setContaminacionMamones(Utilities.redondear2Decimales(contaminacionMamones));
        simulacion.setContaminacionDestetados(Utilities.redondear2Decimales(contaminacionDestetados));
        simulacion.setContaminacionNovillos(Utilities.redondear2Decimales(contaminacionNovillos));
        simulacion.setContaminacionVacas(Utilities.redondear2Decimales(contaminacionVacas));
        simulacion.setTotalCO2(Utilities.redondear2Decimales(totalCO2));
        simulacion.setTotalCH4(Utilities.redondear2Decimales(totalCH4));
        simulacion.setTotalNO2(Utilities.redondear2Decimales(totalNO2));

        //se llama al metodo que guarda la simulación realizada
        dao.guardarSimulacion(simulacion, vacunos);
        
        return simulacion;
    }

    
    @Override
     public List<Vacuno> obtenerVacunosBYSimulacion(Integer idSimulacion) {
        ArrayList<Vacuno> vacunos = new ArrayList<>();
        try {
            System.out.println("Entro DAO");
            vacunos = dao.getVacunosBYSimulacion(idSimulacion);
        } catch (Exception ex) {
            Logger.getLogger(VacunoDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vacunos;
    }
    
}
