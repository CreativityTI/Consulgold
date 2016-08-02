/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.creativity.Filter;

import java.io.Serializable;

/**
 *
 * @author rafael.lima
 */
public class MensagemFilter implements Serializable {

    private static final long serialVersionUID = 1L;

    private String tituloMensagem;

    public String getTituloMensagem() {
        return tituloMensagem;
    }

    public void setTituloMensagem(String tituloMensagem) {
        this.tituloMensagem = tituloMensagem;
    }

   

}