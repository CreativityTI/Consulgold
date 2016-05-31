/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.creativity.controller;

import com.creativity.Filter.FichaFilter;
import com.creativity.model.Ficha;
import com.creativity.model.StatusFicha;
import com.creativity.model.StatusFinanceiro;
import com.creativity.model.Usuario;
import com.creativity.repository.Fichas;
import com.creativity.repository.Usuarios;
import com.creativity.util.FacesUtil;
import java.awt.Font;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Named;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.IndexedColors;

/**
 *
 * @author rafael.lima
 */
@Named
@javax.faces.view.ViewScoped
public class PesquisaFichasBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private Fichas fichas;

    private FichaFilter filtro;
    private List<Ficha> fichasFiltrados;
    private List<Ficha> fichasFiltradasAdmin;
    private List<Ficha> fichasFiltradasAdminGerarFinanceiro;

    private List<Ficha> fichasNovo;

    private Ficha fichaSelecionada;
    private Ficha ficha;

    private List<Usuario> usuariosGestor;

    @Inject
    private Usuarios usuarios;

    public void prepararCadastro() {        
        this.usuariosGestor = this.usuarios.todosGestores();
        this.fichasNovo = fichas.todasFichas();
        this.fichasFiltradasAdminGerarFinanceiro = fichas.todasFichasGerarFinanceiro();

    }

    public void excluir() {
        fichas.remover(fichaSelecionada);
        fichasFiltrados.remove(fichaSelecionada);
        fichasFiltradasAdmin.remove(fichaSelecionada);

        FacesUtil.addInfoMessage("Ficha " + fichaSelecionada.getId()
                + " excluída com sucesso.");
    }

    public PesquisaFichasBean() {
        filtro = new FichaFilter();
        fichasFiltrados = new ArrayList<>();
        fichasFiltradasAdmin = new ArrayList<>();

    }

    public void pesquisar() {
        fichasFiltradasAdmin = fichas.filtradosAdmin(filtro);
        fichasFiltrados = fichas.filtrados(filtro);

    }

    public void posProcessarXls(Object documento) {
        HSSFWorkbook planilha = (HSSFWorkbook) documento;
        HSSFSheet folha = planilha.getSheetAt(0);
        HSSFRow cabecalho = folha.getRow(0);
        HSSFCellStyle estiloCelula = planilha.createCellStyle();
        HSSFFont fonteCabecalho = planilha.createFont();

        fonteCabecalho.setColor(IndexedColors.BLACK.getIndex());
        fonteCabecalho.setBold(true);
        fonteCabecalho.setFontHeightInPoints((short) 10);

        estiloCelula.setFont(fonteCabecalho);
        estiloCelula.setFillForegroundColor(IndexedColors.GREY_50_PERCENT.getIndex());
        estiloCelula.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

        for (int i = 0; i < cabecalho.getPhysicalNumberOfCells(); i++) {
            cabecalho.getCell(i).setCellStyle(estiloCelula);
        }
    }

    public List<Ficha> getFichasFiltradasAdminGerarFinanceiro() {
        return fichasFiltradasAdminGerarFinanceiro;
    }

    public void setFichasFiltradasAdminGerarFinanceiro(List<Ficha> fichasFiltradasAdminGerarFinanceiro) {
        this.fichasFiltradasAdminGerarFinanceiro = fichasFiltradasAdminGerarFinanceiro;
    }

    public StatusFicha[] getStatuses() {
        return StatusFicha.values();
    }

    public StatusFinanceiro[] getStatusesFinanceiro() {
        return StatusFinanceiro.values();
    }

    public List<Ficha> getFichasFiltrados() {
        return fichasFiltrados;
    }

    public FichaFilter getFiltro() {
        return filtro;
    }

    public Ficha getFichaSelecionada() {
        return fichaSelecionada;
    }

    public void setFichaSelecionada(Ficha fichaSelecionada) {
        this.fichaSelecionada = fichaSelecionada;
    }

    public List<Ficha> getFichasNovo() {
        return fichasNovo;
    }

    public void setFichasNovo(List<Ficha> fichasNovo) {
        this.fichasNovo = fichasNovo;
    }

    public List<Ficha> getFichasFiltradasAdmin() {
        return fichasFiltradasAdmin;
    }

    public void setFichasFiltradasAdmin(List<Ficha> fichasFiltradasAdmin) {
        this.fichasFiltradasAdmin = fichasFiltradasAdmin;
    }

    public List<Usuario> getUsuariosGestor() {
        return usuariosGestor;
    }

    public void setUsuariosGestor(List<Usuario> usuariosGestor) {
        this.usuariosGestor = usuariosGestor;
    }
    
    

    public Ficha getFicha() {
        return ficha;
    }

    public void setFicha(Ficha ficha) {
        this.ficha = ficha;
    }

    public Boolean isStatusPago() {
        return this.ficha.getStatusFichaFinanceiro() == this.ficha.getStatusFichaFinanceiro().PAGO;
    }

}
