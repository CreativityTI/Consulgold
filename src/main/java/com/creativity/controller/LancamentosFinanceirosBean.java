/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.creativity.controller;

import com.creativity.Filter.FinanceiroFilter;
import com.creativity.model.BaixaFinanceiro;
import com.creativity.model.Ficha;
import com.creativity.model.Financeiro;
import com.creativity.model.StatusFinanceiro;
import com.creativity.model.TipoBaixa;
import com.creativity.model.Usuario;
import com.creativity.repository.Fichas;
import com.creativity.repository.LancamentosFinanceiros;
import com.creativity.repository.Usuarios;
import com.creativity.service.LancamentoFinanceiroService;
import com.creativity.service.NegocioException;
import com.creativity.util.FacesUtil;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
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
public class LancamentosFinanceirosBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private Fichas fichas;

    private Ficha ficha;

    @Inject
    private LancamentoFinanceiroService lancamentoFinanceiroService;

    @Inject
    private LancamentosFinanceiros lancamentosFinanceiros;

    private List<Financeiro> lancamentos;
    private List<Financeiro> lancamentosPorGestor;
    private Financeiro financeiroSelecionado;
    private Financeiro financeiro;
    private FinanceiroFilter filtro;

    private List<Usuario> usuariosGestor;

    @Inject
    private Usuarios usuarios;

    public LancamentosFinanceirosBean() {
        filtro = new FinanceiroFilter();

    }

    public void prepararCadastro() {
        filtro = new FinanceiroFilter();
        this.usuariosGestor = this.usuarios.todosGestores();

        if (this.financeiro == null) {
            this.financeiro = new Financeiro();
        }

    }

    public void pesquisar() {
        lancamentos = lancamentosFinanceiros.filtradosAguardandoPagamento(filtro);
        lancamentosPorGestor = lancamentosFinanceiros.filtradosAguardandoPagamentoPorGestor(filtro);

    }

    public void salvar() {

        FacesContext context = FacesContext.getCurrentInstance();

        try {
            this.lancamentoFinanceiroService.salvar(financeiro);
            context.addMessage(null, new FacesMessage(" Manutenção realizada com sucesso!"));
            this.financeiro = new Financeiro();

        } catch (NegocioException e) {

            FacesMessage mensagem = new FacesMessage(e.getMessage());
            mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
            context.addMessage(null, mensagem);
        }
    }

    public FinanceiroFilter getFiltro() {
        return filtro;
    }

    public void setFiltro(FinanceiroFilter filtro) {
        this.filtro = filtro;
    }

    public List<Financeiro> getLancamentos() {
        return lancamentos;
    }

    public void setLancamentos(List<Financeiro> lancamentos) {
        this.lancamentos = lancamentos;
    }

    public Financeiro getFinanceiroSelecionado() {
        return financeiroSelecionado;
    }

    public void setFinanceiroSelecionado(Financeiro financeiroSelecionado) {
        this.financeiroSelecionado = financeiroSelecionado;
    }

    public StatusFinanceiro[] getStatusDocFinanceiro() {
        return StatusFinanceiro.values();

    }

    public List<Usuario> getUsuariosGestor() {
        return usuariosGestor;
    }

    public void setUsuariosGestor(List<Usuario> usuariosGestor) {
        this.usuariosGestor = usuariosGestor;
    }

    public List<Financeiro> getLancamentosPorGestor() {
        return lancamentosPorGestor;
    }

    public void setLancamentosPorGestor(List<Financeiro> lancamentosPorGestor) {
        this.lancamentosPorGestor = lancamentosPorGestor;
    }

    public Financeiro getFinanceiro() {
        return financeiro;
    }

    public void setFinanceiro(Financeiro financeiro) {
        this.financeiro = financeiro;
    }

    public LancamentoFinanceiroService getLancamentoFinanceiroService() {
        return lancamentoFinanceiroService;
    }

    public void setLancamentoFinanceiroService(LancamentoFinanceiroService lancamentoFinanceiroService) {
        this.lancamentoFinanceiroService = lancamentoFinanceiroService;
    }

    public Ficha getFicha() {
        return ficha;
    }

    public void setFicha(Ficha ficha) {
        this.ficha = ficha;
    }

    public boolean isEditandoPago() {
        return this.financeiro.getStatusFinanceiro() == financeiro.getStatusFinanceiro().PAGO;

    }

    public BigDecimal getValorTotal() {
        BigDecimal vlTotal = new BigDecimal(BigInteger.ZERO);

        for (Financeiro item : getLancamentos()) {

            vlTotal = vlTotal.add(item.getValor());
        }

        return vlTotal;
    }

    public BigDecimal getValorTotalRecebido() {
        BigDecimal vlTotal = new BigDecimal(BigInteger.ZERO);

        for (Financeiro item : getLancamentos()) {

            vlTotal = vlTotal.add(item.getValorTotalRecebido());
        }

        return vlTotal;
    }
    
    

    public BigDecimal getValorTotalPago() {
        BigDecimal vlTotal = new BigDecimal(BigInteger.ZERO);

        for (Financeiro item : getLancamentos()) {
            if (this.financeiro.getStatusFinanceiro() == financeiro.getStatusFinanceiro().PAGO) {
                vlTotal = vlTotal.add(item.getValor());
            }
            vlTotal = vlTotal.add(item.getValor());
        }

        return vlTotal;
    }

    public BigDecimal getValorTotalPorGestor() {
        BigDecimal vlTotal = new BigDecimal(BigInteger.ZERO);

        for (Financeiro item : lancamentosPorGestor) {
            vlTotal = vlTotal.add(item.getValor());
        }
        return vlTotal;
    }

    public BigDecimal getValorTotalPorGestorRecebido() {
        BigDecimal vlTotal = new BigDecimal(BigInteger.ZERO);

        for (Financeiro item : lancamentosPorGestor) {
            vlTotal = vlTotal.add(item.getValorTotalRecebido());
        }
        return vlTotal;
    }

}
