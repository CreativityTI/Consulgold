/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.creativity.converter;

import com.creativity.model.Financeiro;
import com.creativity.repository.LancamentosFinanceiros;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

/**
 *
 * @author rafael.lima
 */
@FacesConverter(forClass = Financeiro.class)
public class FinanceiroConverter implements Converter {

    
    @Inject
    private LancamentosFinanceiros financeiros;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        Financeiro retorno = null;

        if (value != null && !"".equals(value)) {
            retorno = this.financeiros.porId(new Long(value));
        }

        return retorno;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value != null) {
            Financeiro financeiro = ((Financeiro) value);
            return financeiro.getId() == null ? null : financeiro.getId().toString();
        }
        return null;
    }

}
