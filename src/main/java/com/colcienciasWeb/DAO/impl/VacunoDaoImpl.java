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
        double consAguaMamones = 0.0, consAguaDestetados = 0.0;
        double consAguaNovillos = 0.0, consAguaVacas = 0.0;
        double consAlimentoMamones = 0.0, consAlimentoDestetados = 0.0;
        double consAlimentoNovillos = 0.0, consAlimentoVacas = 0.0;
        double consAgua = 0.0, consAlimento = 0.0;
        for (int i = 0; i < vacunos.size(); i++) {
            vacunos.get(i).setIdCategoria(getCategoriaByPesoVacuno(vacunos.get(i).getPeso()));
            consAgua = dao.getCantidadLiquido(vacunos.get(i));
            consAlimento = dao.getCantidadAlimento(vacunos.get(i));
            switch (vacunos.get(i).getIdCategoria()) {
                case 1:
                    consAguaMamones += consAgua;
                    consAlimentoMamones += consAlimento;
                    break;
                case 2:
                    consAguaDestetados += consAgua;
                    consAlimentoDestetados += consAlimento;
                    break;
                case 3:
                    consAguaNovillos += consAgua;
                    consAlimentoNovillos += consAlimento;
                    break;
                case 4:
                    consAguaVacas += consAgua;
                    consAlimentoVacas += consAlimento;
                    break;
            }
        }
        simulacion.setConsAguaMamones(consAguaMamones);
        simulacion.setConsAguaDestetados(consAguaDestetados);
        simulacion.setConsAguaNovillos(consAguaNovillos);
        simulacion.setConsAguaVacas(consAguaVacas);
        simulacion.setConsAlimentoMamones(consAlimentoMamones);
        simulacion.setConsAlimentDestetados(consAlimentoDestetados);
        simulacion.setConsAlimentoNovillos(consAlimentoNovillos);
        simulacion.setConsAlimentoVacas(consAlimentoVacas);
        return simulacion;
    }

}
