/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.creativity.model;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


/**
 *
 * @author rafael.lima
 */
public class Teste {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("CreativityPU");
        EntityManager em = emf.createEntityManager();

        GrupoUsuario grupoUsuario = new GrupoUsuario();
        grupoUsuario.setDescricao("Teste");
        
        em.getTransaction().begin();
        em.persist(grupoUsuario);
        em.getTransaction().commit();

        System.out.println("Grupo de Usuário salvo com sucesso!");
//em.close();
    }
}
