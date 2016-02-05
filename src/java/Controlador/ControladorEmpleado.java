/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Empleado;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import Servicios.EmpleadoFacade;
import java.util.AbstractList;
import javax.faces.event.ValueChangeEvent;
/**
 *
 * @author homer
 */
@ManagedBean
@ViewScoped
public class ControladorEmpleado implements Serializable{
    @EJB
    private EmpleadoFacade serEmp;
    private Empleado empleado;
    private List<Empleado> empleados;

    public ControladorEmpleado() {
        empleado= new Empleado();
        empleados = new ArrayList<Empleado>();
    }
    @PostConstruct
    public void cargarDatos() {
        empleados = serEmp.findAll();
    }
    public void limpiar() {
        empleado = new Empleado();
    }
    public void actualizarEmpleado() {
        //System.out.println("*************" + opcion.getCodigoSistema());
        serEmp.edit(empleado);
        System.out.println("Actualizando Opcion: "+empleado+empleado.getEmpCodigo());
        limpiar();
    }
    public void eliminarEmpleado() {
        try {
            serEmp.remove(empleado);
            this.empleados = serEmp.findAll();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Empleado Eliminado", ""));
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), ""));
        }
        limpiar();

    }
    public void valueChangeMethod(ValueChangeEvent e) {

        for (int i = 0; i < empleados.size(); i++) {
            Empleado opc = empleados.get(i);
            if (opc.getEmpCodigo() == Integer.parseInt(e.getNewValue().toString())) {
                empleado = opc;
                }
        }

    }
    public void ingresarEmpleado() {
        try {
            System.out.println("Empleado:" + empleado+empleado.getEmpCodigo());
            serEmp.create(empleado);
            this.empleados = serEmp.findAll();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Empleado Ingresado", ""));
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), ""));
        }
        limpiar();
    }

    public EmpleadoFacade getSerEmp() {
        return serEmp;
    }

    public void setSerEmp(EmpleadoFacade serEmp) {
        this.serEmp = serEmp;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public List<Empleado> getEmpleados() {
        return empleados;
    }

    public void setEmpleados(List<Empleado> empleados) {
        this.empleados = empleados;
    }
    
    
}
