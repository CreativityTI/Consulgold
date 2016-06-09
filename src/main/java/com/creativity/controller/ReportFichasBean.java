/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.creativity.controller;

import com.creativity.model.Ficha;
import com.creativity.model.Financeiro;
import com.creativity.model.Mensagem;
import com.creativity.repository.Fichas;
import com.creativity.repository.LancamentosFinanceiros;
import com.creativity.repository.Mensagens;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author rafael.lima
 */
@Named
@ViewScoped
public class ReportFichasBean implements Serializable {

    private List<Mensagem> mensagemsGestor;
    private List<Mensagem> mensagensTodos;

    @Inject
    private Mensagens repositorioMensagens;

    private Ficha ficha;

    private Ficha fichaSelecionada;

    @Inject
    private Fichas fichas;

    @Inject
    private LancamentosFinanceiros lancamentosFinanceiros;

    private List<Ficha> fichasPendentesMenu;

    private Long fichasPendentes;
    private Long fichasPendentesMes;
    private Long fichasPendentesAno;

    private Long fichasAprovadas;
    private Long fichasAprovadasMes;
    private Long fichasAprovadasAno;

    private Long fichasNovas;
    private Long fichasNovasMes;
    private Long fichasNovasAno;

    private Long fichasPendentesGestor;
    private Long fichasPendentesGestorMes;
    private Long fichasPendentesGestorAno;

    private Long fichasAprovadaGestor;
    private Long fichasAprovadaGestorMes;
    private Long fichasAprovadaGestorAno;

    private Long fichasNovoCadastroGestor;
    private Long fichasNovoCadastroGestorMes;
    private Long fichasNovoCadastroGestorAno;

    private List<Ficha> fichasPendentesGestorMenu;
    private List<Ficha> fichasPendentesAdmMenu;
    private List<Financeiro> financeiroAbertoPorGestor;
    private BigDecimal financeiroAbertoTotal;
    private BigDecimal financeiroPagoTotal;
    private BigDecimal financeiroSaldoTotal;

    public void prepararReports() {
        
        
        this.ficha = new Ficha();

        this.mensagemsGestor = repositorioMensagens.mensagemGestor();
        this.mensagensTodos = repositorioMensagens.todasMensagens();

        this.financeiroAbertoPorGestor = lancamentosFinanceiros.financeiroAbertoPorGestor();
        this.financeiroAbertoTotal = lancamentosFinanceiros.valorAbertoTotal();
        this.financeiroPagoTotal = lancamentosFinanceiros.valorPagoTotal();
        //this.financeiroSaldoTotal = lancamentosFinanceiros.valorSaldoTotal();

        this.fichasPendentesMenu = fichas.todasFichasPendentesAdmMenu();

        this.fichasPendentes = fichas.todasFichasPendentes();
        this.fichasPendentesMes = fichas.todasFichasPendentesMes();
        this.fichasPendentesAno = fichas.todasFichasPendentesAno();

        this.fichasAprovadas = fichas.todasFichasAprovada();
        this.fichasAprovadasMes = fichas.todasFichasAprovadaMes();
        this.fichasAprovadasAno = fichas.todasFichasAprovadaAno();

        this.fichasNovas = fichas.todasFichasNovoCadastro();
        this.fichasNovasMes = fichas.todasFichasNovoCadastroMes();
        this.fichasNovasAno = fichas.todasFichasNovoCadastroAno();

        fichasPendentesGestor = fichas.todasFichasPendentesGestor();
        fichasPendentesGestorMes = fichas.todasFichasPendentesGestorMes();
        fichasPendentesGestorAno = fichas.todasFichasPendentesGestorAno();

        fichasAprovadaGestor = fichas.todasFichasAprovadaGestor();
        fichasAprovadaGestorMes = fichas.todasFichasAprovadaGestorMes();
        fichasAprovadaGestorAno = fichas.todasFichasAprovadaGestorAno();

        fichasNovoCadastroGestor = fichas.todasFichasNovoCadastroGestor();
        fichasNovoCadastroGestorMes = fichas.todasFichasNovoCadastroGestorMes();
        fichasNovoCadastroGestorAno = fichas.todasFichasNovoCadastroGestorAno();

        fichasPendentesGestorMenu = fichas.todasFichasPendentesGestorMenu();
        fichasPendentesAdmMenu = fichas.todasFichasPendentesAdmMenu();

    }

