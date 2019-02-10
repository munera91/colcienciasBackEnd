/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.colcienciasWeb.Controller;

import com.colcienciasWeb.Business.IUsuarioBusiness;
import com.colcienciasWeb.Model.jpa.Usuario;
import com.colcienciasWeb.Path.Path.PathUsuario;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author sala305
 */
@RestController
@RequestMapping(value = "/")
public class UsuarioController {

    @Autowired
    private IUsuarioBusiness usuarioBusiness;

    @PostMapping(PathUsuario.VALUSUARIO)
    public Boolean validarUsuario(@RequestBody Usuario usuario,
            HttpServletResponse hsr) {
        return usuarioBusiness.validarUsuario(usuario);
    }
    
    @PostMapping(PathUsuario.USUARIO)
    public Boolean reestablecerPassword(@RequestBody Usuario usuario,
            HttpServletResponse hsr){
        return usuarioBusiness.cambiarPassword(usuario);
    }

    @GetMapping(PathUsuario.USUARIO_EMAIL)
    public Boolean changePass(@PathVariable String email,
            HttpServletResponse hsr) {
        return usuarioBusiness.changePass(email);
    }

    @GetMapping(PathUsuario.USUARIO)
    public List<Usuario> findAll() {
        return usuarioBusiness.findAll();
    }

}
