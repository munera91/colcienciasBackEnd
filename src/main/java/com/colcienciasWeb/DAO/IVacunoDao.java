/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.colcienciasWeb.DAO;

import com.colcienciasWeb.Model.HistoricoVacuno;
import com.colcienciasWeb.Model.Simulacion;
import com.colcienciasWeb.Model.Vacuno;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Usuario
 */
public interface IVacunoDao {
    
    List<Vacuno> obtenerVacunos(String idPredio);
    Vacuno obtenerVacuno(String idVacuno);
    String crearVacuno(Vacuno vacuno);
    String eliminarVacuno(String idVacuno);
    String modificarVacuno(Vacuno vacuno);
    List<HistoricoVacuno> obtenerHistoricoVacuno(String idVacuno);
    Simulacion simulacion (ArrayList<Vacuno> vacunos);
    
}
