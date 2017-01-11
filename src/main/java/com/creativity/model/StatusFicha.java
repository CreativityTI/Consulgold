/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.creativity.model;

/**
 *
 * @author rafael.lima
 */
public enum StatusFicha {

    NOVOCADASTRO("Novo Cadastro"),
    PENDENTE("Pendente"),
    APROVADO("Aprovado"),
     CANCELADO("Cancelada"),
    BACKUP("Backup");

    private String descricao;

    StatusFicha(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

}
