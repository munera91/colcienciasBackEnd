/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.colcienciasWeb.Business.impl;


import com.colcienciasWeb.Business.IPredioBusiness;
import com.colcienciasWeb.DAO.IPredioDao;
import com.colcienciasWeb.Model.Predio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 *
 * @author sala305
 */
@Service
public class PredioBusinessImpl implements IPredioBusiness{

    @Autowired
    private IPredioDao predioDao;
 

    @Override
    public Predio obtenerPredio(String id) {
        return predioDao.obtenerPredio(id);
    }

    @Override
    public String crearPredio(Predio predio) {
        return predioDao.crearPredio(predio);
    }

    @Override
    public String eliminarPredio(Predio predio) {
        return predioDao.eliminarPredio(predio);
    }

    @Override
    public String modificarPredio(Predio predio) {
        return predioDao.modificarPredio(predio);
    }

    @Override
    public List<Predio> obtenerPredios() {
        return predioDao.obtenerPredios();
    }
    
}
