/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.creativity.repository;

import com.creativity.Filter.FichaFilter;
import com.creativity.model.Ficha;
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
public class Fichas implements Serializable {

    private static final long serialVersionUID = 1L;

    private EntityManager manager;

    @Inject
    public Fichas(EntityManager manager) {
        this.manager = manager;
    }

    @SuppressWarnings("unchecked")
    public List<Ficha> filtradosNovo(FichaFilter filtro) {
        Session session = manager.unwrap(Session.class);
        Criteria criteria = session.createCriteria(Ficha.class)
                .createAlias("gestor", "v");;

        if (StringUtils.isNotBlank(filtro.getNomeVendedor())) {
            criteria.add(Restrictions.ilike("v.nome", filtro.getNomeVendedor(), MatchMode.ANYWHERE));
        }

        return criteria.addOrder(Order.asc("v.nome")).list();
    }

    public List<Ficha> filtradosAdmin(FichaFilter filtro) {

        Session session = this.manager.unwrap(Session.class);

        Criteria criteria = session.createCriteria(Ficha.class)
                // fazemos uma associação (join) com cliente e nomeamos como "c"

                // fazemos uma associação (join) com vendedor e nomeamos como "v"
                .createAlias("gestor", "v");

        if (filtro.getNumeroDe() != null) {
            // id deve ser maior ou igual (ge = greater or equals) a filtro.numeroDe
            criteria.add(Restrictions.ge("id", filtro.getNumeroDe()));
        }

        if (filtro.getNumeroAte() != null) {
            // id deve ser menor ou igual (le = lower or equal) a filtro.numeroDe
            criteria.add(Restrictions.le("id", filtro.getNumeroAte()));
        }

        if (filtro.getDataCriacaoDe() != null) {
            criteria.add(Restrictions.ge("dataCriacao", filtro.getDataCriacaoDe()));
        }

        if (filtro.getDataCriacaoAte() != null) {
            criteria.add(Restrictions.le("dataCriacao", filtro.getDataCriacaoAte()));
        }

        if (filtro.getDataAprovacaoDe() != null) {
            criteria.add(Restrictions.ge("dataAprovacao", filtro.getDataAprovacaoDe()));
        }

        if (filtro.getDataAprovacaoAte() != null) {
            criteria.add(Restrictions.le("dataAprovacao", filtro.getDataAprovacaoAte()));
        }

        if (StringUtils.isNotBlank(filtro.getNomeVendedor())) {
            // acessamos o nome do vendedor associado ao pedido pelo alias "v", criado anteriormente
            criteria.add(Restrictions.ilike("v.nome", filtro.getNomeVendedor(), MatchMode.ANYWHERE));
        }

        if (StringUtils.isNotBlank(filtro.getNomeCliente())) {
            // acessamos o nome do vendedor associado ao pedido pelo alias "v", criado anteriormente
            criteria.add(Restrictions.ilike("nomeRazao", filtro.getNomeCliente(), MatchMode.ANYWHERE));
        }
        if (filtro.getStatuses() != null && filtro.getStatuses().length > 0) {
            // adicionamos uma restrição "in", passando um array de constantes da enum StatusPedido
            criteria.add(Restrictions.in("statusFicha", filtro.getStatuses()));
        }

        return criteria.addOrder(Order.asc("id")).list();
    }

