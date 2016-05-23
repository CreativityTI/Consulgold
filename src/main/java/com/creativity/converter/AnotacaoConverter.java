/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.creativity.converter;

import com.creativity.model.Anotacao;
import com.creativity.repository.Anotacoes;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

/**
 *
 * @author rafael.lima
 */
@FacesConverter(forClass = Anotacao.class)
public class AnotacaoConverter implements Converter {

    
    @Inject
    private Anotacoes anotacoes;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        Anotacao retorno = null;

        if (value != null && !"".equals(value)) {
            retorno = this.anotacoes.porId(new Long(value));
        }

        return retorno;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value != null) {
            Anotacao anotacao = ((Anotacao) value);
            return anotacao.getId() == null ? null : anotacao.getId().toString();
        }
        return null;
    }

}
