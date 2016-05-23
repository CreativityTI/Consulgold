/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.creativity.service;

import com.creativity.model.Mensagem;
import com.creativity.model.Status;
import com.creativity.repository.Mensagens;
import com.creativity.util.Transactional;
import java.io.Serializable;
import java.util.Date;
import javax.inject.Inject;

/**
 *
 * @author rafael.lima
 */
public class CadastroMensagemService implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private Mensagens mensagens;

    @Transactional
    public Mensagem salvar(Mensagem mensagem) throws NegocioException {

        if (mensagem.isNovo()) {
            mensagem.setDataCriacao(new Date());
            mensagem.setStatus(false);
        }
        mensagem = this.mensagens.guardar(mensagem);
        return mensagem;

    }

    @Transactional
    public void excluir(Mensagem mensagem) throws NegocioException {
        mensagem = this.mensagens.porId(mensagem.getId());
        this.mensagens.remover(mensagem);
    }

}
