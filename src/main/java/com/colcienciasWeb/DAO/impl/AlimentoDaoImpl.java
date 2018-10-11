/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.colcienciasWeb.DAO.impl;

import com.colcienciasWeb.DAO.ColcienciasDao;
import com.colcienciasWeb.Model.PropiedadAlimento;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.stereotype.Repository;
import com.colcienciasWeb.DAO.IAlimentoDao;

/**
 *
 * @author sala305
 */
@Repository
public class AlimentoDaoImpl implements IAlimentoDao {

    ColcienciasDao dao = new ColcienciasDao();

    @Override
    public PropiedadAlimento propiedadAlimento(String idAlimento) {
        PropiedadAlimento propiedadAlimento = null;
        try {
            System.out.println("Entro DAO PropiedadAlimento");
            propiedadAlimento = dao.getNutritionalTable(idAlimento);
        } catch (Exception ex) {
            Logger.getLogger(AlimentoDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return propiedadAlimento;
    }

}
