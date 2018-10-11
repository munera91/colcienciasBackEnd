/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.colcienciasWeb.Business.impl;


import com.colcienciasWeb.Business.IAlimentoBusiness;
import com.colcienciasWeb.DAO.IAlimentoDao;
import com.colcienciasWeb.Model.PropiedadAlimento;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 *
 * @author sala305
 */
@Service
public class AlimentoBusinessImpl implements IAlimentoBusiness{

    @Autowired
    private IAlimentoDao alimentoDao;


    @Override
    public PropiedadAlimento obtenerPropiedadAlimento(String idAlimento) {
        return alimentoDao.propiedadAlimento(idAlimento);
    }
    
}
