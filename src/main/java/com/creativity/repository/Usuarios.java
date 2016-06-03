/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.creativity.repository;

import org.apache.commons.lang3.StringUtils;
import com.creativity.Filter.UsuarioFilter;
import com.creativity.model.Usuario;
import com.creativity.security.UsuarioSistema;
import com.creativity.service.NegocioException;
import com.creativity.util.Transactional;
import java.io.Serializable;
import java.util.List;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

/**
 *
 * @author rafael.lima
 */
public class Usuarios implements Serializable {

    private static final long serialVersionUID = 1L;

    private EntityManager manager;

    @Inject
    public Usuarios(EntityManager manager) {
        this.manager = manager;
    }

    public Usuario porId(Long id) {
        return manager.find(Usuario.class, id);

    }

    public List<Usuario> usuariosLogado() {

        String nome = null;

        UsuarioSistema usuarioLogado = getUsuarioLogado();

        if (usuarioLogado != null) {
            nome = usuarioLogado.getUsuario().getNome();

        }
        return manager.createQuery("from Usuario where (nome) = :nome", Usuario.class).setParameter("nome", nome).getResultList();
    }

    public List<Usuario> gestorLogado() {

        String nome = null;

        UsuarioSistema usuarioLogado = getUsuarioLogado();

        if (usuarioLogado != null) {
            nome = usuarioLogado.getUsuario().getNome();

        }
        return manager.createQuery("from Usuario where (nome) = :nome", Usuario.class).setParameter("nome", nome).getResultList();
    }

    public List<Usuario> usuarios() {

        String nome = null;

        UsuarioSistema usuarioLogado = getUsuarioLogado();

        if (usuarioLogado != null) {
            nome = usuarioLogado.getUsuario().getNome();

        }
        return manager.createQuery("from Usuario where (nome) = :nome", Usuario.class).setParameter("nome", nome).getResultList();
    }

    public List<Usuario> consultorGestorLogado() {

        Long gestor = null;

        UsuarioSistema usuarioLogado = getUsuarioLogado();

        if (usuarioLogado != null) {

            gestor = usuarioLogado.getUsuario().getId();

        }
        return manager.createQuery("from Usuario where (gestor_id) = :gestor", Usuario.class)
                .setParameter("gestor", gestor).getResultList();
    }

    @SuppressWarnings("unchecked")
    public List<Usuario> filtrados(UsuarioFilter filtro) {
        Session session = manager.unwrap(Session.class);
        Criteria criteria = session.createCriteria(Usuario.class);

        if (StringUtils.isNotBlank(filtro.getNome())) {
            criteria.add(Restrictions.ilike("nome", filtro.getNome(), MatchMode.ANYWHERE));
        }

        return criteria.addOrder(Order.asc("nome")).list();
    }

    public Usuario porEmail(String email) {
        Usuario usuario = null;

        try {
            usuario = this.manager.createQuery("from Usuario where lower(email) = :email", Usuario.class)
                    .setParameter("email", email.toLowerCase()).getSingleResult();
        } catch (NoResultException e) {
            // nenhum usuário encontrado com o e-mail informado
        }

        return usuario;
    }

    public List<Usuario> todosGestores() {
        return manager.createQuery("from Usuario where grupousuario_id in (1,2) order by nome ASC ", Usuario.class).getResultList();
    }

  

    public List<Usuario> todosConsultores() {
        return manager.createQuery("from Usuario where grupousuario_id = 3 order by nome ASC ", Usuario.class).getResultList();
    }

    public Usuario guardar(Usuario usuario) {
        return this.manager.merge(usuario);
    }

    public void adicionar(Usuario usuario) {
        this.manager.persist(usuario);
    }

    @Transactional
    public void remover(Usuario usuario) {
        try {
            usuario = porId(usuario.getId());
            manager.remove(usuario);
            manager.flush();
        } catch (PersistenceException e) {
            throw new NegocioException("Usuário não pode ser excluído.");
        }
    }

    public List<Usuario> todosUsuarios() {
        return manager.createQuery("from Usuario", Usuario.class).getResultList();
    }

    private UsuarioSistema getUsuarioLogado() {
        UsuarioSistema usuario = null;

        UsernamePasswordAuthenticationToken auth = (UsernamePasswordAuthenticationToken) FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal();

        if (auth != null && auth.getPrincipal() != null) {
            usuario = (UsuarioSistema) auth.getPrincipal();
        }

        return usuario;

    }

}
