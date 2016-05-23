/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.creativity.controller;

import com.creativity.model.BaixaFinanceiro;
import com.creativity.model.Financeiro;
import com.creativity.model.StatusFinanceiro;
import com.creativity.model.TipoBaixa;
import com.creativity.service.LancamentoFinanceiroService;
import com.creativity.util.FacesUtil;
import java.io.Serializable;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author rafael.lima
 */
@Named
@javax.faces.view.ViewScoped
public class BaixaFinanceiroBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private LancamentoFinanceiroService lancamentoFinanceiroService;

    private Financeiro financeiro;

    private BaixaFinanceiro baixaFinanceiro;

    private BaixaFinanceiro baixaFinanceiroSelecionada;

    public void prepararCadastro() {

        if (this.baixaFinanceiro == null) {
            this.baixaFinanceiro = new BaixaFinanceiro();
        }

    }

    public void adicionarPagamento() {
        baixaFinanceiro.setFinanceiro(financeiro);
        this.financeiro.getBaixasFinanceiro().add(baixaFinanceiro);
        financeiro.recalcularValorTotalRecebido();
        if (financeiro.getValorTotalRecebido().compareTo(financeiro.getValor()) >= 0) {
            baixaFinanceiro.setTipoBaixa(TipoBaixa.TOTAL);
            financeiro.setStatusFinanceiro(StatusFinanceiro.PAGO);
            this.lancamentoFinanceiroService.salvar(this.financeiro);
            FacesUtil.addInfoMessage("Pagamento Total Realizado!");
        } else {
            baixaFinanceiro.setTipoBaixa(TipoBaixa.PARCIAL);
            this.lancamentoFinanceiroService.salvar(this.financeiro);
            FacesUtil.exibirMensagemAlerta("Pagamento relizado PARCIAL!");
            baixaFinanceiro = new BaixaFinanceiro();
        }

    }

    public void excluirPagamento() {
        financeiro.getBaixasFinanceiro().remove(baixaFinanceiroSelecionada);
        financeiro.setStatusFinanceiro(StatusFinanceiro.ABERTO);
        financeiro.recalcularValorTotalRecebido();
        this.lancamentoFinanceiroService.salvar(this.financeiro);
        FacesUtil.addInfoMessage("Baixa Estornada com sucesso!");
        this.baixaFinanceiroSelecionada = new BaixaFinanceiro();
    }

    public Financeiro getFinanceiro() {
        return financeiro;
    }

    public void setFinanceiro(Financeiro financeiro) {
        this.financeiro = financeiro;
    }

    public BaixaFinanceiro getBaixaFinanceiro() {
        return baixaFinanceiro;
    }

    public void setBaixaFinanceiro(BaixaFinanceiro baixaFinanceiro) {
        this.baixaFinanceiro = baixaFinanceiro;
    }

    public BaixaFinanceiro getBaixaFinanceiroSelecionada() {
        return baixaFinanceiroSelecionada;
    }

    public void setBaixaFinanceiroSelecionada(BaixaFinanceiro baixaFinanceiroSelecionada) {
        this.baixaFinanceiroSelecionada = baixaFinanceiroSelecionada;
    }
    
     public Boolean isStatusPago() {
        return this.financeiro.getStatusFinanceiro() == this.financeiro.getStatusFinanceiro().PAGO;
    }


}
