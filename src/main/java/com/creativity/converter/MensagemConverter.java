/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.creativity.converter;

import com.creativity.model.Mensagem;
import com.creativity.repository.Mensagens;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

/**
 *
 * @author rafael.lima
 */
@FacesConverter(forClass = Mensagem.class)
public class MensagemConverter implements Converter {

    
    @Inject
    private Mensagens mensagens;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        Mensagem retorno = null;

        if (value != null && !"".equals(value)) {
            retorno = this.mensagens.porId(new Long(value));
        }

        return retorno;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value != null) {
            Mensagem mensagem = ((Mensagem) value);
            return mensagem.getId() == null ? null : mensagem.getId().toString();
        }
        return null;
    }

}
