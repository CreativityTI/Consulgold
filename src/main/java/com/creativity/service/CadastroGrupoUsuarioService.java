/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.creativity.service;

import com.creativity.model.GrupoUsuario;
import com.creativity.repository.GruposUsuarios;
import com.creativity.util.Transactional;
import java.io.Serializable;
import javax.inject.Inject;

/**
 *
 * @author rafael.lima
 */
public class CadastroGrupoUsuarioService implements Serializable {
    
      private static final long serialVersionUID = 1L;

    @Inject
    private GruposUsuarios gruposUsuarios;

    @Transactional
    public GrupoUsuario salvar(GrupoUsuario grupoUsuario) {
        return gruposUsuarios.salvar(grupoUsuario);

    }

}
