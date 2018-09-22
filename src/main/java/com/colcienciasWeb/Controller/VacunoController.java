/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.colcienciasWeb.Controller;


import com.colcienciasWeb.Business.IVacunoBusiness;
import com.colcienciasWeb.Model.Vacuno;
import com.colcienciasWeb.Path.Path.PathVacuno;
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
public class VacunoController {

    @Autowired
    private IVacunoBusiness vacunoBusiness;

    @GetMapping(PathVacuno.VACUNO)
    public List<Vacuno> obtenerVacunos(HttpServletResponse hsr) {
        return vacunoBusiness.obtenerVacunos();
    }
    
    @GetMapping(PathVacuno.VACUNO_ID)
    public Vacuno obtenerVacuno (@PathVariable String id, 
            HttpServletResponse hsr){
        return vacunoBusiness.obtenerVacuno(id);
    }
    
    @PostMapping(PathVacuno.VACUNO)
    public String crearVacuno (@RequestBody Vacuno vacuno,
            HttpServletResponse hsr){
        return vacunoBusiness.crearVacuno(vacuno);
    }
    
    @PutMapping(PathVacuno.VACUNO)
    public String modificarVacuno(@RequestBody Vacuno vacuno,
            HttpServletResponse hsr){
        return vacunoBusiness.modificarVacuno(vacuno);
    }
    
    @DeleteMapping(PathVacuno.VACUNO)
    public String eliminarVacuno(@RequestBody Vacuno vacuno,
            HttpServletResponse hsr){
        return vacunoBusiness.eliminarVacuno(vacuno);
    }
}
