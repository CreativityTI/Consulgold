/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.creativity.repository;

import com.creativity.model.Banco;
import java.io.Serializable;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;

/**
 *
 * @author rafael.lima
 */
public class Bancos implements Serializable {

    private static final long serialVersionUID = 1L;

    private EntityManager manager;

    @Inject
    public Bancos(EntityManager manager) {
        this.manager = manager;
    }

    public Banco porId(Long id) {
        return manager.find(Banco.class, id);    }

 

    public List<Banco> todasBancos() {
        return manager.createQuery("from Banco", Banco.class).getResultList();
    }
}
