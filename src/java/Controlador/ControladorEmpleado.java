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
public class ControladorEmpleado implements Serializable {

    @EJB
    private EmpleadoFacade serEmp;
    private Empleado empleado;
    private List<Empleado> empleados;

    public ControladorEmpleado() {
        empleado = new Empleado();
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
        System.out.println("Actualizando Opcion: " + empleado + empleado.getEmpCodigo());
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
            System.out.println("Empleado:" + empleado + empleado.getEmpCodigo());
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

    public boolean validadorDeCedula(String cedula) {
        boolean cedulaCorrecta = false;

        try {

            if (cedula.length() == 10) // ConstantesApp.LongitudCedula
            {
                int tercerDigito = Integer.parseInt(cedula.substring(2, 3));
                if (tercerDigito < 6) {
// Coeficientes de validación cédula
// El decimo digito se lo considera dígito verificador
                    int[] coefValCedula = {2, 1, 2, 1, 2, 1, 2, 1, 2};
                    int verificador = Integer.parseInt(cedula.substring(9, 10));
                    int suma = 0;
                    int digito = 0;
                    for (int i = 0; i < (cedula.length() - 1); i++) {
                        digito = Integer.parseInt(cedula.substring(i, i + 1)) * coefValCedula[i];
                        suma += ((digito % 10) + (digito / 10));
                    }

                    if ((suma % 10 == 0) && (suma % 10 == verificador)) {
                        cedulaCorrecta = true;
                    } else if ((10 - (suma % 10)) == verificador) {
                        cedulaCorrecta = true;
                    } else {
                        cedulaCorrecta = false;
                    }
                } else {
                    cedulaCorrecta = false;
                }
            } else {
                cedulaCorrecta = false;
            }
        } catch (NumberFormatException nfe) {
            cedulaCorrecta = false;
        } catch (Exception err) {
            System.out.println("Una excepcion ocurrio en el proceso de validadcion");
            cedulaCorrecta = false;
        }

        if (!cedulaCorrecta) {
            System.out.println("La Cédula ingresada es Incorrecta");
        }
        return cedulaCorrecta;
    }

}
