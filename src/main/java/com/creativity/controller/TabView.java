/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.creativity.controller;

import java.io.Serializable;
import javax.faces.component.UIComponent;
import javax.inject.Named;
import org.primefaces.event.TabChangeEvent;

/**
 *
 * @author rafael.lima
 */
@Named
@javax.faces.view.ViewScoped
public class TabView  implements Serializable{

    public void onTabChange(TabChangeEvent event) {

        String activeTab = event.getTab().getId();

        int activeTabIndex = 0;

        //Realiza um loop para identificar qual é a tab que foi selecionada.
        //Obs.: As tabs filhas devem ter um id definido, para que seja facilitada a busca
        //e o entendimento pois o JSF por padrão coloca IDs com nomes que ele mesmo escolhe 
        //para as tabs.
        for (UIComponent comp : event.getTab().getParent().getChildren()) {
            if (comp.getId().equals(activeTab)) {
                break;
            }
            activeTabIndex++;
        }

        System.out.println("ID da Tab Atual: " + event.getTab().getId());
        System.out.println("Index da Tab Atual: " + activeTabIndex);

        setTabAtual(activeTabIndex);
    }

    private void setTabAtual(int activeTabIndex) {
        System.out.println("Tab Atual"+activeTabIndex);
    }

   

}
