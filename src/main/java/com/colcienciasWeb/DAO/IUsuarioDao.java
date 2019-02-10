/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.colcienciasWeb.DAO;


import com.colcienciasWeb.Model.jpa.Usuario;
import java.util.List;

/**
 *
 * @author Usuario
 */
public interface IUsuarioDao {
    
    List<Usuario> findAll();
    Boolean validarUsuario(Usuario usuario);   
    Boolean changePass(String emailUser);
    Boolean cambiarPassword(Usuario usuario);
      
}
