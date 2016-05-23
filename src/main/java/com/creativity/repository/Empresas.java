/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.creativity.repository;

import com.creativity.model.Empresa;
import java.io.Serializable;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;

/**
 *
 * @author rafael.lima
 */
public class Empresas implements Serializable {

    private static final long serialVersionUID = 1L;

    private EntityManager manager;

    @Inject
    public Empresas(EntityManager manager) {
        this.manager = manager;
    }

    public Empresa porId(Long id) {
        return manager.find(Empresa.class, id);

    }

 

    public List<Empresa> todasEmpresas() {
        return manager.createQuery("from Empresa", Empresa.class).getResultList();
    }
}
