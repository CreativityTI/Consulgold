/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.creativity.converter;

import com.creativity.model.Banco;
import com.creativity.model.Empresa;
import com.creativity.repository.Bancos;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

/**
 *
 * @author rafael.lima
 */
@FacesConverter(forClass = Banco.class)
public class BancoConverter implements Converter {

    @Inject
    private Bancos bancos;

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        Banco retorno = null;

        if (value != null && !"".equals(value)) {
            retorno = this.bancos.porId(new Long(value));
        }

        return retorno;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object value) {
        if (value != null) {
            Banco banco = ((Banco) value);
            return banco.getId() == null ? null : banco.getId().toString();
        }
        return null;
    }

}
