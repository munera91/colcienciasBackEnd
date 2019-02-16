/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.colcienciasWeb.Business.impl;

import com.colcienciasWeb.Business.ISimulacionBusiness;
import com.colcienciasWeb.DAO.ISimulacionDao;
import com.colcienciasWeb.Model.ConsultaSimulacion;
import com.colcienciasWeb.Model.RegistroSimulacion;
import com.colcienciasWeb.Model.Simulacion;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author sala305
 */
@Service
public class SimulacionBusinessImpl implements ISimulacionBusiness {

    @Autowired
    private ISimulacionDao simulacionDao;

    @Override
    public List<RegistroSimulacion> getSimulaciones(ConsultaSimulacion parametros) {
        return simulacionDao.getSimulaciones(parametros);
    }

    @Override
    public Simulacion getSimulacion(int ID_SIMULACION) {
        return simulacionDao.getSimulacion(ID_SIMULACION);
    }

}
