/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.colcienciasWeb.DAO.implJPA;

import com.colcienciasWeb.DAO.IUsuarioDao;
import com.colcienciasWeb.Model.jpa.Usuario;
import com.colcienciasWeb.utilities.Utilities;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Usuario
 */
@Repository("UsuarioJPA")
public class UsuarioDaoImplJPA implements IUsuarioDao {

    @PersistenceContext
    @Autowired
    private EntityManager entityManager;

    private SimpleDateFormat simpleDateFormat = null;
    Date fechaUltCambio = null;
    Date fechaActual = new Date();

    @Override
    @Transactional
    public Boolean cambiarPassword(Usuario usuario) {
        Boolean actualizada = false;
        Integer permitirCambio = null;
        Usuario user = (Usuario) entityManager.find(Usuario.class, usuario.getIdentificacion());
        if (user != null) {
                System.out.println("Nombre user: " + user.getNombre());
                if (user.getUltCambioFecha() != null) {
                    fechaUltCambio = user.getUltCambioFecha();                    
                    permitirCambio = Utilities.restarFechas(fechaUltCambio, fechaActual);
                } else {
                    permitirCambio = 1;
                }
                user.setPassword(Utilities.getStringMessageDigest(usuario.getPassword()));
                entityManager.merge(user);
                actualizada = true;
        } else {
            actualizada = false;
        }
        return actualizada;
    }

    @Override
    @Transactional
    public List<Usuario> findAll() {
        return entityManager.createQuery("SELECT u FROM Usuario u").getResultList();
    }

    @Override
    public Boolean validarUsuario(Usuario usuario) {
        Boolean validado = false;
        System.out.println("Pass user normal: " + usuario.getPassword());
        usuario.setPassword(Utilities.getStringMessageDigest(usuario.getPassword()));
        System.out.println("Pass user MD5: " + usuario.getPassword());
        Usuario user = (Usuario) entityManager.find(Usuario.class, usuario.getIdentificacion());
        if (user != null) {
            System.out.println("Nombre user: " + user.getNombre());
            validado = usuario.getIdentificacion().equalsIgnoreCase(user.getIdentificacion())
                    && usuario.getPassword().equals(user.getPassword());
        }
        return validado;
    }

    @Override
    public Boolean changePass(String emailUser) {
        Boolean encontrado = false;
        Usuario usuario = null;
        List<Usuario> users = findAll();
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getEmail().equals(emailUser)) {
                usuario = users.get(i);
                System.out.println("Nombre usuario: " + usuario.getNombre());
                encontrado = true;
                break;
            }
        }
        return encontrado;
    }

}
