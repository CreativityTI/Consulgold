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
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;

/**
 *
 * @author rafael.lima
 */
@Entity
@Table(name = "FICHA")
public class Ficha implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;

    @NotBlank
    @Length(max = 100)
    @Column(nullable = false, length = 100)
    private String nomeRazao;

    @Length(max = 100)
    @Column(length = 100)
    private String nomeFantasia;

    @CNPJ(message = "CNPJ Inválido verifique!")
    private String cnpj;

    @CPF(message = "CPF Inválido verifique!")
    private String cpf;
    @Length(max = 15)
    @Column(length = 15)
    private String rg;

    @Length(max = 15)
    @Column(length = 15)
    private String ie;

    @NotBlank
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataNascimento;

    private String mcc;

    @Length(max = 100)
    @Column(nullable = false, length = 100)
    private String oferta;

    @NotBlank
    private String cep = null;

    @Length(max = 20)
    @NotBlank
    @Column(nullable = false, length = 20)
    private String tipoLogradouro;

    @Length(max = 50)
    @NotBlank
    @Column(nullable = false, length = 50)
    private String logradouro;

    @Length(max = 2)
    @NotBlank
    @Column(nullable = false, length = 2)
    private String estado;

    @Length(max = 100)
    @NotBlank
    @Column(nullable = false, length = 100)
    private String cidade;

    @Length(max = 40)
    @NotBlank
    @Column(nullable = false, length = 40)
    private String bairro;

    @Length(max = 10)
    @NotBlank
    @Column(nullable = false, length = 10)
    private String numero;

    @Length(max = 40)
    @Column(length = 40)
    private String complemento;

    @NotBlank
    private String telefone;
    @NotBlank
    private String celular;
    @NotBlank
    private String telPreferencial;

    @Email(message = "o e-mail informado não é valido")
    private String email;

    @Length(max = 20)
    @NotBlank
    @Column(nullable = false, length = 20)
    private String agencia;

    @Length(max = 10)
    @NotBlank
    @Column(nullable = false, length = 10)
    private String conta;

    @Length(max = 10)
    @NotBlank
    @Column(nullable = false, length = 10)
    private String digitoConta;

    @NotBlank
    private String formaPagemento;

    private BigDecimal valorUnitario = BigDecimal.valueOf(400);

    @NotNull
    private BigDecimal valorDesconto = BigDecimal.ZERO;

    @NotNull
    private BigDecimal valorTotal = BigDecimal.ZERO;

    @NotNull
    private BigDecimal quantidade = BigDecimal.ZERO;

    @Length(max = 100)
    @NotBlank
    @Column(nullable = false, length = 100)
    private String solucaoCaptura;

    @Length(max = 100)
    @NotBlank
    @Column(nullable = false, length = 100)
    private String operadora;

    @Length(max = 100)
    @NotBlank
    @Column(nullable = false, length = 100)
    private String diaInstalacao;

    @Column(columnDefinition = "DATE")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date dataCriacao;

    @Column(columnDefinition = "DATE")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date dataAprovacao;

    @Column(columnDefinition = "DATE")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date dataEntregaMaquina;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private StatusResposta statusResposta;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private StatusFicha statusFicha = StatusFicha.NOVOCADASTRO;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private TipoPessoa tipoPessoa;

    @OneToMany(mappedBy = "ficha", cascade = CascadeType.ALL)
    private List<Anotacao> atonacoes = new ArrayList<>();

    @OneToMany(mappedBy = "ficha", cascade = CascadeType.ALL)
    private List<Financeiro> lancamentoFinanceiros = new ArrayList<>();

    @NotNull
    @ManyToOne
    @JoinColumn(name = "gestor_id", nullable = false)
    private Usuario gestor;

    @ManyToOne
    @JoinColumn(name = "consultor_id")
    private Usuario consultor;

    @ManyToOne
    @JoinColumn(name = "banco_id")
    private Banco banco;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "categoria_id", nullable = false)
    private Categoria categoria;

    private String numeroCreadenciamento;

    private String observacaoCredenciamento;

    private String nomeTitular;

    private String cpfTtitular;
    private String rgTtiluar;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataNascTitular;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private StatusFinanceiro statusFichaFinanceiro = StatusFinanceiro.SEMLANCAMENTO;

    @Transient
    public boolean isNovo() {
        return getId() == null;
    }

    @Transient
    public boolean isExistente() {
        return !isNovo();
    }

    public Long getId() {
        return id;
    }

    public String getNomeRazao() {
        return nomeRazao;
    }

    public void setNomeRazao(String nomeRazao) {
        this.nomeRazao = nomeRazao;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getIe() {
        return ie;
    }

    public void setIe(String ie) {
        this.ie = ie;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getMcc() {
        return mcc;
    }

    public void setMcc(String mcc) {
        this.mcc = mcc;
    }

    public String getOferta() {
        return oferta;
    }

    public void setOferta(String oferta) {
        this.oferta = oferta;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getTipoLogradouro() {
        return tipoLogradouro;
    }

    public void setTipoLogradouro(String tipoLogradouro) {
        this.tipoLogradouro = tipoLogradouro;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getTelPreferencial() {
        return telPreferencial;
    }

    public void setTelPreferencial(String telPreferencial) {
        this.telPreferencial = telPreferencial;
    }

    public Banco getBanco() {
        return banco;
    }

    public void setBanco(Banco banco) {
        this.banco = banco;
    }

    public String getAgencia() {
        return agencia;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    public String getConta() {
        return conta;
    }

    public void setConta(String conta) {
        this.conta = conta;
    }

    public String getFormaPagemento() {
        return formaPagemento;
    }

    public void setFormaPagemento(String formaPagemento) {
        this.formaPagemento = formaPagemento;
    }

    public BigDecimal getValorDesconto() {
        return valorDesconto;
    }

    public void setValorDesconto(BigDecimal valorDesconto) {
        this.valorDesconto = valorDesconto;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public String getSolucaoCaptura() {
        return solucaoCaptura;
    }

    public void setSolucaoCaptura(String solucaoCaptura) {
        this.solucaoCaptura = solucaoCaptura;
    }

    public BigDecimal getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(BigDecimal quantidade) {
        this.quantidade = quantidade;
    }

    public String getOperadora() {
        return operadora;
    }

    public void setOperadora(String operadora) {
        this.operadora = operadora;
    }

    public String getDiaInstalacao() {
        return diaInstalacao;
    }

    public void setDiaInstalacao(String diaInstalacao) {
        this.diaInstalacao = diaInstalacao;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public StatusFicha getStatusFicha() {
        return statusFicha;
    }

    public void setStatusFicha(StatusFicha statusFicha) {
        this.statusFicha = statusFicha;
    }

    public List<Anotacao> getAtonacoes() {
        return atonacoes;
    }

    public void setAtonacoes(List<Anotacao> atonacoes) {
        this.atonacoes = atonacoes;
    }

    public Usuario getGestor() {
        return gestor;
    }

    public void setGestor(Usuario gestor) {
        this.gestor = gestor;
    }

    public Usuario getConsultor() {
        return consultor;
    }

    public void setConsultor(Usuario consultor) {
        this.consultor = consultor;
    }

    public BigDecimal getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(BigDecimal valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public TipoPessoa getTipoPessoa() {
        return tipoPessoa;
    }

    public void setTipoPessoa(TipoPessoa tipoPessoa) {
        this.tipoPessoa = tipoPessoa;
    }

    public String getDigitoConta() {
        return digitoConta;
    }

    public void setDigitoConta(String digitoConta) {
        this.digitoConta = digitoConta;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumeroCreadenciamento() {
        return numeroCreadenciamento;
    }

    public void setNumeroCreadenciamento(String numeroCreadenciamento) {
        this.numeroCreadenciamento = numeroCreadenciamento;
    }

    public String getObservacaoCredenciamento() {
        return observacaoCredenciamento;
    }

    public void setObservacaoCredenciamento(String observacaoCredenciamento) {
        this.observacaoCredenciamento = observacaoCredenciamento;
    }

    public List<Financeiro> getLancamentoFinanceiros() {
        return lancamentoFinanceiros;
    }

    public void setLancamentoFinanceiros(List<Financeiro> lancamentoFinanceiros) {
        this.lancamentoFinanceiros = lancamentoFinanceiros;
    }

    public String getNomeTitular() {
        return nomeTitular;
    }

    public void setNomeTitular(String nomeTitular) {
        this.nomeTitular = nomeTitular;
    }

    public String getCpfTtitular() {
        return cpfTtitular;
    }

    public void setCpfTtitular(String cpfTtitular) {
        this.cpfTtitular = cpfTtitular;
    }

    public String getRgTtiluar() {
        return rgTtiluar;
    }

    public void setRgTtiluar(String rgTtiluar) {
        this.rgTtiluar = rgTtiluar;
    }

    public Date getDataNascTitular() {
        return dataNascTitular;
    }

    public void setDataNascTitular(Date dataNascTitular) {
        this.dataNascTitular = dataNascTitular;
    }

    public StatusFinanceiro getStatusFichaFinanceiro() {
        return statusFichaFinanceiro;
    }

    public void setStatusFichaFinanceiro(StatusFinanceiro statusFichaFinanceiro) {
        this.statusFichaFinanceiro = statusFichaFinanceiro;
    }

    public Date getDataAprovacao() {
        return dataAprovacao;
    }

    public void setDataAprovacao(Date dataAprovacao) {
        this.dataAprovacao = dataAprovacao;
    }

    public Date getDataEntregaMaquina() {
        return dataEntregaMaquina;
    }

    public void setDataEntregaMaquina(Date dataEntregaMaquina) {
        this.dataEntregaMaquina = dataEntregaMaquina;
    }

    @Override
    public int hashCode() {
        int hash = 7;
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
        final Ficha other = (Ficha) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Transient
    public BigDecimal getValorTotalFicha() {
        return this.getValorUnitario().multiply(this.getQuantidade());
    }

    @Transient
    public BigDecimal getValorSubtotal() {
        return this.getValorTotal().add(this.getValorDesconto());

    }

    public void recalcularValorTotal() {
        BigDecimal total = BigDecimal.ZERO;

        total = total.add(getValorTotalFicha().subtract(this.getValorDesconto()));

        this.setValorTotal(total);

    }

    public StatusResposta getStatusResposta() {
        return statusResposta;
    }

    public void setStatusResposta(StatusResposta statusResposta) {
        this.statusResposta = statusResposta;
    }

    public BigDecimal getValorTotalFinanceiro() {
        BigDecimal vlTotal = new BigDecimal(BigInteger.ZERO);
        for (Financeiro item : getLancamentoFinanceiros()) {
            vlTotal = vlTotal.add(item.getValor());
        }
        return vlTotal;
    }

}
