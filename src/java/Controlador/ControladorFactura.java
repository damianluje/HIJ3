/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;
import Modelo.DetalleFactura;
import Modelo.Empleado;
import Modelo.Factura;
import Modelo.Proveedor;
import Modelo.Factura;
import Modelo.Factura;
import Modelo.Paciente;
import Modelo.TipoServicio;
import Servicios.DetalleFacturaFacade;
import Servicios.EmpleadoFacade;
import Servicios.FacturaFacade;
import Servicios.PacienteFacade;
import Servicios.ProveedorFacade;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import Servicios.FacturaFacade;
import Servicios.FacturaFacade;
import Servicios.TipoServicioFacade;
import java.util.AbstractList;
import javax.faces.event.ValueChangeEvent;
/**
 *
 * @author homer
 */
@ManagedBean
@ViewScoped
public class ControladorFactura implements Serializable{
    @EJB
    private FacturaFacade serFac;
    private Factura factura;
    private List<Factura> facturas;
    @EJB
    private PacienteFacade serPac;
    private int codPac;
    @EJB
    private EmpleadoFacade serEmp;
    private int codEmp;
    @EJB
    private TipoServicioFacade serTip;    
    @EJB
    private DetalleFacturaFacade serDet;
    private DetalleFactura detalle;
    private List<DetalleFactura> detalles;
    private int codDet;
    private int codFac;

    public int getCodFac() {
        return codFac;
    }

    public void setCodFac(int codFac) {
        this.codFac = codFac;
    }

    public int getCodDet() {
        return codDet;
    }

    public void setCodDet(int codDet) {
        this.codDet = codDet;
        
    }

    public DetalleFacturaFacade getSerDet() {
        return serDet;
    }

    public void setSerDet(DetalleFacturaFacade serDet) {
        this.serDet = serDet;
    }

    public DetalleFactura getDetalle() {
        return detalle;
    }

    public void setDetalle(DetalleFactura detalle) {
        this.detalle = detalle;
        
    }
    
    public ControladorFactura() {
        factura=new Factura();
        facturas=new ArrayList<>();
        detalle= new DetalleFactura();
        detalles=new ArrayList<>();
    }
    @PostConstruct
    public void cargarDatos() {
        facturas = serFac.findAll();
    }
    public void limpiar() {
        factura = new Factura();
        detalle =new DetalleFactura();
    }
    public void actualizarFactura() {
        //System.out.println("*************" + opcion.getCodigoSistema());
        serFac.edit(factura);
        System.out.println("Actualizando Opcion: "+factura+factura.getFacCodigo());
        limpiar();
    }
    public void actualizarDetalleFactura() {
        //System.out.println("*************" + opcion.getCodigoSistema());
        serDet.edit(detalle);
        System.out.println("Actualizando Detalle: "+detalle+detalle.getDfaCodigo());
        limpiar();
    }
    public void eliminarFactura() {
        try {
            serFac.remove(factura);
            this.facturas = serFac.findAll();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Factura Eliminado", ""));
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), ""));
        }
        limpiar();

    }
    public void valueChangeMethod(ValueChangeEvent e) {

        for (int i = 0; i < facturas.size(); i++) {
            Factura opc = facturas.get(i);
            if (opc.getFacCodigo() == Integer.parseInt(e.getNewValue().toString())) {
                factura = opc;
                }
        }
         
    }
    public void valueChangeMethodPaciente(ValueChangeEvent e) {

        Paciente pac=serPac.find(Integer.parseInt(e.getNewValue().toString()));
        
        factura.setPacCodigo(pac);
        System.out.println("Proveedor: "+pac.getPacCodigo());    
    }
    public void valueChangeMethodEmpleado(ValueChangeEvent e) {

        Empleado pro=serEmp.find(Integer.parseInt(e.getNewValue().toString()));
        
        factura.setEmpCodigo(pro);
        System.out.println("Proveedor: "+pro.getEmpCodigo());    
    }
     public void valueChangeMethodTipoServicio(ValueChangeEvent e) {

         TipoServicio pro=serTip.find(Integer.parseInt(e.getNewValue().toString()));
        
        detalle.setTsvCodigo(pro);  
    }
    public void valueChangeMethodFactura(ValueChangeEvent e) {

        for (int i = 0; i < factura.getDetalleFacturaList().size(); i++) {
            DetalleFactura opc = factura.getDetalleFacturaList().get(i);
            if (opc.getDfaCodigo() == Integer.parseInt(e.getNewValue().toString())) {
                detalle = opc;
                }
        }
    }
    public void ingresarFactura() {
        try {
            System.out.println("Factura:" + factura+factura.getFacCodigo());
            serFac.create(factura);
            this.facturas = serFac.findAll();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Factura Ingresado", ""));
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), "-_-"));
        }
        limpiar();
    }
    public void ingresarDetalleFactura() {
        try {
            System.out.println("Detalle Factura:" + detalle+detalle.getDfaCodigo());
            detalle.setFacCodigo(factura);
            serDet.create(detalle);
            this.detalles= serDet.findAll();
            this.facturas = serFac.findAll();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Detalle Ingresado: Porfavor seleccione en la cabezera la Factura N:"+ detalle.getFacCodigo().getFacCodigo() , ""));
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), "-_-"));
        }
        limpiar();
    }
    public void eliminarDetalleFactura() {
        try {
            serDet.remove(detalle);
            this.detalles= serDet.findAll();
            this.facturas = serFac.findAll();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Detalle Eliminado: Porfavor seleccione en la cabezera la Factura N:"+ factura.getFacCodigo() , ""));
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), "-_-"));
        }
        limpiar();
    }

    public FacturaFacade getSerFac() {
        return serFac;
    }

    public void setSerFac(FacturaFacade serFac) {
        this.serFac = serFac;
    }

    public Factura getFactura() {
        return factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }

    public List<Factura> getFacturas() {
        return facturas;
    }

    public void setFacturas(List<Factura> facturas) {
        this.facturas = facturas;
    }

    public int getCodPac() {
        return codPac;
    }

    public void setCodPac(int codPac) {
        this.codPac = codPac;
        Paciente pac=serPac.find(codPac);
        
        factura.setPacCodigo(pac);
    }

    public int getCodEmp() {
        return codEmp;
    }

    public void setCodEmp(int codEmp) {
        this.codEmp = codEmp;
        Empleado emp=serEmp.find(codEmp);
        
        factura.setEmpCodigo(emp);
    }
    
    
}
