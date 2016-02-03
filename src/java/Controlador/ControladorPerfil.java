/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.OpcionesPerfil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import Modelo.Perfil;
import Servicios.PerfilFacade;
import javax.faces.event.ValueChangeEvent;

/**
 *
 * @author Damian
 */
@ManagedBean
@ViewScoped
public class ControladorPerfil implements Serializable {

    @EJB
    private PerfilFacade serPer;

    private Perfil perfil;
    private List<Perfil> perfiles;

    public ControladorPerfil() {
        perfil = new Perfil();
        perfiles = new ArrayList<>();
        perfil.setOpcionesPerfilList(new ArrayList<OpcionesPerfil>());
    } 

    @PostConstruct
    public void cargarDatos() {
        perfiles = serPer.findAll();
//        System.out.println(perfiles);
    }

    public void limpiar() {
        perfil = new Perfil();
    }

    public void actualizarPerfil() {
        //System.out.println("*************" + perfil.getCodigoPerfil());
        serPer.edit(perfil);
        limpiar();
    }

    public void eliminarPerfil() {
        try {
            serPer.remove(perfil);
            this.perfiles = serPer.findAll();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Perfil Eliminado", ""));
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), ""));
        }
        limpiar();

    }
    
    public void valueChangeMethod(ValueChangeEvent e) {

        
        for (int i = 0; i < perfiles.size(); i++) {
            Perfil per = perfiles.get(i);
            if (per.getPerCodigo()==Integer.parseInt(e.getNewValue().toString())) {
                perfil=per;
            }
        }
        
    }
    

    public void ingresarPerfil() {
        try {
            perfil.setPerCodigo(null);
            serPer.create(perfil);
            this.perfiles = serPer.findAll();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Perfil Ingresada", ""));
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), ""));
        }
        limpiar();
    }

    public Perfil getPerfil(int cod) {
        return serPer.find(cod);
    }

    public PerfilFacade getSerPer() {
        return serPer;
    }

    public void setSerPer(PerfilFacade serPer) {
        this.serPer = serPer;
    }

    

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    public List<Perfil> getPerfils() {
        return perfiles;
    }

    public void setPerfils(List<Perfil> perfiles) {
        this.perfiles = perfiles;
    }

}
