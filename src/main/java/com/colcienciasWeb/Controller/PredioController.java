/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.colcienciasWeb.Controller;

import com.colcienciasWeb.Business.IPredioBusiness;
import com.colcienciasWeb.Model.Predio;
import com.colcienciasWeb.Path.Path.PathPredio;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author sala305
 */
@RestController
@RequestMapping(value = "/")
public class PredioController {

    @Autowired
    private IPredioBusiness predioBusiness;

    @GetMapping(PathPredio.PREDIOS)
    public List<Predio> obtenerPredios(@PathVariable String idFinca, HttpServletResponse hsr) {
        return predioBusiness.obtenerPredios(idFinca);
    }

    @GetMapping(PathPredio.PREDIO_ID)
    public Predio obtenerPredio(@PathVariable String idPredio,
            HttpServletResponse hsr) {
        if (Integer.parseInt(idPredio) > 0) {
            return predioBusiness.obtenerPredio(idPredio);
        }
        return null;
    }

    @PostMapping(PathPredio.PREDIO)
    public String crearPredio(@RequestBody Predio predio,
            HttpServletResponse hsr) {
        return predioBusiness.crearPredio(predio);
    }

    @PutMapping(PathPredio.PREDIO)
    public String modificarPredio(@RequestBody Predio predio,
            HttpServletResponse hsr) {
        return predioBusiness.modificarPredio(predio);
    }

    @DeleteMapping(PathPredio.PREDIO_ID)
    public String eliminarPredio(@PathVariable String idPredio,
            HttpServletResponse hsr) {
        return predioBusiness.eliminarPredio(idPredio);
    }
}
