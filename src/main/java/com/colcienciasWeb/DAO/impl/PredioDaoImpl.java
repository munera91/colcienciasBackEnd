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
    public List<Predio> obtenerPredios() {
        ArrayList<Predio> predios = new ArrayList<>();
        try {
            System.out.println("Entro DAO");
            //predios = dao.getPredios();
        } catch (Exception ex) {
            Logger.getLogger(PredioDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return predios;
    }

    @Override
    public Predio obtenerPredio(String id) {
        Predio predio = new Predio();
        List<Predio> listado = Data.getListaPredios();
        for (Predio vac : listado) {
            predio = vac;
        }
        return predio;
    }

    @Override
    public String crearPredio(Predio predio) {
        String respuesta = "Predio ya existe";
        List<Predio> listado;
        try {
            //listado = dao.getPredios();

//            if (!listado.contains(predio)) {
//                listado.add(predio);
//                dao.insertarPredio(predio);
//                respuesta = "Predio registrado";
//            }
        } catch (Exception ex) {
            Logger.getLogger(FincaDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return respuesta;
    }

    @Override
    public String eliminarPredio(Predio predio) {
        String respuesta = "Predio eliminado";
        try {
           // dao.eliminarPredio(predio);
        } catch (Exception ex) {
            Logger.getLogger(FincaDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return respuesta;
    }

    @Override
    public String modificarPredio(Predio predio) {
        String respuesta = "Predio actualizado";
        List<Predio> listado;
        try {
//            listado = dao.getPredios();
//            for (int i = 0; i < listado.size(); i++) {
//                if (listado.get(i).getID().equals(predio.getID())) {
//                    dao.actualizarPredio(predio);
//                    break;
//                }
//            }
        } catch (Exception ex) {
            Logger.getLogger(PredioDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return respuesta;
    }

}
