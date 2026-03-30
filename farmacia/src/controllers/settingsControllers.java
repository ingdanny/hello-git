/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import views.sistemView;

/**
 *
 * @author PtIngHome
 */
public class settingsControllers implements MouseListener{

    private sistemView views;
    public settingsControllers(sistemView views){
        this.views = views;
        this.views.products.addMouseListener(this);
        this.views.purchases.addMouseListener(this);
        this.views.sales.addMouseListener(this);
        this.views.costumers.addMouseListener(this);
        this.views.employees.addMouseListener(this);
        this.views.suppliers.addMouseListener(this);
        this.views.categories.addMouseListener(this);
        this.views.reports.addMouseListener(this);
        this.views.settings.addMouseListener(this);
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if(e.getSource() == views.products){
            views.products.setBackground(new Color(152, 202, 63));
        }else if (e.getSource()== views.purchases){
            views.purchases.setBackground(new Color(152, 202, 63));
        }else if (e.getSource()== views.sales){
            views.sales.setBackground(new Color(152, 202, 63));
        }else if (e.getSource()== views.costumers){
            views.costumers.setBackground(new Color(152, 202, 63));
        }else if (e.getSource()== views.employees){
            views.employees.setBackground(new Color(152, 202, 63));
        }else if (e.getSource()== views.suppliers){
            views.suppliers.setBackground(new Color(152, 202, 63));
        }else if (e.getSource()== views.categories){
            views.categories.setBackground(new Color(152, 202, 63));
        }else if (e.getSource()== views.reports){
            views.reports.setBackground(new Color(152, 202, 63));
        }else if (e.getSource()== views.settings){
            views.settings.setBackground(new Color(152, 202, 63));
        }     
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if(e.getSource() == views.products){
            views.products.setBackground(new Color(18, 45, 61));
        }else if (e.getSource()== views.purchases){
            views.purchases.setBackground(new Color(18, 45, 61));
        }else if (e.getSource()== views.sales){
            views.sales.setBackground(new Color(18, 45, 61));
        }else if (e.getSource()== views.costumers){
            views.costumers.setBackground(new Color(18, 45, 61));
        }else if (e.getSource()== views.employees){
            views.employees.setBackground(new Color(18, 45, 61));
        }else if (e.getSource()== views.suppliers){
            views.suppliers.setBackground(new Color(18, 45, 61));
        }else if (e.getSource()== views.categories){
            views.categories.setBackground(new Color(18, 45, 61));
        }else if (e.getSource()== views.reports){
            views.reports.setBackground(new Color(18, 45, 61));
        }else if (e.getSource()== views.settings){
            views.settings.setBackground(new Color(18, 45, 61));
        }
    }
    
    
}
