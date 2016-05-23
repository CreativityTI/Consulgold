/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.creativity.repository;

import com.creativity.model.BaixaFinanceiro;
import java.io.Serializable;
import javax.inject.Inject;
import javax.persistence.EntityManager;

/**
 *
 * @author rafael.lima
 */
public class BaixaFinanceiros implements Serializable {

    private static final long serialVersionUID = 1L;

    private EntityManager manager;

    @Inject
    public BaixaFinanceiros(EntityManager manager) {
        this.manager = manager;
    }

    public BaixaFinanceiro porId(Long id) {
        return manager.find(BaixaFinanceiro.class, id);

    }

}
