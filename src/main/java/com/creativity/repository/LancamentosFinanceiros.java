/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.creativity.repository;

import com.creativity.Filter.FinanceiroFilter;
import com.creativity.model.Financeiro;
import com.creativity.model.StatusFinanceiro;
import com.creativity.security.UsuarioSistema;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

/**
 *
 * @author rafael.lima
 */
public class LancamentosFinanceiros implements Serializable {

    private static final long serialVersionUID = 1L;

    private EntityManager manager;

    private Financeiro financeiro;

    @Inject
    public LancamentosFinanceiros(EntityManager manager) {
        this.manager = manager;
    }

    public Financeiro porId(Long id) {
        return manager.find(Financeiro.class, id);

    }

    public Financeiro guardar(Financeiro financeiro) {
        return this.manager.merge(financeiro);
    }

    public List<Financeiro> todosLancamentos(FinanceiroFilter filtro) {
        return manager.createQuery("from Financeiro", Financeiro.class).getResultList();
    }

    public List<Financeiro> filtradosAguardandoPagamento(FinanceiroFilter filtro) {

        Session session = this.manager.unwrap(Session.class);

        Criteria criteria = session.createCriteria(Financeiro.class)
                // fazemos uma associação (join) com cliente e nomeamos como "c"

                // fazemos uma associação (join) com vendedor e nomeamos como "v"
                .createAlias("ficha", "f");

        if (filtro.getNumeroDe() != null) {
            // id deve ser maior ou igual (ge = greater or equals) a filtro.numeroDe
            criteria.add(Restrictions.ge("id", filtro.getNumeroDe()));
        }

        if (filtro.getNumeroAte() != null) {
            // id deve ser menor ou igual (le = lower or equal) a filtro.numeroDe
            criteria.add(Restrictions.le("id", filtro.getNumeroAte()));
        }

        if (filtro.getDataCriacaoDe() != null) {
            criteria.add(Restrictions.ge("dataLancamento", filtro.getDataCriacaoDe()));
        }

        if (filtro.getDataCriacaoAte() != null) {
            criteria.add(Restrictions.le("dataLancamento", filtro.getDataCriacaoAte()));
        }

        if (StringUtils.isNotBlank(filtro.getNomeCliente())) {
            // acessamos o nome do vendedor associado ao pedido pelo alias "v", criado anteriormente
            criteria.add(Restrictions.ilike("f.nomeRazao", filtro.getNomeCliente(), MatchMode.ANYWHERE));
        }

        if (StringUtils.isNotBlank(filtro.getNomeCliente())) {
            // acessamos o nome do vendedor associado ao pedido pelo alias "v", criado anteriormente
            criteria.add(Restrictions.ilike("f.nomeRazao", filtro.getNomeCliente(), MatchMode.ANYWHERE));
        }

        if (filtro.getStatusDocFinanceiro() != null && filtro.getStatusDocFinanceiro().length > 3) {
            // adicionamos uma restrição "in", passando um array de constantes da enum StatusPedido
            criteria.add(Restrictions.in("statusFinanceiro", filtro.getStatusDocFinanceiro()));
        }

        if (filtro.getGestor() != null) {
            // id deve ser maior ou igual (ge = greater or equals) a filtro.numeroDe
            criteria.add(Restrictions.eq("f.gestor.id", filtro.getGestor()));
        }

        return criteria.addOrder(Order.asc("id")).list();
    }

    public List<Financeiro> filtradosAguardandoPagamentoPorGestor(FinanceiroFilter filtro) {

        Long usuarioGestorLogado = null;

        UsuarioSistema usuarioLogado = getUsuarioLogado();

        if (usuarioLogado != null) {
            usuarioGestorLogado = usuarioLogado.getUsuario().getId();

        }

        Session session = this.manager.unwrap(Session.class);

        Criteria criteria = session.createCriteria(Financeiro.class)
                // fazemos uma associação (join) com cliente e nomeamos como "c"

                // fazemos uma associação (join) com vendedor e nomeamos como "v"
                .createAlias("ficha", "f")
                .add(Restrictions.eq("f.gestor.id", Long.valueOf(usuarioGestorLogado)));

        if (filtro.getNumeroDe() != null) {
            // id deve ser maior ou igual (ge = greater or equals) a filtro.numeroDe
            criteria.add(Restrictions.ge("id", filtro.getNumeroDe()));
        }

        if (filtro.getNumeroAte() != null) {
            // id deve ser menor ou igual (le = lower or equal) a filtro.numeroDe
            criteria.add(Restrictions.le("id", filtro.getNumeroAte()));
        }

        if (filtro.getDataCriacaoDe() != null) {
            criteria.add(Restrictions.ge("dataLancamento", filtro.getDataCriacaoDe()));
        }

        if (filtro.getDataCriacaoAte() != null) {
            criteria.add(Restrictions.le("dataLancamento", filtro.getDataCriacaoAte()));
        }

        if (filtro.getStatusDocFinanceiro() != null && filtro.getStatusDocFinanceiro().length > 0) {
            // adicionamos uma restrição "in", passando um array de constantes da enum StatusPedido
            criteria.add(Restrictions.in("statusFinanceiro", filtro.getStatusDocFinanceiro()));
        }

        if (StringUtils.isNotBlank(filtro.getNomeCliente())) {
            // acessamos o nome do vendedor associado ao pedido pelo alias "v", criado anteriormente
            criteria.add(Restrictions.ilike("f.nomeRazao", filtro.getNomeCliente(), MatchMode.ANYWHERE));
        }

        return criteria.addOrder(Order.asc("id")).list();
    }

    public List<Financeiro> financeiroAbertoPorGestor() {

        Long usuarioGestorLogado = null;

        UsuarioSistema usuarioLogado = getUsuarioLogado();

        if (usuarioLogado != null) {
            usuarioGestorLogado = usuarioLogado.getUsuario().getId();

        }

        Session session = this.manager.unwrap(Session.class);

        Criteria criteria = session.createCriteria(Financeiro.class)
                // fazemos uma associação (join) com cliente e nomeamos como "c"

                // fazemos uma associação (join) com vendedor e nomeamos como "v"
                .createAlias("ficha", "f")
                .add(Restrictions.eq("f.gestor.id", Long.valueOf(usuarioGestorLogado)))
                .add(Restrictions.like("statusFinanceiro", StatusFinanceiro.ABERTO));

        return criteria.addOrder(Order.asc("id")).list();

    }

    public BigDecimal valorAbertoTotal() {
        Session session = this.manager.unwrap(Session.class);
        org.hibernate.Query q = session.createSQLQuery("select sum(valor) from financeiro where statusFinanceiro = 'ABERTO'");
        return (BigDecimal) q.uniqueResult();
    }

    public BigDecimal valorPagoTotal() {
        Session session = this.manager.unwrap(Session.class);
        org.hibernate.Query q = session.createSQLQuery("select sum(valorTotalRecebido) from financeiro");
        return (BigDecimal) q.uniqueResult();
    }

    public BigDecimal valorSaldoTotal() {
        Session session = this.manager.unwrap(Session.class);
        org.hibernate.Query q = session.createSQLQuery("select   \n"
                + "((select sum(valor) from financeiro where statusFinanceiro = 'ABERTO')\n"
                + "- (select sum(valorTotalRecebido) from financeiro where statusFinanceiro = 'PAGO')) from financeiro\n"
                + "group by valor");
        return (BigDecimal) q.uniqueResult();
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
