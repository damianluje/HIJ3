/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Opciones;
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
import Servicios.OpcionesFacade;
import Servicios.SistemaFacade;
import javax.el.ELContext;
import javax.faces.bean.ManagedProperty;
import javax.faces.event.ValueChangeEvent;

/**
 *
 * @author homer
 */
@ManagedBean
@ViewScoped
public class ControladorOpciones implements Serializable {

    @EJB
    private OpcionesFacade serOpc;

    @EJB
    private SistemaFacade serSis;
    private Opciones opcion;
    private List<Opciones> opciones;

    public ControladorOpciones() {
        opcion = new Opciones();
        opcion.setSisCodigo(new Sistema());
        opciones = new ArrayList<>();
        
    }

    @PostConstruct
    public void cargarDatos() {
        opciones = serOpc.findAll();
//        System.out.println(opciones);
    }

    

    public void limpiar() {
        opcion = new Opciones();
        opcion.setSisCodigo(new Sistema());
    }

    public void actualizarOpcion() {
        //System.out.println("*************" + opcion.getCodigoSistema());
        serOpc.edit(opcion);
        limpiar();
    }

    public void eliminarOpcion() {
        try {
            serOpc.remove(opcion);
            this.opciones = serOpc.findAll();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sistema Eliminado", ""));
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), ""));
        }
        limpiar();

    }

    public void valueChangeMethod(ValueChangeEvent e) {

        for (int i = 0; i < opciones.size(); i++) {
            Opciones opc = opciones.get(i);
            if (opc.getOpcCodigo() == Integer.parseInt(e.getNewValue().toString())) {
                opcion = opc;
            }
        }

    }

    public void valueChangeMethod1(ValueChangeEvent e) {
        System.out.println(Integer.parseInt(e.getNewValue().toString()));
        
        Sistema sis=serSis.find(Integer.parseInt(e.getNewValue().toString()));
        
        opcion.setSisCodigo(sis);

    }

    public void ingresarOpcion() {
        try {
            System.out.println("Opcion:" + opcion);
            serOpc.create(opcion);
            this.opciones = serOpc.findAll();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sistema Ingresada", ""));
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), ""));
        }
        limpiar();
    }

    public OpcionesFacade getSerOpc() {
        return serOpc;
    }

    public void setSerOpc(OpcionesFacade serOpc) {
        this.serOpc = serOpc;
    }

    public Opciones getOpcion() {
        return opcion;
    }

    public void setOpcion(Opciones opcion) {
        this.opcion = opcion;
    }

    public List<Opciones> getOpciones() {
        return opciones;
    }

    public void setOpciones(List<Opciones> opciones) {
        this.opciones = opciones;
    }

    public void setSistema(int cod) {
//        ControladorSistema asd=new ControladorSistema();
//        
//        this.opcion.setSisCodigo(asd.getSistema(cod));
    }

}
