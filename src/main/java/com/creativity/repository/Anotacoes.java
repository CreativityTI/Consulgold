/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.creativity.repository;

import com.creativity.model.Anotacao;
import java.io.Serializable;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;

/**
 *
 * @author rafael.lima
 */
public class Anotacoes implements Serializable {

    private static final long serialVersionUID = 1L;

    private EntityManager manager;

    @Inject
    public Anotacoes(EntityManager manager) {
        this.manager = manager;
    }

    public Anotacao porId(Long id) {
        return manager.find(Anotacao.class, id);

    }

    public List<Anotacao> todasAnotacoes() {
        return manager.createQuery("from Ficha f JOIN FETCH f.anotacoes", Anotacao.class).getResultList();
    }

    public Anotacao guardar(Anotacao anotacao) {
        return this.manager.merge(anotacao);
    }
    

}
