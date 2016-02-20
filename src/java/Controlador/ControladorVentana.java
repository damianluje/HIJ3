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
import Modelo.Ventana;
import Servicios.OpcionesFacade;
import Servicios.SistemaFacade;
import Servicios.VentanaFacade;
import javax.el.ELContext;
import javax.faces.bean.ManagedProperty;
import javax.faces.event.ValueChangeEvent;

/**
 *
 * @author homer
 */
@ManagedBean
@ViewScoped
public class ControladorVentana implements Serializable {

    @EJB
    private OpcionesFacade serOpc;
    @EJB
    private VentanaFacade serVen;

    private Opciones opcion;
    private List<Opciones> opciones;
    private List<Ventana> ventanas;
    private Ventana ventana;
    private Integer codOpc;

    public ControladorVentana() {
        ventana = new Ventana();
        
        opciones = new ArrayList<>();
        ventanas=new ArrayList<>();

    }

    @PostConstruct
    public void cargarDatos() {
        opciones = serOpc.findAll();
        ventanas= serVen.findAll();
//        System.out.println(opciones);
    }

    public void limpiar() {
        ventana=new Ventana();
    }

    public void actualizarVentana() {
        //System.out.println("*************" + opcion.getCodigoSistema());
        serVen.edit(ventana);
        //System.out.println("Actualizando Opcion: "+opcion+opcion.getSisCodigo());
        limpiar();
    }

    public void eliminarVentana() {
        try {
            serVen.remove(ventana);
            this.ventanas = serVen.findAll();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Ventana Eliminada", ""));
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), ""));
        }
        limpiar();

    }

    public void valueChangeMethod(ValueChangeEvent e) {

        for (int i = 0; i < ventanas.size(); i++) {
            Ventana ven = ventanas.get(i);
            if (ven.getVenCodigo()== Integer.parseInt(e.getNewValue().toString())) {
                ventana = ven;
                setCodOpc(ven.getOpcCodigo().getOpcCodigo());
            }
        }

    }

    public void valueChangeMethod1(ValueChangeEvent e) {
        //System.out.println(Integer.parseInt(e.getNewValue().toString()));

        Opciones opc = serOpc.find(Integer.parseInt(e.getNewValue().toString()));

        ventana.setOpcCodigo(opc);
        //System.out.println("Sistema: " + sis.getSisCodigo());

    }

    public void ingresarVentana() {
        try {
            //System.out.println("Opcion:" + opcion + opcion.getSisCodigo());
            ventana.setVenCodigo(null);
            serVen.create(ventana);

            this.ventanas = serVen.findAll();
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

    public VentanaFacade getSerVen() {
        return serVen;
    }

    public void setSerVen(VentanaFacade serVen) {
        this.serVen = serVen;
    }

    public List<Ventana> getVentanas() {
        return ventanas;
    }

    public void setVentanas(List<Ventana> ventanas) {
        this.ventanas = ventanas;
    }

    public Ventana getVentana() {
        return ventana;
    }

    public void setVentana(Ventana ventana) {
        this.ventana = ventana;
    }

    public Integer getCodOpc() {
        return codOpc;
    }

    public void setCodOpc(Integer codOpc) {
        this.codOpc = codOpc;
        Opciones opc=serOpc.find(codOpc);
        
        ventana.setOpcCodigo(opc);
    }

    

}
