/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.creativity.repository;

import com.creativity.model.GrupoUsuario;
import java.io.Serializable;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;

/**
 *
 * @author rafael.lima
 */
public class GruposUsuarios implements Serializable {

    private static final long serialVersionUID = 1L;

    private EntityManager manager;

    @Inject
    public GruposUsuarios(EntityManager manager) {
        this.manager = manager;
    }

    public GrupoUsuario porId(Long id) {
        return manager.find(GrupoUsuario.class, id);

    }

    public GrupoUsuario salvar(GrupoUsuario grupoUsuario) {
        return manager.merge(grupoUsuario);
    }

    public List<GrupoUsuario> todosGruposUsuarios() {
        return manager.createQuery("from GrupoUsuario", GrupoUsuario.class).getResultList();
    }
    
     
}
