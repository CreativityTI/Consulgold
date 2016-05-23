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
public enum StatusFinanceiro {

    ABERTO("Em Aberto"),
    SEMLANCAMENTO("Sem Lançamento"),
    PAGO("Pago");

    private String descricao;

    StatusFinanceiro(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

}
