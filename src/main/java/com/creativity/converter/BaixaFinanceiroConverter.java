/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.creativity.converter;

import com.creativity.model.BaixaFinanceiro;
import com.creativity.repository.BaixaFinanceiros;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

/**
 *
 * @author rafael.lima
 */
@FacesConverter(forClass = BaixaFinanceiro.class)
public class BaixaFinanceiroConverter implements Converter {

    
    @Inject
    private BaixaFinanceiros baixaFinanceiros;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        BaixaFinanceiro retorno = null;

        if (value != null && !"".equals(value)) {
            retorno = this.baixaFinanceiros.porId(new Long(value));
        }

        return retorno;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value != null) {
            BaixaFinanceiro baixaFinanceiro = ((BaixaFinanceiro) value);
            return baixaFinanceiro.getId() == null ? null : baixaFinanceiro.getId().toString();
        }
        return null;
    }

}
