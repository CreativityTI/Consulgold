/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.creativity.controller;

import com.creativity.model.Anotacao;
import com.creativity.model.Banco;
import com.creativity.model.Categoria;
import com.creativity.model.Ficha;
import com.creativity.model.Financeiro;
import com.creativity.model.StatusFicha;
import com.creativity.model.StatusFinanceiro;
import com.creativity.model.StatusResposta;
import com.creativity.model.TipoPessoa;
import com.creativity.model.Usuario;
import com.creativity.repository.Bancos;
import com.creativity.repository.Categorias;
import com.creativity.repository.Usuarios;
import com.creativity.service.CadastroFichaService;
import com.creativity.service.NegocioException;
import com.creativity.util.FacesUtil;
import java.io.Serializable;
import java.util.ArrayList;
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
public class CadastroFichaBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private Bancos bancosRepository;

    private List<Banco> todosBancos;

    @Inject
    private Categorias categorias;

    private Categoria categoriaPai;

    private List<Categoria> categoriasRaizes;
    private List<Categoria> subcategorias;

    private TipoPessoa tipoPessoa;

    @Inject
    private Usuarios usuarios;

    private List<Usuario> consultores;
    private List<Usuario> gestores;
    private List<Usuario> usuariosGestor;
    private List<Usuario> usuariosConsultor;
    private List<Usuario> usuarioLogado;

    private Ficha ficha;

    @Inject
    private CadastroFichaService cadastroFichaService;

    private Anotacao anotacao;

    private Financeiro financeiro;

    private String cep = null;

    public void prepararCadastro() {

        if (FacesUtil.isNotPostback()) {
            categoriasRaizes = categorias.raizes();

            if (this.categoriaPai != null) {
                carregarSubcategorias();
            }
        }
        this.todosBancos = this.bancosRepository.todasBancos();
        this.usuarioLogado = this.usuarios.usuariosLogado();
        this.gestores = this.usuarios.gestorLogado();/* TRAZ OS GESTOR LOGADO*/
        this.consultores = this.usuarios.consultorGestorLogado();/*TRAZ OS CONSULTORES DO GESTOR LOGADO*/
        this.usuariosGestor = this.usuarios.todosGestores();
        this.usuariosConsultor = this.usuarios.todosConsultores();
        anotacao = new Anotacao();
        financeiro = new Financeiro();
        /*TRAZ O USUARIO LOGADO*/
        if (this.ficha == null) {
            this.ficha = new Ficha();

        }
    }

    /*LANÇAMENTOS DE ANOTAÇÃO*/
    public void novaAnotacao() {
        anotacao = new Anotacao();
    }

    public void novoLancamentoFinanceiro() {
        financeiro = new Financeiro();

    }

    public void adicionarAnotacaoPendente() {

        FacesContext context = FacesContext.getCurrentInstance();

        try {
            anotacao.setFicha(this.ficha);
            anotacao.setDataAnotacao(new Date());
            anotacao.setUsuario(this.usuarioLogado.get(0));
            this.ficha.getAtonacoes().add(anotacao);
            FacesUtil.addInfoMessage("Anotação adicionanda com sucesso!");
            this.ficha.setStatusFicha(StatusFicha.PENDENTE);
            this.ficha.setStatusResposta(StatusResposta.RESATENDENTE);

            this.cadastroFichaService.salvar(this.ficha);
            FacesUtil.addInfoMessage(" Ficha está com estatus PENDENTE!");
            this.ficha = new Ficha();

        } catch (Exception e) {
            FacesMessage mensagem = new FacesMessage(e.getMessage());
            mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
            context.addMessage(null, mensagem);
        }

    }

    public void adicionarFinanceiro() {
        FacesContext context = FacesContext.getCurrentInstance();
        /*if (!ficha.getAtonacoes().contains(this.anotacao)) {*/

        try {
            financeiro.setFicha(this.ficha);
            financeiro.setDataLancamento(new Date());
            this.ficha.getLancamentoFinanceiros().add(financeiro);
            FacesUtil.addInfoMessage("Financeiro adicionando com sucesso!");
            this.ficha.setStatusFichaFinanceiro(StatusFinanceiro.ABERTO);

            this.cadastroFichaService.salvar(this.ficha);
            FacesUtil.addInfoMessage(" Ficha está com estatus FINANCEIRO EM ABERTO!");
            /* this.ficha = new Ficha();*/

        } catch (Exception e) {
            FacesMessage mensagem = new FacesMessage(e.getMessage());
            mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
            context.addMessage(null, mensagem);
        }

    }

    public void adicionarAnotacaoResAtendente() {
        FacesContext context = FacesContext.getCurrentInstance();
        /*if (!ficha.getAtonacoes().contains(this.anotacao)) {*/

        try {
            anotacao.setFicha(ficha);
            anotacao.setDataAnotacao(new Date());
            anotacao.setUsuario(this.usuarioLogado.get(0));
            ficha.getAtonacoes().add(anotacao);
            FacesUtil.addInfoMessage("Anotação adicionanda com sucesso!");
            this.ficha.setStatusResposta(StatusResposta.RESATENDENTE);
            this.cadastroFichaService.salvar(this.ficha);
            this.anotacao = new Anotacao();

        } catch (Exception e) {
            FacesMessage mensagem = new FacesMessage(e.getMessage());
            mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
            context.addMessage(null, mensagem);
        }

    }

    public void adicionarAnotacaoResConsultor() {
        FacesContext context = FacesContext.getCurrentInstance();
        /*if (!ficha.getAtonacoes().contains(this.anotacao)) {*/

        try {
            anotacao.setFicha(ficha);
            anotacao.setDataAnotacao(new Date());
            anotacao.setUsuario(this.usuarioLogado.get(0));
            ficha.getAtonacoes().add(anotacao);
            FacesUtil.addInfoMessage("Anotação adicionanda com sucesso!");
            this.ficha.setStatusResposta(StatusResposta.RESCONSULTOR);
            this.cadastroFichaService.salvar(this.ficha);
            this.anotacao = new Anotacao();

        } catch (Exception e) {
            FacesMessage mensagem = new FacesMessage(e.getMessage());
            mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
            context.addMessage(null, mensagem);
        }

    }

    public void adicionarAnotacao() {
        FacesContext context = FacesContext.getCurrentInstance();
        /*if (!ficha.getAtonacoes().contains(this.anotacao)) {*/

        try {
            anotacao.setFicha(ficha);
            anotacao.setDataAnotacao(new Date());
            ficha.getAtonacoes().add(anotacao);
            FacesUtil.addInfoMessage("Anotação adicionanda com sucesso!");
            this.cadastroFichaService.salvar(this.ficha);
            this.anotacao = new Anotacao();

        } catch (Exception e) {
            FacesMessage mensagem = new FacesMessage(e.getMessage());
            mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
            context.addMessage(null, mensagem);
        }

    }

    public void excluiAnotacao() {
        ficha.getAtonacoes().remove(anotacao);
    }

    public void carregarConsultores() {
        this.consultores = this.usuarios.consultorGestorLogado();
        /* TRAZ OS CONSULTORES CORRESPONDENTES AO GESTOR*/

    }

    public void salvar() {

        FacesContext context = FacesContext.getCurrentInstance();

        try {
            this.cadastroFichaService.salvar(this.ficha);
            context.addMessage(null, new FacesMessage(" Ficha salva com sucesso!"));
            ficha = new Ficha();
            categoriaPai = null;
            subcategorias = new ArrayList<>();

        } catch (NegocioException e) {

            FacesMessage mensagem = new FacesMessage(e.getMessage());
            mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
            context.addMessage(null, mensagem);
        }
    }

    public void recalcularFicha() {
        if (this.ficha != null) {
            this.ficha.recalcularValorTotal();
        }
    }

    public void fichaAprovar() {

        FacesContext context = FacesContext.getCurrentInstance();

        try {
            this.ficha.setStatusFicha(StatusFicha.APROVADO);
            this.cadastroFichaService.salvar(this.ficha);
            context.addMessage(null, new FacesMessage(" Ficha APROVADA com sucesso!"));
            this.ficha = new Ficha();

        } catch (NegocioException e) {

            FacesMessage mensagem = new FacesMessage(e.getMessage());
            mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
            context.addMessage(null, mensagem);
        }
    }

    public void fichaPendente() {

        FacesContext context = FacesContext.getCurrentInstance();

        try {
            this.ficha.setStatusFicha(StatusFicha.PENDENTE);
            this.cadastroFichaService.salvar(this.ficha);
            context.addMessage(null, new FacesMessage(" Ficha está com estatus PENDENTE!"));

        } catch (NegocioException e) {

            FacesMessage mensagem = new FacesMessage(e.getMessage());
            mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
            context.addMessage(null, mensagem);
        }
    }

    public void encontraCEPS() {
        CepWebService cepWebService = new CepWebService(getCep());

        if (cepWebService.getResultado() == 1) {

            ficha.setCep(cep);
            ficha.setTipoLogradouro(cepWebService.getTipoLogradouro());
            ficha.setLogradouro(cepWebService.getLogradouro());
            ficha.setEstado(cepWebService.getEstado());
            ficha.setCidade(cepWebService.getCidade());
            ficha.setBairro(cepWebService.getBairro());

        } else {

            ficha.setCep(cep);
            ficha.setTipoLogradouro(cepWebService.getTipoLogradouro());
            ficha.setLogradouro(cepWebService.getLogradouro());
            ficha.setEstado(cepWebService.getEstado());
            ficha.setCidade(cepWebService.getCidade());
            ficha.setBairro(cepWebService.getBairro());

        }

    }

    public TipoPessoa[] getTiposPessoas() {
        return TipoPessoa.values();
    }
    
    

    public void carregarSubcategorias() {
        subcategorias = categorias.subcategoriasDe(categoriaPai);
    }

    public Usuarios getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(Usuarios usuarios) {
        this.usuarios = usuarios;
    }

    public List<Usuario> getConsultores() {
        return consultores;
    }

    public void setConsultores(List<Usuario> consultores) {
        this.consultores = consultores;
    }

    public Ficha getFicha() {
        return ficha;
    }

    public void setFicha(Ficha ficha) {
        this.ficha = ficha;

        if (this.ficha != null) {
            this.categoriaPai = this.ficha.getCategoria().getCategoriaPai();
        }
    }

    public List<Usuario> getGestores() {
        return gestores;
    }

    public void setGestores(List<Usuario> gestores) {
        this.gestores = gestores;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public Anotacao getAnotacao() {
        return anotacao;
    }

    public void setAnotacao(Anotacao anotacao) {
        this.anotacao = anotacao;
    }

    public List<Usuario> getUsuariosGestor() {
        return usuariosGestor;
    }

    public void setUsuariosGestor(List<Usuario> usuariosGestor) {
        this.usuariosGestor = usuariosGestor;
    }

    public List<Usuario> getUsuariosConsultor() {
        return usuariosConsultor;
    }

    public void setUsuariosConsultor(List<Usuario> usuariosConsultor) {
        this.usuariosConsultor = usuariosConsultor;
    }

    public List<Usuario> getUsuarioLogado() {
        return usuarioLogado;
    }

    public void setUsuarioLogado(List<Usuario> usuarioLogado) {
        this.usuarioLogado = usuarioLogado;
    }

    public List<Categoria> getCategoriasRaizes() {
        return categoriasRaizes;
    }

    public void setCategoriasRaizes(List<Categoria> categoriasRaizes) {
        this.categoriasRaizes = categoriasRaizes;
    }

    public List<Categoria> getSubcategorias() {
        return subcategorias;
    }

    public void setSubcategorias(List<Categoria> subcategorias) {
        this.subcategorias = subcategorias;
    }

    public Categorias getCategorias() {
        return categorias;
    }

    public void setCategorias(Categorias categorias) {
        this.categorias = categorias;
    }

    public Categoria getCategoriaPai() {
        return categoriaPai;
    }

    public void setCategoriaPai(Categoria categoriaPai) {
        this.categoriaPai = categoriaPai;
    }

    public List<Banco> getTodosBancos() {
        return todosBancos;
    }

    public void setTodosBancos(List<Banco> todosBancos) {
        this.todosBancos = todosBancos;
    }

    public Financeiro getFinanceiro() {
        return financeiro;
    }

    public void setFinanceiro(Financeiro financeiro) {
        this.financeiro = financeiro;
    }

    public TipoPessoa[] getTiposPessoa() {
        return TipoPessoa.values();
    }

    public boolean isEditando() {
        return this.ficha.getId() != null;

    }

    public boolean isEditandoCep() {
        return this.ficha.getId() == null;
    }

    public boolean isRespostaAtendente() {
        return this.ficha.getStatusResposta() == ficha.getStatusResposta().RESATENDENTE;
    }

    public boolean isRespostaConsultor() {
        return this.ficha.getStatusResposta() == ficha.getStatusResposta().RESCONSULTOR;
    }

}
