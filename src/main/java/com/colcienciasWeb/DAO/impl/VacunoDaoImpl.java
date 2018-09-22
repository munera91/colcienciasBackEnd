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
    public List<Vacuno> obtenerVacunos() {
        ArrayList<Vacuno> vacunos = new ArrayList<>();
        try {
            System.out.println("Entro DAO");
            vacunos = dao.getVacunos();
        } catch (Exception ex) {
            Logger.getLogger(VacunoDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vacunos;
    }

    @Override
    public Vacuno obtenerVacuno(String id) {
        Vacuno vacuno = new Vacuno();
        List<Vacuno> listado = Data.getListaVacunos();
        for (Vacuno vac : listado) {
            vacuno = vac;
        }
        return vacuno;
    }

    @Override
    public String crearVacuno(Vacuno vacuno) {
        String respuesta = "Vacuno ya existe";
        List<Vacuno> listado;
        try {
            listado = dao.getVacunos();
            
            listado.add(vacuno);
            dao.insertarVacuno(vacuno);
            respuesta = "Vacuno registrado";

        } catch (Exception ex) {
            Logger.getLogger(FincaDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return respuesta;
    }
    
    public int getCategoriaByPesoVacuno(Double peso){
        int categoriaVacuno = 0;
        
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
        String respuesta = "Vacuno actualizado";
        List<Vacuno> listado;
        try {
            listado = dao.getVacunos();
            for (int i = 0; i < listado.size(); i++) {
                if (listado.get(i).getID() == (vacuno.getID())) {
                    dao.actualizarVacuno(vacuno);
                    break;
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(VacunoDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return respuesta;
    }

}
