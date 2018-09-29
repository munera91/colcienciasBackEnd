/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.colcienciasWeb.DAO.impl;

import com.colcienciasWeb.DAO.ColcienciasDao;
import com.colcienciasWeb.DAO.IMunicipioDao;
import com.colcienciasWeb.DAO.IPredioDao;
import com.colcienciasWeb.Model.Predio;
import com.colcienciasWeb.Data.Data;
import com.colcienciasWeb.Model.Municipio;
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
public class MunicipioDaoImpl implements IMunicipioDao {

    ColcienciasDao dao = new ColcienciasDao();

    @Override
    public List<Municipio> obtenerMunicipios(String idDepartamento) {
        ArrayList<Municipio> municipios = new ArrayList<>();
        try {
            System.out.println("Entro DAO Municipios");
            municipios = dao.getMunicipios(idDepartamento);
        } catch (Exception ex) {
            Logger.getLogger(MunicipioDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return municipios;
    }

}
