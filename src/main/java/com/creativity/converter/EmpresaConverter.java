/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.creativity.converter;

import com.creativity.model.Empresa;
import com.creativity.repository.Empresas;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

/**
 *
 * @author rafael.lima
 */
@FacesConverter(forClass = Empresa.class)
public class EmpresaConverter implements Converter {

    @Inject
    private Empresas empresas;

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        Empresa retorno = null;

        if (value != null && !"".equals(value)) {
            retorno = this.empresas.porId(new Long(value));
        }

        return retorno;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object value) {
        if (value != null) {
            Empresa empresa = ((Empresa) value);
            return empresa.getId() == null ? null : empresa.getId().toString();
        }
        return null;
    }

}
