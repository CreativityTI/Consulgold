/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.creativity.controller;

import com.creativity.Filter.MensagemFilter;
import com.creativity.model.Mensagem;
import com.creativity.model.Status;
import com.creativity.repository.Mensagens;
import com.creativity.util.FacesUtil;
import java.io.Serializable;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author rafael.lima
 */
@Named
@javax.faces.view.ViewScoped
public class PesquisaMensagemBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private Mensagens mensagens;
    private MensagemFilter filtro;

    private Mensagem mensagemSelecionada;

    private List<Mensagem> mensagensFiltradas;

    public PesquisaMensagemBean() {
        filtro = new MensagemFilter();
    }

    public void excluir() {
        mensagens.remover(mensagemSelecionada);
        mensagensFiltradas.remove(mensagemSelecionada);

        FacesUtil.addInfoMessage("Mensagem " + mensagemSelecionada.getTituloMensagem()
                + " excluída com sucesso.");
    }

    public void pesquisar() {
        mensagensFiltradas = mensagens.filtrados(filtro);
    }

    public Mensagem getMensagemSelecionada() {
        return mensagemSelecionada;
    }

    public void setMensagemSelecionada(Mensagem mensagemSelecionada) {
        this.mensagemSelecionada = mensagemSelecionada;
    }

    public List<Mensagem> getMensagensFiltradas() {
        return mensagensFiltradas;
    }

    public void setMensagensFiltradas(List<Mensagem> mensagensFiltradas) {
        this.mensagensFiltradas = mensagensFiltradas;
    }

    public MensagemFilter getFiltro() {
        return filtro;
    }

    public void setFiltro(MensagemFilter filtro) {
        this.filtro = filtro;
    }

 
}
