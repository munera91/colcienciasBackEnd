/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.colcienciasWeb.Business;


import com.colcienciasWeb.Model.Finca;
import java.util.List;

/**
 *
 * @author Usuario
 */
public interface IFincaBusiness {
    
    List<Finca> obtenerFincas();
    Finca obtenerFinca(String id);
    String crearFinca(Finca finca);
    String eliminarFinca(Finca finca);
    String modificarFinca(Finca finca);
    
}
