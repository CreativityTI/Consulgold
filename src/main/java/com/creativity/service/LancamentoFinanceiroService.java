/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.creativity.service;

import com.creativity.model.Financeiro;
import com.creativity.repository.LancamentosFinanceiros;
import com.creativity.util.Transactional;
import java.io.Serializable;
import javax.inject.Inject;

/**
 *
 * @author rafael.lima
 */
public class LancamentoFinanceiroService implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private LancamentosFinanceiros lancamentosFinanceiros;

    @Transactional
    public Financeiro salvar(Financeiro financeiro) throws NegocioException {
        return financeiro = this.lancamentosFinanceiros.guardar(financeiro);

    }

}
