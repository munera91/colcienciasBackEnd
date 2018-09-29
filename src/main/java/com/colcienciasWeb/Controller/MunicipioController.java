/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.colcienciasWeb.Controller;

import com.colcienciasWeb.Business.IMunicipioBusiness;
import com.colcienciasWeb.Model.Municipio;
import com.colcienciasWeb.Path.Path.PathMunicipio;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author sala305
 */
@RestController
@RequestMapping(value = "/")
public class MunicipioController {

    @Autowired
    private IMunicipioBusiness municipioBusiness;

    @GetMapping(PathMunicipio.MUNICIPIOS)
    public List<Municipio> obtenerPredios(@PathVariable String idDepartamento, HttpServletResponse hsr) {
        return municipioBusiness.obtenerMunicipios(idDepartamento);
    }

}
