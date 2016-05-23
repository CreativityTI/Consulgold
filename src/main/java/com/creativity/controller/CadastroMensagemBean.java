/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.creativity.controller;

import com.creativity.model.Mensagem;
import com.creativity.model.Status;
import com.creativity.model.Usuario;
import com.creativity.repository.Usuarios;
import com.creativity.service.CadastroMensagemService;
import com.creativity.service.NegocioException;
import com.creativity.util.FacesUtil;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author rafael.lima
 */
@Named
@javax.faces.view.ViewScoped
public class CadastroMensagemBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private Usuarios usuarios;

    private Usuario usuario;

    @Inject
    private CadastroMensagemService cadastroMensagemService;

    private List<Usuario> todosUsuarios;

    private Mensagem mensagem;

    public void prepararCadastro() {

        this.todosUsuarios = usuarios.todosUsuarios();

        if (this.mensagem == null) {
            this.mensagem = new Mensagem();
            this.usuario = new Usuario();

        }

    }

    public void salvar() {

        FacesContext context = FacesContext.getCurrentInstance();

        try {
         
            this.cadastroMensagemService.salvar(this.mensagem);
            FacesUtil.addInfoMessage("Mensagem salva com sucesso!");
            this.mensagem = new Mensagem();

        } catch (NegocioException e) {

            FacesMessage mensagem = new FacesMessage(e.getMessage());
            mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
            context.addMessage(null, mensagem);
        }
    }

    public List<Usuario> getTodosUsuarios() {
        return todosUsuarios;
    }

    public void setTodosUsuarios(List<Usuario> todosUsuarios) {
        this.todosUsuarios = todosUsuarios;
    }

    public Mensagem getMensagem() {
        return mensagem;
    }

    public void setMensagem(Mensagem mensagem) {
        this.mensagem = mensagem;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Status[] getStatuses() {
        return Status.values();
    }

}
