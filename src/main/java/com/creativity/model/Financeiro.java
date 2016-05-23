/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.creativity.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author rafael.lima
 */
@Entity
@Table(name = "FINANCEIRO")
public class Financeiro implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;

    private BigDecimal valor = BigDecimal.ZERO;

    @Column(columnDefinition = "DATE")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date dataLancamento;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private StatusFinanceiro statusFinanceiro = StatusFinanceiro.ABERTO;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ficha_id", nullable = false, unique = true)
    private Ficha ficha;

    @OneToMany(mappedBy = "financeiro",orphanRemoval = true, cascade = CascadeType.ALL)
    private List<BaixaFinanceiro> baixasFinanceiro = new ArrayList<>();

    private BigDecimal valorTotalRecebido = BigDecimal.ZERO;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Date getDataLancamento() {
        return dataLancamento;
    }

    public void setDataLancamento(Date dataLancamento) {
        this.dataLancamento = dataLancamento;
    }

    public Ficha getFicha() {
        return ficha;
    }

    public void setFicha(Ficha ficha) {
        this.ficha = ficha;
    }

    public StatusFinanceiro getStatusFinanceiro() {
        return statusFinanceiro;
    }

    public void setStatusFinanceiro(StatusFinanceiro statusFinanceiro) {
        this.statusFinanceiro = statusFinanceiro;
    }

    public List<BaixaFinanceiro> getBaixasFinanceiro() {
        return baixasFinanceiro;
    }

    public void setBaixasFinanceiro(List<BaixaFinanceiro> baixasFinanceiro) {
        this.baixasFinanceiro = baixasFinanceiro;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 41 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Financeiro other = (Financeiro) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    public BigDecimal getValorTotalRecebido() {
        return valorTotalRecebido;

    }

    public void setValorTotalRecebido(BigDecimal valorTotalRecebido) {

        this.valorTotalRecebido = valorTotalRecebido;
    }

    public void recalcularValorTotalRecebido() {
        BigDecimal vlTotal = new BigDecimal(BigInteger.ZERO);
        for (BaixaFinanceiro item : getBaixasFinanceiro()) {
            vlTotal = vlTotal.add(item.getValorPagamento());
        }
        this.setValorTotalRecebido(vlTotal);
    }

   

    

}
