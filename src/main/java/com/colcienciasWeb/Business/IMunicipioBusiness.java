/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.colcienciasWeb.Business;



import com.colcienciasWeb.Model.Municipio;
import java.util.List;

/**
 *
 * @author Usuario
 */
public interface IMunicipioBusiness {
    
    List<Municipio> obtenerMunicipios(String idDepartamento);
          
}
