/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.colcienciasWeb.Business.impl;


import com.colcienciasWeb.Business.IMunicipioBusiness;
import com.colcienciasWeb.DAO.IMunicipioDao;
import com.colcienciasWeb.Model.Municipio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 *
 * @author sala305
 */
@Service
public class MunicipioBusinessImpl implements IMunicipioBusiness{

    @Autowired
    private IMunicipioDao municipioDao;


    @Override
    public List<Municipio> obtenerMunicipios(String idDepartamento) {
        return municipioDao.obtenerMunicipios(idDepartamento);
    }
    
}
