/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.creativity.security;

import com.creativity.model.Usuario;
import com.creativity.repository.Usuarios;
import com.creativity.util.CDIServiceLocator;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 *
 * @author rafael.lima
 */
public class AppUserDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuarios usuarios = CDIServiceLocator.getBean(Usuarios.class);
        Usuario usuario = usuarios.porEmail(email);

        UsuarioSistema user = null;

        if (usuario != null) {
            user = new UsuarioSistema(usuario, getGrupos(usuario));
        }

        return user;
    }

    private Collection<? extends GrantedAuthority> getGrupos(Usuario usuario) {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(usuario.getGrupoUsuario().getDescricao().toUpperCase()));
        return authorities;
    }

}
