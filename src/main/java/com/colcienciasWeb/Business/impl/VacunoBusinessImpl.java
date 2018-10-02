/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.colcienciasWeb.Business.impl;


import com.colcienciasWeb.Business.IVacunoBusiness;
import com.colcienciasWeb.DAO.IVacunoDao;
import com.colcienciasWeb.Model.Vacuno;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 *
 * @author sala305
 */
@Service
public class VacunoBusinessImpl implements IVacunoBusiness{

    @Autowired
    private IVacunoDao vacunoDao;
 

    @Override
    public Vacuno obtenerVacuno(String idVacuno) {
        return vacunoDao.obtenerVacuno(idVacuno);
    }

    @Override
    public String crearVacuno(Vacuno vacuno) {
        return vacunoDao.crearVacuno(vacuno);
    }

    @Override
    public String eliminarVacuno(Vacuno vacuno) {
        return vacunoDao.eliminarVacuno(vacuno);
    }

    @Override
    public String modificarVacuno(Vacuno vacuno) {
        return vacunoDao.modificarVacuno(vacuno);
    }

    @Override
    public List<Vacuno> obtenerVacunos(String idPredio) {
        return vacunoDao.obtenerVacunos(idPredio);
    }
    
}
