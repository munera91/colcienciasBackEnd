/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.colcienciasWeb.Controller;


import com.colcienciasWeb.Business.IFincaBusiness;
import com.colcienciasWeb.Model.Finca;
import com.colcienciasWeb.Path.Path.PathFinca;
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
public class FincaController {

    @Autowired
    private IFincaBusiness fincaBusiness;

    @GetMapping(PathFinca.FINCA)
    public List<Finca> obtenerFincas(HttpServletResponse hsr) {
        return fincaBusiness.obtenerFincas();
    }
    
    @GetMapping(PathFinca.FINCA_ID)
    public Finca obtenerFinca (@PathVariable String id, 
            HttpServletResponse hsr){
        return fincaBusiness.obtenerFinca(id);
    }
    
    @PostMapping(PathFinca.FINCA)
    public String crearFinca (@RequestBody Finca finca,
            HttpServletResponse hsr){
        return fincaBusiness.crearFinca(finca);
    }
    
    @PutMapping(PathFinca.FINCA)
    public String modificarFinca(@RequestBody Finca finca,
            HttpServletResponse hsr){
        return fincaBusiness.modificarFinca(finca);
    }
    
    @DeleteMapping(PathFinca.FINCA)
    public String eliminarFinca(@RequestBody Finca finca,
            HttpServletResponse hsr){
        return fincaBusiness.eliminarFinca(finca);
    }
    

}
