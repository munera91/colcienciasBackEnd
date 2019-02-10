/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.colcienciasWeb.DAO.implJPA;

import com.colcienciasWeb.DAO.IUsuarioDao;
import com.colcienciasWeb.Model.jpa.Usuario;
import com.colcienciasWeb.utilities.Utilities;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.print.attribute.standard.Destination;
import javax.transaction.Transactional;
import org.jboss.logging.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ResourceProperties.Content;
import org.springframework.http.StreamingHttpOutputMessage.Body;
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

    @Override
    @Transactional
    public Boolean cambiarPassword(Usuario usuario) {
        Boolean actualizada;
        Usuario user = (Usuario) entityManager.find(Usuario.class, usuario.getIdentificacion());
        if (user != null) {
            System.out.println("Nombre user: " + user.getNombre());
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
