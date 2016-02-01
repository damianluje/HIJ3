/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import Modelo.Sistema;
import Servicios.SistemaFacade;
import javax.faces.event.ValueChangeEvent;

/**
 *
 * @author homer
 */
@ManagedBean
@ViewScoped
public class ControladorSistema implements Serializable {

    @EJB
    private SistemaFacade serPer;

    private Sistema sistema;
    private List<Sistema> sistemas;

    public ControladorSistema() {
        sistema = new Sistema();
        sistemas = new ArrayList<>();
        
    }

    @PostConstruct
    public void cargarDatos() {
        sistemas = serPer.findAll();
//        System.out.println(sistemas);
    }

    public void limpiar() {
        sistema = new Sistema();
    }

    public void actualizarSistema() {
        //System.out.println("*************" + sistema.getCodigoSistema());
        serPer.edit(sistema);
        limpiar();
    }

    public void eliminarSistema() {
        try {
            serPer.remove(sistema);
            this.sistemas = serPer.findAll();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sistema Eliminado", ""));
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), ""));
        }
        limpiar();

    }
    
    public void valueChangeMethod(ValueChangeEvent e) {

        
        for (int i = 0; i < sistemas.size(); i++) {
            Sistema sis = sistemas.get(i);
            if (sis.getSisCodigo()==Integer.parseInt(e.getNewValue().toString())) {
                sistema=sis;
            }
        }
        
    }
    

    public void ingresarSistema() {
        try {
            serPer.create(sistema);
            this.sistemas = serPer.findAll();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sistema Ingresada", ""));
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), ""));
        }
        limpiar();
    }

    public Sistema getSistema(int cod) {
        return serPer.find(cod);
    }

    public SistemaFacade getSerPer() {
        return serPer;
    }

    public void setSerPer(SistemaFacade serPer) {
        this.serPer = serPer;
    }

    

    public Sistema getSistema() {
        return sistema;
    }

    public void setSistema(Sistema sistema) {
        this.sistema = sistema;
    }

    public List<Sistema> getSistemas() {
        return sistemas;
    }

    public void setSistemas(List<Sistema> sistemas) {
        this.sistemas = sistemas;
    }

}
