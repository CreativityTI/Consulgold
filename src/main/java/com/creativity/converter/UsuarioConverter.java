/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.creativity.converter;

import com.creativity.model.Usuario;
import com.creativity.repository.Usuarios;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

/**
 *
 * @author rafael.lima
 */
@FacesConverter(forClass = Usuario.class)
public class UsuarioConverter implements Converter {

    
    @Inject
    private Usuarios usuarios;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        Usuario retorno = null;

        if (value != null && !"".equals(value)) {
            retorno = this.usuarios.porId(new Long(value));
        }

        return retorno;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value != null) {
            Usuario usuario = ((Usuario) value);
            return usuario.getId() == null ? null : usuario.getId().toString();
        }
        return null;
    }

}
