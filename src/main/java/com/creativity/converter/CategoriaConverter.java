/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.creativity.converter;

import com.creativity.model.Categoria;
import com.creativity.repository.Categorias;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

/**
 *
 * @author rafael.lima
 */
@FacesConverter(forClass = Categoria.class)
public class CategoriaConverter implements Converter {

    @Inject
    private Categorias categorias;

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        Categoria retorno = null;

        if (value != null && !"".equals(value)) {
            retorno = this.categorias.porId(new Long(value));
        }

        return retorno;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object value) {
         if (value != null) {
            Categoria categoria = ((Categoria) value);
            return categoria.getId() == null ? null :categoria.getId().toString();
        }
        return null;
    }

}
