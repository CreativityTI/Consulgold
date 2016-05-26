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
public enum StatusResposta {

    RESATENDENTE("Anotação Atendente"),    
    RESCONSULTOR("Anotação Consultor");

    private String descricao;

    StatusResposta(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

}
