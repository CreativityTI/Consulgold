/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.creativity.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Transient;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author rafael.lima
 */
@Entity
@Table(name = "MENSAGEM")
public class Mensagem implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;

    @Column(columnDefinition = "DATE")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date dataCriacao;

    @NotBlank
    @Column(length = 20)
    private String tituloMensagem;

    @NotBlank
    @Column(length = 3000)
    private String textoMensagem;

    private boolean status;

    @ManyToOne
    @JoinColumn(name = "consultor_id")
    private Usuario consultor;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public String getTituloMensagem() {
        return tituloMensagem;
    }

    public void setTituloMensagem(String tituloMensagem) {
        this.tituloMensagem = tituloMensagem;
    }

    public String getTextoMensagem() {
        return textoMensagem;
    }

    public void setTextoMensagem(String textoMensagem) {
        this.textoMensagem = textoMensagem;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Usuario getConsultor() {
        return consultor;
    }

    public void setConsultor(Usuario consultor) {
        this.consultor = consultor;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 11 * hash + Objects.hashCode(this.id);
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
        final Mensagem other = (Mensagem) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Transient
    public boolean isNovo() {
        return getId() == null;
    }

}
