/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.colcienciasWeb.Business;


import com.colcienciasWeb.Model.Predio;
import java.util.List;

/**
 *
 * @author Usuario
 */
public interface IPredioBusiness {
    
    List<Predio> obtenerPredios(String idPredio);
    Predio obtenerPredio(String id);
    String crearPredio(Predio predio);
    String eliminarPredio(Predio predio);
    String modificarPredio(Predio predio);
          
}
