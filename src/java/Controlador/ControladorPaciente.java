/*
 * To change this license header, choose License Headers in Pacject Pacperties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;
import Modelo.Paciente;
import Servicios.PacienteFacade;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.util.AbstractList;
import javax.faces.event.ValueChangeEvent;
/**
 *
 * @author homer
 */
@ManagedBean
@ViewScoped
public class ControladorPaciente implements Serializable{
    @EJB
    private PacienteFacade serPac;
    private Paciente paciente;
    private List<Paciente> pacientes;

    public ControladorPaciente() {
        paciente=new Paciente();
        pacientes=new ArrayList<>();
    }
    @PostConstruct
    public void cargarDatos() {
        pacientes = serPac.findAll();
    }
    public void limpiar() {
        paciente = new Paciente();
    }
    public void actualizarPaciente() {
        //System.out.println("*************" + opcion.getCodigoSistema());
        serPac.edit(paciente);
        System.out.println("Actualizando Opcion: "+paciente+paciente.getPacCodigo());
        limpiar();
    }
    public void eliminarPaciente() {
        try {
            serPac.remove(paciente);
            this.pacientes = serPac.findAll();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Paciente Eliminado", ""));
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), ""));
        }
        limpiar();

    }
    public void valueChangeMethod(ValueChangeEvent e) {

        for (int i = 0; i < pacientes.size(); i++) {
            Paciente opc = pacientes.get(i);
            if (opc.getPacCodigo() == Integer.parseInt(e.getNewValue().toString())) {
                paciente = opc;
                }
        }

    }
    public void ingresarPaciente() {
        try {
            System.out.println("Paciente:" + paciente+paciente.getPacCodigo());
            serPac.create(paciente);
            this.pacientes = serPac.findAll();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Paciente Ingresado", ""));
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), ""));
        }
        limpiar();
    }

    public PacienteFacade getSerPac() {
        return serPac;
    }

    public void setSerPac(PacienteFacade serPac) {
        this.serPac = serPac;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public List<Paciente> getPacientes() {
        return pacientes;
    }

    public void setPacientes(List<Paciente> pacientes) {
        this.pacientes = pacientes;
    }
    
}
