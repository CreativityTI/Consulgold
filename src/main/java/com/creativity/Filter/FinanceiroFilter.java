/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.creativity.Filter;

import com.creativity.model.StatusFinanceiro;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author rafael.lima
 */
public class FinanceiroFilter implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long numeroDe;
    private Long numeroAte;
    private Date dataCriacaoDe;
    private Date dataCriacaoAte;
    
    private String nomeVendedor;
    private String nomeCliente;
    private Long gestor;
    private StatusFinanceiro[] statusDocFinanceiro;


    public Long getNumeroDe() {
        return numeroDe;
    }

    public void setNumeroDe(Long numeroDe) {
        this.numeroDe = numeroDe;
    }

    public Long getNumeroAte() {
        return numeroAte;
    }

    public void setNumeroAte(Long numeroAte) {
        this.numeroAte = numeroAte;
    }

    public Date getDataCriacaoDe() {
        return dataCriacaoDe;
    }

    public void setDataCriacaoDe(Date dataCriacaoDe) {
        this.dataCriacaoDe = dataCriacaoDe;
    }

    public Date getDataCriacaoAte() {
        return dataCriacaoAte;
    }

    public void setDataCriacaoAte(Date dataCriacaoAte) {
        this.dataCriacaoAte = dataCriacaoAte;
    }

    public String getNomeVendedor() {
        return nomeVendedor;
    }

    public void setNomeVendedor(String nomeVendedor) {
        this.nomeVendedor = nomeVendedor;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public StatusFinanceiro[] getStatusDocFinanceiro() {
        return statusDocFinanceiro;
    }

    public void setStatusDocFinanceiro(StatusFinanceiro[] statusDocFinanceiro) {
        this.statusDocFinanceiro = statusDocFinanceiro;
    }

    public Long getGestor() {
        return gestor;
    }

    public void setGestor(Long gestor) {
        this.gestor = gestor;
    }


}