    public Long getFichasPendentes() {
        return fichasPendentes;
    }

    public void setFichasPendentes(Long fichasPendentes) {
        this.fichasPendentes = fichasPendentes;
    }

    public Long getFichasPendentesMes() {
        return fichasPendentesMes;
    }

    public void setFichasPendentesMes(Long fichasPendentesMes) {
        this.fichasPendentesMes = fichasPendentesMes;
    }

    public Long getFichasPendentesAno() {
        return fichasPendentesAno;
    }

    public void setFichasPendentesAno(Long fichasPendentesAno) {
        this.fichasPendentesAno = fichasPendentesAno;
    }

    public Long getFichasAprovadas() {
        return fichasAprovadas;
    }

    public void setFichasAprovadas(Long fichasAprovadas) {
        this.fichasAprovadas = fichasAprovadas;
    }

    public Long getFichasAprovadasMes() {
        return fichasAprovadasMes;
    }

    public void setFichasAprovadasMes(Long fichasAprovadasMes) {
        this.fichasAprovadasMes = fichasAprovadasMes;
    }

    public Long getFichasAprovadasAno() {
        return fichasAprovadasAno;
    }

    public void setFichasAprovadasAno(Long fichasAprovadasAno) {
        this.fichasAprovadasAno = fichasAprovadasAno;
    }

    public Long getFichasNovas() {
        return fichasNovas;
    }

    public void setFichasNovas(Long fichasNovas) {
        this.fichasNovas = fichasNovas;
    }

    public Long getFichasNovasMes() {
        return fichasNovasMes;
    }

    public void setFichasNovasMes(Long fichasNovasMes) {
        this.fichasNovasMes = fichasNovasMes;
    }

    public Long getFichasNovasAno() {
        return fichasNovasAno;
    }

    public void setFichasNovasAno(Long fichasNovasAno) {
        this.fichasNovasAno = fichasNovasAno;
    }

    public Long getFichasPendentesGestor() {
        return fichasPendentesGestor;
    }

    public void setFichasPendentesGestor(Long fichasPendentesGestor) {
        this.fichasPendentesGestor = fichasPendentesGestor;
    }

    public Long getFichasPendentesGestorMes() {
        return fichasPendentesGestorMes;
    }

    public void setFichasPendentesGestorMes(Long fichasPendentesGestorMes) {
        this.fichasPendentesGestorMes = fichasPendentesGestorMes;
    }

    public Long getFichasPendentesGestorAno() {
        return fichasPendentesGestorAno;
    }

    public void setFichasPendentesGestorAno(Long fichasPendentesGestorAno) {
        this.fichasPendentesGestorAno = fichasPendentesGestorAno;
    }

    public Long getFichasAprovadaGestor() {
        return fichasAprovadaGestor;
    }

    public void setFichasAprovadaGestor(Long fichasAprovadaGestor) {
        this.fichasAprovadaGestor = fichasAprovadaGestor;
    }

    public Long getFichasAprovadaGestorMes() {
        return fichasAprovadaGestorMes;
    }

    public void setFichasAprovadaGestorMes(Long fichasAprovadaGestorMes) {
        this.fichasAprovadaGestorMes = fichasAprovadaGestorMes;
    }

    public Long getFichasAprovadaGestorAno() {
        return fichasAprovadaGestorAno;
    }

    public void setFichasAprovadaGestorAno(Long fichasAprovadaGestorAno) {
        this.fichasAprovadaGestorAno = fichasAprovadaGestorAno;
    }

    public Long getFichasNovoCadastroGestor() {
        return fichasNovoCadastroGestor;
    }

