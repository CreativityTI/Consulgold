/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.creativity.service;

import com.creativity.model.Ficha;
import com.creativity.model.StatusFicha;
import com.creativity.repository.Fichas;
import com.creativity.repository.LancamentosFinanceiros;
import com.creativity.util.Transactional;
import java.io.Serializable;
import java.util.Date;
import javax.inject.Inject;

/**
 *
 * @author rafael.lima
 */
public class CadastroFichaService implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private Fichas fichas;

  
    @Transactional
    public Ficha salvar(Ficha ficha) throws NegocioException {

        if (ficha.isNovo()) {
            ficha.setDataCriacao(new Date());
            ficha.setStatusFicha(StatusFicha.NOVOCADASTRO);
        }

        ficha = this.fichas.guardar(ficha);
        return ficha;

    }

}
