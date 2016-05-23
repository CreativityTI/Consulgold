/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.creativity.repository;

import com.creativity.Filter.MensagemFilter;
import com.creativity.Filter.UsuarioFilter;
import com.creativity.model.Mensagem;
import com.creativity.model.Usuario;
import com.creativity.security.UsuarioSistema;
import com.creativity.service.NegocioException;
import com.creativity.util.Transactional;
import java.io.Serializable;
import java.util.List;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import org.apache.commons.lang3.StringUtils;
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
public class Mensagens implements Serializable {

    private static final long serialVersionUID = 1L;

    private EntityManager manager;

    @Inject
    public Mensagens(EntityManager manager) {
        this.manager = manager;
    }

    public Mensagem porId(Long id) {
        return manager.find(Mensagem.class, id);

    }

    public List<Mensagem> todasMensagens() {
        return manager.createQuery("from Mensagem m where m.status = 0 and m.consultor.id = 25 ", Mensagem.class).getResultList();
    }

    public Mensagem guardar(Mensagem mensagem) {
        return this.manager.merge(mensagem);
    }

    @Transactional
    public void remover(Mensagem mensagem) {
        try {
            mensagem = porId(mensagem.getId());
            manager.remove(mensagem);
            manager.flush();
        } catch (PersistenceException e) {
            throw new NegocioException("Mensagem não pode ser excluída.");
        }
    }

    @SuppressWarnings("unchecked")
    public List<Mensagem> filtrados(MensagemFilter filtro) {
        Session session = manager.unwrap(Session.class);
        Criteria criteria = session.createCriteria(Mensagem.class);

        if (StringUtils.isNotBlank(filtro.getTituloMensagem())) {
            criteria.add(Restrictions.ilike("tituloMensagem", filtro.getTituloMensagem(), MatchMode.ANYWHERE));
        }

        return criteria.addOrder(Order.asc("tituloMensagem")).list();
    }

    public List<Mensagem> mensagemGestor() {
        String nome = null;

        UsuarioSistema usuarioLogado = getUsuarioLogado();

        if (usuarioLogado != null) {
            nome = usuarioLogado.getUsuario().getNome();

        }

        return manager.createQuery("from Mensagem f where f.status = 0 and (f.consultor.nome)= :nome").setParameter("nome", nome).getResultList();

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
