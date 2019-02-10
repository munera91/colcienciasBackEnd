/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.colcienciasWeb.Business.impl;

import com.colcienciasWeb.Business.IUsuarioBusiness;
import com.colcienciasWeb.DAO.IUsuarioDao;
import com.colcienciasWeb.Model.jpa.Usuario;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 *
 * @author Kmilo-Munera
 */
@Service
public class UsuarioBusinessImpl implements IUsuarioBusiness {

    @Autowired
    @Qualifier("UsuarioJPA")
    private IUsuarioDao usuarioDao;

    @Override
    public Boolean cambiarPassword(Usuario usuario) {
        return usuarioDao.cambiarPassword(usuario);
    }

    @Override
    public List<Usuario> findAll() {
        return usuarioDao.findAll();
    }

    @Override
    public Boolean validarUsuario(Usuario usuario) {
        return usuarioDao.validarUsuario(usuario);
    }

    @Override
    public Boolean changePass(String email) {
        return usuarioDao.changePass(email);
    }

}
