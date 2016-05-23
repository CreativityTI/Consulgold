/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.creativity.converter;

import com.creativity.model.Ficha;
import com.creativity.repository.Fichas;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

/**
 *
 * @author rafael.lima
 */
@FacesConverter(forClass = Ficha.class)
public class FichaConverter implements Converter {

    
    @Inject
    private Fichas fichas;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        Ficha retorno = null;

        if (value != null && !"".equals(value)) {
            retorno = this.fichas.porId(new Long(value));
        }

        return retorno;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value != null) {
            Ficha ficha = ((Ficha) value);
            return ficha.getId() == null ? null : ficha.getId().toString();
        }
        return null;
    }

}
