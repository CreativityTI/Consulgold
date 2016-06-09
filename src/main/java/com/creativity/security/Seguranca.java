/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.creativity.security;

import com.creativity.util.FacesUtil;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

/**
 *
 * @author rafael.lima
 */
@Named
@RequestScoped
public class Seguranca {

    public String getNomeUsuario() {
        String nome = null;

        UsuarioSistema usuarioLogado = getUsuarioLogado();

        if (usuarioLogado != null) {
            nome = usuarioLogado.getUsuario().getNome();
        }

        return nome;
    }

    public Boolean getStatusUsuario() {
        Boolean status = null;

        UsuarioSistema usuarioLogado = getUsuarioLogado();

        if (usuarioLogado != null) {
            status = usuarioLogado.getUsuario().getStatus();
        }

        return status;
    }

    private UsuarioSistema getUsuarioLogado() {
        UsuarioSistema usuario = null;

        UsernamePasswordAuthenticationToken auth = (UsernamePasswordAuthenticationToken) FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal();

        if (auth != null && auth.getPrincipal() != null) {
            usuario = (UsuarioSistema) auth.getPrincipal();
        }

        return usuario;

    }

    public boolean isPermitirGestor() {
        return FacesUtil.getExternalContext().isUserInRole("GESTOR");
    }

    public boolean isPermitirAdmin() {
        return FacesUtil.getExternalContext().isUserInRole("ADMINISTRADOR")
                || FacesUtil.getExternalContext().isUserInRole("ATENDENTE")
                || FacesUtil.getExternalContext().isUserInRole("FINANCEIRO");
    }

    public boolean isAprovarFicha() {
        return FacesUtil.getExternalContext().isUserInRole("ADMINISTRADOR")
                || FacesUtil.getExternalContext().isUserInRole("ATENDENTE")
                || FacesUtil.getExternalContext().isUserInRole("FINANCEIRO");
    }

    public boolean isPedenteFicha() {
        return FacesUtil.getExternalContext().isUserInRole("ADMINISTRADOR")
                || FacesUtil.getExternalContext().isUserInRole("ATENDENTE");
    }

}
