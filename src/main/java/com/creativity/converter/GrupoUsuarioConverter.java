/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.creativity.converter;

import com.creativity.model.GrupoUsuario;
import com.creativity.repository.GruposUsuarios;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

/**
 *
 * @author rafael.lima
 */
@FacesConverter(forClass = GrupoUsuario.class)
public class GrupoUsuarioConverter implements Converter {

    @Inject
    private GruposUsuarios gruposUsuarios;

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        GrupoUsuario retorno = null;

        if (value != null && !"".equals(value)) {
            retorno = this.gruposUsuarios.porId(new Long(value));
        }

        return retorno;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object value) {
         if (value != null) {
            GrupoUsuario grupoUsuario = ((GrupoUsuario) value);
            return grupoUsuario.getId() == null ? null : grupoUsuario.getId().toString();
        }
        return null;
    }

}