    public List<Ficha> filtrados(FichaFilter filtro) {
        String nome = null;

        UsuarioSistema usuarioLogado = getUsuarioLogado();

        if (usuarioLogado != null) {
            nome = usuarioLogado.getUsuario().getNome();

        }

        Session session = this.manager.unwrap(Session.class);

        Criteria criteria = session.createCriteria(Ficha.class)
                // fazemos uma associação (join) com cliente e nomeamos como "c"

                // fazemos uma associação (join) com vendedor e nomeamos como "v"
                .createAlias("gestor", "v");

        if (filtro.getNumeroDe() != null) {
            // id deve ser maior ou igual (ge = greater or equals) a filtro.numeroDe
            criteria.add(Restrictions.ge("id", filtro.getNumeroDe()));
        }

        if (filtro.getNumeroAte() != null) {
            // id deve ser menor ou igual (le = lower or equal) a filtro.numeroDe
            criteria.add(Restrictions.le("id", filtro.getNumeroAte()));
        }

        if (filtro.getDataCriacaoDe() != null) {
            criteria.add(Restrictions.ge("dataCriacao", filtro.getDataCriacaoDe()));
        }

        if (filtro.getDataCriacaoAte() != null) {
            criteria.add(Restrictions.le("dataCriacao", filtro.getDataCriacaoAte()));
        }

        if (filtro.getDataAprovacaoDe() != null) {
            criteria.add(Restrictions.ge("dataAprovacao", filtro.getDataAprovacaoDe()));
        }

        if (filtro.getDataAprovacaoAte() != null) {
            criteria.add(Restrictions.le("dataAprovacao", filtro.getDataAprovacaoAte()));
        }

        if (StringUtils.isNotBlank(filtro.getCpf())) {
            criteria.add(Restrictions.ilike("cpf", filtro.getCpf()));
        }

        if (StringUtils.isNotBlank(filtro.getCnpj())) {
            criteria.add(Restrictions.ilike("cnpj", filtro.getCnpj()));
        }

        if (StringUtils.isNotBlank(nome)) {
            // acessamos o nome do vendedor associado ao pedido pelo alias "v", criado anteriormente
            criteria.add(Restrictions.ilike("v.nome", nome, MatchMode.ANYWHERE));
        }
        if (StringUtils.isNotBlank(filtro.getNomeCliente())) {
            criteria.add(Restrictions.ilike("nomeRazao", filtro.getNomeCliente(), MatchMode.ANYWHERE));
        }

        /* if (StringUtils.isNotBlank(filtro.getNomeVendedor())) {
            // acessamos o nome do vendedor associado ao pedido pelo alias "v", criado anteriormente
            criteria.add(Restrictions.ilike("v.nome", filtro.getNomeVendedor(), MatchMode.ANYWHERE));
        }*/
        if (filtro.getStatuses() != null && filtro.getStatuses().length > 0) {
            // adicionamos uma restrição "in", passando um array de constantes da enum StatusPedido
            criteria.add(Restrictions.in("statusFicha", filtro.getStatuses()));
        }

        return criteria.addOrder(Order.asc("id")).list();
    }

    @Transactional
    public void remover(Ficha ficha) {
        try {
            ficha = porId(ficha.getId());
            manager.remove(ficha);
            manager.flush();
        } catch (PersistenceException e) {
            throw new NegocioException("Ficha não pode ser excluída.");
        }
    }

    public Ficha porId(Long id) {
        return manager.find(Ficha.class, id);

    }

    public Ficha guardar(Ficha ficha) {
        return this.manager.merge(ficha);
    }

    public Long todasFichasPendentes() {
        Session session = this.manager.unwrap(Session.class);
        org.hibernate.Query q = session.createQuery("select count(*) from Ficha where statusFicha = 'PENDENTE' and dataCriacao = current_date()");
        return (Long) q.uniqueResult();

    }

    public Long todasFichasPendentesMes() {
        Session session = this.manager.unwrap(Session.class);
        org.hibernate.Query q = session.createQuery("select count(*) from Ficha where statusFicha = 'PENDENTE' and month(dataCriacao)=month(current_date())");
        return (Long) q.uniqueResult();
    }

    public Long todasFichasPendentesAno() {
        Session session = this.manager.unwrap(Session.class);
        org.hibernate.Query q = session.createQuery("select count(*) from Ficha where statusFicha = 'PENDENTE' and year(dataCriacao)=year(current_date())");
        return (Long) q.uniqueResult();
    }

    public Long todasFichasNovoCadastro() {
        Session session = this.manager.unwrap(Session.class);
        org.hibernate.Query q = session.createQuery("select count(*) from Ficha where statusFicha = 'NOVOCADASTRO' and dataCriacao = current_date()");
        return (Long) q.uniqueResult();

    }

    public Long todasFichasNovoCadastroMes() {
        Session session = this.manager.unwrap(Session.class);
        org.hibernate.Query q = session.createQuery("select count(*) from Ficha where statusFicha = 'NOVOCADASTRO' and month(dataCriacao)=month(current_date())");
        return (Long) q.uniqueResult();
    }

