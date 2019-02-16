/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.colcienciasWeb.Business;


import com.colcienciasWeb.Model.ConsultaSimulacion;
import com.colcienciasWeb.Model.RegistroSimulacion;
import com.colcienciasWeb.Model.Simulacion;
import java.util.List;

/**
 *
 * @author Usuario
 */
public interface ISimulacionBusiness {
    
    List<RegistroSimulacion> getSimulaciones(ConsultaSimulacion parametros);
    Simulacion getSimulacion(int ID_SIMULACION);
          
}
