/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.creativity.controller;

import com.creativity.util.FacesUtil;
import java.io.IOException;
import java.io.Serializable;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

/**
 *
 * @author rafael.lima
 */
@Named
@SessionScoped
public class LoginBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private String email;

    public void preRender() {
        if ("true".equals(FacesUtil.getRequestParameter("invalid"))) {
            FacesUtil.addErrorMessage("Usuário ou senha inválido!");
        }
    }

    public void login() throws ServletException, IOException {
        RequestDispatcher dispatcher = FacesUtil.getServletRequest().getRequestDispatcher("/j_spring_security_check");
        dispatcher.forward(FacesUtil.getServletRequest(), FacesUtil.getServletResponse());

        FacesContext.getCurrentInstance().responseComplete();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
