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
            dao.insertarVacuno(vacuno);
            respuesta = "Vacuno registrado";
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
    public String eliminarVacuno(Vacuno vacuno) {
        String respuesta = "Vacuno eliminado";
        try {
            dao.eliminarVacuno(vacuno);
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

    

}
