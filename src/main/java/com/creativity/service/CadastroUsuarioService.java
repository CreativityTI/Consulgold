/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.creativity.service;

import com.creativity.model.Usuario;
import com.creativity.repository.Usuarios;
import com.creativity.util.Transactional;
import java.io.Serializable;
import javax.inject.Inject;

/**
 *
 * @author rafael.lima
 */
public class CadastroUsuarioService implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private Usuarios usuarios;

    @Transactional
    public Usuario salvar(Usuario usuario) throws NegocioException {
        Usuario usuarioExistente = usuarios.porEmail(usuario.getEmail());
        if (usuarioExistente != null && !usuarioExistente.equals(usuario)) {
            throw new NegocioException("Já existe um usuario com o e-mail informado.");
        }
        return usuarios.guardar(usuario);
    }

    @Transactional
    public void excluir(Usuario usuario) throws NegocioException {
        usuario = this.usuarios.porId(usuario.getId());
        this.usuarios.remover(usuario);
    }

}
