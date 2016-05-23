/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.creativity.controller;

import com.creativity.Filter.UsuarioFilter;
import com.creativity.model.Usuario;
import com.creativity.repository.Usuarios;
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
public class PesquisaUsuarioBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private Usuarios usuarios;
    private UsuarioFilter filtro;
    
    private Usuario usuarioSelecionado;

    private List<Usuario> usuariosFiltrados;

    public PesquisaUsuarioBean() {
        filtro = new UsuarioFilter();
    }

    public void excluir() {
        usuarios.remover(usuarioSelecionado);
        usuariosFiltrados.remove(usuarioSelecionado);
       
        FacesUtil.addInfoMessage("Usuario " + usuarioSelecionado.getNome()
                + " excluído com sucesso.");
    }

    public void pesquisar() {
        usuariosFiltrados = usuarios.filtrados(filtro);
    }

    public List<Usuario> getUsuariosFiltrados() {
        return usuariosFiltrados;
    }

    public UsuarioFilter getFiltro() {
        return filtro;
    }

    public Usuario getUsuarioSelecionado() {
        return usuarioSelecionado;
    }

    public void setUsuarioSelecionado(Usuario usuarioSelecionado) {
        this.usuarioSelecionado = usuarioSelecionado;
    }
    
    
}
