/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.colcienciasWeb.DAO.impl;

import com.colcienciasWeb.DAO.ColcienciasDao;
import com.colcienciasWeb.DAO.IPredioDao;
import com.colcienciasWeb.DAO.ISimulacionDao;
import com.colcienciasWeb.Model.ConsultaSimulacion;
import com.colcienciasWeb.Model.Predio;
import com.colcienciasWeb.Model.RegistroSimulacion;
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
public class SimulacionDaoImpl implements ISimulacionDao {

    ColcienciasDao dao = new ColcienciasDao();

    @Override
    public List<RegistroSimulacion> getSimulaciones(ConsultaSimulacion parametros) {
        ArrayList<RegistroSimulacion> simulaciones = new ArrayList();
        //simulaciones = dao.getSimulaciones(parametros);
        return simulaciones;
    }

    @Override
    public Simulacion getSimulacion(int idSimulacion) {
        Simulacion simulacion = null;
        simulacion = dao.getSimulacionBYID(idSimulacion);
        return simulacion;
    }
    

}
