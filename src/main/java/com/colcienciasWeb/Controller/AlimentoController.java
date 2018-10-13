/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.colcienciasWeb.Controller;

import com.colcienciasWeb.Business.IAlimentoBusiness;
import com.colcienciasWeb.Model.PropiedadAlimento;
import com.colcienciasWeb.Path.Path;
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
public class AlimentoController {

    @Autowired
    private IAlimentoBusiness alimentoBusiness;

    @GetMapping(Path.PathPropAlimento.ALIMENTO)
    public PropiedadAlimento obtenerPropiedadAlimento(@PathVariable String idAlimento, HttpServletResponse hsr) {
        return alimentoBusiness.obtenerPropiedadAlimento(idAlimento);
    }

}
