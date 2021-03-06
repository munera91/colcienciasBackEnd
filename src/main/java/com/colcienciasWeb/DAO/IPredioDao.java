/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.colcienciasWeb.DAO;

import com.colcienciasWeb.Model.Predio;
import java.util.List;

/**
 *
 * @author Usuario
 */
public interface IPredioDao {
    
    List<Predio> obtenerPredios(String idFinca);
    Predio obtenerPredio(String id);
    String crearPredio(Predio predio);
    String eliminarPredio(String idPredio);
    String modificarPredio(Predio predio);
    
}