    public void setFichasNovoCadastroGestor(Long fichasNovoCadastroGestor) {
        this.fichasNovoCadastroGestor = fichasNovoCadastroGestor;
    }

    public Long getFichasNovoCadastroGestorMes() {
        return fichasNovoCadastroGestorMes;
    }

    public void setFichasNovoCadastroGestorMes(Long fichasNovoCadastroGestorMes) {
        this.fichasNovoCadastroGestorMes = fichasNovoCadastroGestorMes;
    }

    public Long getFichasNovoCadastroGestorAno() {
        return fichasNovoCadastroGestorAno;
    }

    public void setFichasNovoCadastroGestorAno(Long fichasNovoCadastroGestorAno) {
        this.fichasNovoCadastroGestorAno = fichasNovoCadastroGestorAno;
    }

    public List<Ficha> getFichasPendentesGestorMenu() {
        return fichasPendentesGestorMenu;
    }

    public void setFichasPendentesGestorMenu(List<Ficha> fichasPendentesGestorMenu) {
        this.fichasPendentesGestorMenu = fichasPendentesGestorMenu;
    }

    public List<Ficha> getFichasPendentesAdmMenu() {
        return fichasPendentesAdmMenu;
    }

    public void setFichasPendentesAdmMenu(List<Ficha> fichasPendentesAdmMenu) {
        this.fichasPendentesAdmMenu = fichasPendentesAdmMenu;
    }

    public Ficha getFicha() {
        return ficha;
    }

    public void setFicha(Ficha ficha) {
        this.ficha = ficha;
    }

    public Ficha getFichaSelecionada() {
        return fichaSelecionada;
    }

    public List<Ficha> getFichasPendentesMenu() {
        return fichasPendentesMenu;
    }

    public void setFichasPendentesMenu(List<Ficha> fichasPendentesMenu) {
        this.fichasPendentesMenu = fichasPendentesMenu;
    }

    public void setFichaSelecionada(Ficha fichaSelecionada) {
        this.fichaSelecionada = fichaSelecionada;
    }

    public Fichas getFichas() {
        return fichas;
    }

    public void setFichas(Fichas fichas) {
        this.fichas = fichas;
    }

    public List<Financeiro> getFinanceiroAbertoPorGestor() {
        return financeiroAbertoPorGestor;
    }

    public void setFinanceiroAbertoPorGestor(List<Financeiro> financeiroAbertoPorGestor) {
        this.financeiroAbertoPorGestor = financeiroAbertoPorGestor;
    }

    public BigDecimal getFinanceiroAbertoTotal() {
        return financeiroAbertoTotal;
    }

    public void setFinanceiroAbertoTotal(BigDecimal financeiroAbertoTotal) {
        this.financeiroAbertoTotal = financeiroAbertoTotal;
    }

    public BigDecimal getFinanceiroPagoTotal() {
        return financeiroPagoTotal;
    }

    public void setFinanceiroPagoTotal(BigDecimal financeiroPagoTotal) {
        this.financeiroPagoTotal = financeiroPagoTotal;
    }

    public BigDecimal getFinanceiroSaldoTotal() {
        return financeiroSaldoTotal;
    }

    public void setFinanceiroSaldoTotal(BigDecimal financeiroSaldoTotal) {
        this.financeiroSaldoTotal = financeiroSaldoTotal;
    }

    public List<Mensagem> getMensagemsGestor() {
        return mensagemsGestor;
    }

    public void setMensagemsGestor(List<Mensagem> mensagemsGestor) {
        this.mensagemsGestor = mensagemsGestor;
    }

    public List<Mensagem> getMensagensTodos() {
        return mensagensTodos;
    }

    public void setMensagensTodos(List<Mensagem> mensagensTodos) {
        this.mensagensTodos = mensagensTodos;
    }



    public BigDecimal getValorTotalPorGestor() {
        BigDecimal vlTotal = new BigDecimal(BigInteger.ZERO);

        for (Financeiro item : financeiroAbertoPorGestor) {
            vlTotal = vlTotal.add(item.getValor());
        }
        return vlTotal;
    }

}
