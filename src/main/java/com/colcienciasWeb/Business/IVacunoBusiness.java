/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.colcienciasWeb.Business;


import com.colcienciasWeb.Model.Vacuno;
import java.util.List;

/**
 *
 * @author Usuario
 */
public interface IVacunoBusiness {
    
    List<Vacuno> obtenerVacunos();
    Vacuno obtenerVacuno(String id);
    String crearVacuno(Vacuno vacuno);
    String eliminarVacuno(Vacuno vacuno);
    String modificarVacuno(Vacuno vacuno);
          
}