    public Long todasFichasNovoCadastroAno() {
        Session session = this.manager.unwrap(Session.class);
        org.hibernate.Query q = session.createQuery("select count(*) from Ficha where statusFicha = 'NOVOCADASTRO' and year(dataCriacao)=year(current_date())");
        return (Long) q.uniqueResult();
    }

    public Long todasFichasAprovada() {
        Session session = this.manager.unwrap(Session.class);
        org.hibernate.Query q = session.createQuery("select count(*) from Ficha where statusFicha = 'APROVADO' and date(dataAprovacao)=date(current_date())");
        return (Long) q.uniqueResult();
    }

    public Long todasFichasAprovadaMes() {
        Session session = this.manager.unwrap(Session.class);
        org.hibernate.Query q = session.createQuery("select count(*) from Ficha where statusFicha = 'APROVADO' and month(dataAprovacao)=month(current_date())");
        return (Long) q.uniqueResult();
    }

    public Long todasFichasAprovadaAno() {
        Session session = this.manager.unwrap(Session.class);
        org.hibernate.Query q = session.createQuery("select count(*) from Ficha where statusFicha = 'APROVADO' and year(dataAprovacao)=year(current_date())");
        return (Long) q.uniqueResult();
    }

    public List<Ficha> todasFichas() {
        return manager.createQuery("from Ficha", Ficha.class).getResultList();
    }

    public List<Ficha> todasFichasGerarFinanceiro() {
        return manager.createQuery("from Ficha where statusFicha = 'APROVADO' and statusFichaFinanceiro = 'SEMLANCAMENTO'", Ficha.class).getResultList();
    }

    public List<Ficha> todasFichasAguardandoPagamenetoFinanceiro() {
        return manager.createQuery("from Ficha where statusFicha = 'APROVADO' and statusFichaFinanceiro = 'ABERTO'", Ficha.class).getResultList();
    }

    private UsuarioSistema getUsuarioLogado() {
        UsuarioSistema usuario = null;

        UsernamePasswordAuthenticationToken auth = (UsernamePasswordAuthenticationToken) FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal();

        if (auth != null && auth.getPrincipal() != null) {
            usuario = (UsuarioSistema) auth.getPrincipal();
        }

        return usuario;

    }

    public Long todasFichasPendentesGestor() {
        String nome = null;

        UsuarioSistema usuarioLogado = getUsuarioLogado();

        if (usuarioLogado != null) {
            nome = usuarioLogado.getUsuario().getNome();

        }

        Session session = this.manager.unwrap(Session.class);
        org.hibernate.Query q = session.createQuery("select count(*) from Ficha f where f.statusFicha = 'PENDENTE' and f.dataCriacao = current_date() and (f.gestor.nome)= :nome").setParameter("nome", nome);
        return (Long) q.uniqueResult();

    }

    public List<Ficha> todasFichasPendentesGestorMenu() {
        String nome = null;

        UsuarioSistema usuarioLogado = getUsuarioLogado();

        if (usuarioLogado != null) {
            nome = usuarioLogado.getUsuario().getNome();

        }

        return manager.createQuery("from Ficha f where f.statusFicha = 'PENDENTE' and (f.gestor.nome)= :nome").setParameter("nome", nome).getResultList();

    }

    public List<Ficha> todasFichasPendentesAdmMenu() {

        return manager.createQuery("from Ficha f where f.statusFicha = 'PENDENTE'").getResultList();

    }

    public Long todasFichasPendentesGestorMes() {
        String nome = null;

        UsuarioSistema usuarioLogado = getUsuarioLogado();

        if (usuarioLogado != null) {
            nome = usuarioLogado.getUsuario().getNome();

        }

        Session session = this.manager.unwrap(Session.class);
        org.hibernate.Query q = session.createQuery("select count(*) from Ficha f where f.statusFicha = 'PENDENTE' and month(f.dataCriacao)=month(current_date()) and (f.gestor.nome)= :nome").setParameter("nome", nome);
        return (Long) q.uniqueResult();

    }

