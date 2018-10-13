/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.colcienciasWeb.DAO.impl;

import com.colcienciasWeb.DAO.ColcienciasDao;
import com.colcienciasWeb.DAO.IPredioDao;
import com.colcienciasWeb.Model.Predio;
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
public class PredioDaoImpl implements IPredioDao {

    ColcienciasDao dao = new ColcienciasDao();

    @Override
    public List<Predio> obtenerPredios(String idFinca) {
        ArrayList<Predio> predios = new ArrayList<>();
        try {
            System.out.println("Entro DAO Predios");
            predios = dao.getPredios(idFinca);
        } catch (Exception ex) {
            Logger.getLogger(PredioDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return predios;
    }

    @Override
    public Predio obtenerPredio(String id) {
        Predio predio = new Predio();
        try {
            predio = dao.getPredioBYID(Integer.parseInt(id));
        } catch (Exception ex) {
            Logger.getLogger(PredioDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return predio;
    }

    @Override
    public String crearPredio(Predio predio) {
        String respuesta = "Predio ya existe";
        List<Predio> listado;
        try {
            listado = dao.getPredios(Integer.toString(predio.getIdFinca()));
            listado.add(predio);
            dao.insertarPredio(predio);
            respuesta = "Predio registrado";

        } catch (Exception ex) {
            Logger.getLogger(FincaDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return respuesta;
    }

    @Override
    public String eliminarPredio(String idPredio) {
        String respuesta = "";
        try {
            respuesta = dao.eliminarPredio(idPredio);
        } catch (Exception ex) {
            Logger.getLogger(FincaDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return respuesta;
    }

    @Override
    public String modificarPredio(Predio predio) {
        String respuesta = "No se pudo actualizar el predio";
        try {
            dao.actualizarPredio(predio);
            respuesta = "Predio actualizado";
        } catch (Exception ex) {
            Logger.getLogger(PredioDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return respuesta;
    }

}
