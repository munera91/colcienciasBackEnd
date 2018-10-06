/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.colcienciasWeb.DAO.impl;

import com.colcienciasWeb.DAO.ColcienciasDao;
import com.colcienciasWeb.DAO.IFincaDao;
import com.colcienciasWeb.Model.Finca;
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
public class FincaDaoImpl implements IFincaDao {

    ColcienciasDao dao = new ColcienciasDao();

    @Override
    public List<Finca> obtenerFincas() {
        ArrayList<Finca> fincas = new ArrayList<>();
        try {
            System.out.println("Entro DAO Fincas");
            fincas = dao.getFincas();
        } catch (Exception ex) {
            Logger.getLogger(FincaDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return fincas;
    }


    @Override
    public String crearFinca(Finca finca) {
        List<Finca> listado = obtenerFincas();
        String respuesta = "";
        try {
            listado.add(finca);
            dao.insertarFinca(finca);
            respuesta = "Finca registrada";
        } catch (Exception ex) {
            Logger.getLogger(FincaDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return respuesta;
    }

    @Override
    public String eliminarFinca(Finca finca) {
        String respuesta = "";
        try {
            respuesta = dao.eliminarFinca(finca);
        } catch (Exception ex) {
            Logger.getLogger(FincaDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return respuesta;
    }

    @Override
    public String modificarFinca(Finca finca) {
        String respuesta = "Finca actualizada";
        List<Finca> listado;
        try {
            listado = dao.getFincas();
            for (int i = 0; i < listado.size(); i++) {
                if (listado.get(i).getID() == finca.getID()) {
                    dao.actualizarFinca(finca);
                    break;
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(FincaDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return respuesta;
    }

    @Override
    public Finca obtenerFinca(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
