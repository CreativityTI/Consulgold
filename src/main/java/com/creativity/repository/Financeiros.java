/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.creativity.repository;

import com.creativity.model.Financeiro;
import java.io.Serializable;
import javax.inject.Inject;
import javax.persistence.EntityManager;

/**
 *
 * @author rafael.lima
 */
public class Financeiros implements Serializable {

    private static final long serialVersionUID = 1L;

    private EntityManager manager;

    @Inject
    public Financeiros(EntityManager manager) {
        this.manager = manager;
    }

    public Financeiro porId(Long id) {
        return manager.find(Financeiro.class, id);

    }

}
