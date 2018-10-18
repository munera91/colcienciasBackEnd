/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.colcienciasWeb.Controller;


import com.colcienciasWeb.Business.IVacunoBusiness;
import com.colcienciasWeb.Model.HistoricoVacuno;
import com.colcienciasWeb.Model.Simulacion;
import com.colcienciasWeb.Model.Vacuno;
import com.colcienciasWeb.Path.Path;
import com.colcienciasWeb.Path.Path.PathVacuno;
import java.util.ArrayList;
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

    @GetMapping(PathVacuno.VACUNOS)
    public List<Vacuno> obtenerVacunos(@PathVariable String idPredio, 
            HttpServletResponse hsr) {
        return vacunoBusiness.obtenerVacunos(idPredio);
    }
    
    @GetMapping(PathVacuno.VACUNO_H)
    public List<HistoricoVacuno> obtenerHistoricoVacuno(@PathVariable String idVacuno, 
            HttpServletResponse hsr) {
        return vacunoBusiness.obtenerHistoricoVacuno(idVacuno);
    }
    
    @PostMapping(Path.PathSimulacion.SIMULAR)
    public Simulacion simular(@RequestBody ArrayList<Vacuno> vacunos,
            HttpServletResponse hsr){
        return vacunoBusiness.simulacion(vacunos);
    }
    
    
    @GetMapping(PathVacuno.VACUNO_ID)
    public Vacuno obtenerVacuno (@PathVariable String idVacuno, 
            HttpServletResponse hsr){
        return vacunoBusiness.obtenerVacuno(idVacuno);
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
    
    @DeleteMapping(PathVacuno.VACUNO_ID)
    public String eliminarVacuno(@PathVariable String idVacuno,
            HttpServletResponse hsr){
        return vacunoBusiness.eliminarVacuno(idVacuno);
    }
}
