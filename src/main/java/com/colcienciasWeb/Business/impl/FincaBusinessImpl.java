/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.colcienciasWeb.Business.impl;


import com.colcienciasWeb.Business.IFincaBusiness;
import com.colcienciasWeb.DAO.IFincaDao;
import com.colcienciasWeb.Model.Finca;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 *
 * @author sala305
 */
@Service
public class FincaBusinessImpl implements IFincaBusiness{

    @Autowired
    private IFincaDao fincaDao;
 

    @Override
    public Finca obtenerFinca(String id) {
        return fincaDao.obtenerFinca(id);
    }

    @Override
    public String crearFinca(Finca finca) {
        return fincaDao.crearFinca(finca);
    }

    @Override
    public String eliminarFinca(Finca finca) {
        return fincaDao.eliminarFinca(finca);
    }

    @Override
    public String modificarFinca(Finca finca) {
        return fincaDao.modificarFinca(finca);
    }

    @Override
    public List<Finca> obtenerFincas() {
        return fincaDao.obtenerFincas();
    }
    
}