    public Long todasFichasPendentesGestorAno() {
        String nome = null;

        UsuarioSistema usuarioLogado = getUsuarioLogado();

        if (usuarioLogado != null) {
            nome = usuarioLogado.getUsuario().getNome();

        }

        Session session = this.manager.unwrap(Session.class);
        org.hibernate.Query q = session.createQuery("select count(*) from Ficha f where f.statusFicha = 'PENDENTE' and year(f.dataCriacao)=year(current_date()) and (f.gestor.nome)= :nome").setParameter("nome", nome);
        return (Long) q.uniqueResult();

    }

    public Long todasFichasAprovadaGestor() {
        String nome = null;

        UsuarioSistema usuarioLogado = getUsuarioLogado();

        if (usuarioLogado != null) {
            nome = usuarioLogado.getUsuario().getNome();

        }

        Session session = this.manager.unwrap(Session.class);
        org.hibernate.Query q = session.createQuery("select count(*) from Ficha f where f.statusFicha = 'APROVADO' and date(f.dataAprovacao)=date(current_date()) and (f.gestor.nome)= :nome").setParameter("nome", nome);
        return (Long) q.uniqueResult();

    }

    public Long todasFichasAprovadaGestorMes() {
        String nome = null;

        UsuarioSistema usuarioLogado = getUsuarioLogado();

        if (usuarioLogado != null) {
            nome = usuarioLogado.getUsuario().getNome();

        }

        Session session = this.manager.unwrap(Session.class);
        org.hibernate.Query q = session.createQuery("select count(*) from Ficha f where f.statusFicha = 'APROVADO' and month(f.dataAprovacao)=month(current_date()) and (f.gestor.nome)= :nome").setParameter("nome", nome);
        return (Long) q.uniqueResult();

    }

    public Long todasFichasAprovadaGestorAno() {
        String nome = null;

        UsuarioSistema usuarioLogado = getUsuarioLogado();

        if (usuarioLogado != null) {
            nome = usuarioLogado.getUsuario().getNome();

        }

        Session session = this.manager.unwrap(Session.class);
        org.hibernate.Query q = session.createQuery("select count(*) from Ficha f where f.statusFicha = 'APROVADO' and year(f.dataAprovacao)=year(current_date()) and (f.gestor.nome)= :nome").setParameter("nome", nome);
        return (Long) q.uniqueResult();

    }

    public Long todasFichasNovoCadastroGestor() {
        String nome = null;

        UsuarioSistema usuarioLogado = getUsuarioLogado();

        if (usuarioLogado != null) {
            nome = usuarioLogado.getUsuario().getNome();

        }

        Session session = this.manager.unwrap(Session.class);
        org.hibernate.Query q = session.createQuery("select count(*) from Ficha f where f.statusFicha = 'NOVOCADASTRO' and f.dataCriacao = current_date() and (f.gestor.nome)= :nome").setParameter("nome", nome);
        return (Long) q.uniqueResult();

    }

    public Long todasFichasNovoCadastroGestorMes() {
        String nome = null;

        UsuarioSistema usuarioLogado = getUsuarioLogado();

        if (usuarioLogado != null) {
            nome = usuarioLogado.getUsuario().getNome();

        }

        Session session = this.manager.unwrap(Session.class);
        org.hibernate.Query q = session.createQuery("select count(*) from Ficha f where f.statusFicha = 'NOVOCADASTRO' and month(f.dataCriacao)=month(current_date()) and (f.gestor.nome)= :nome").setParameter("nome", nome);
        return (Long) q.uniqueResult();

    }

    public Long todasFichasNovoCadastroGestorAno() {
        String nome = null;

        UsuarioSistema usuarioLogado = getUsuarioLogado();

        if (usuarioLogado != null) {
            nome = usuarioLogado.getUsuario().getNome();

        }

        Session session = this.manager.unwrap(Session.class);
        org.hibernate.Query q = session.createQuery("select count(*) from Ficha f where f.statusFicha = 'NOVOCADASTRO' and year(f.dataCriacao)=year(current_date()) and (f.gestor.nome)= :nome").setParameter("nome", nome);
        return (Long) q.uniqueResult();

    }

}
