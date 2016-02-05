/*
 * To change this license header, choose License Headers in Tsvject Tsvperties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;
import Modelo.Proveedor;
import Modelo.TipoServicio;
import Modelo.TipoServicio;
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
import Servicios.TipoServicioFacade;
import Servicios.TipoServicioFacade;
import java.util.AbstractList;
import javax.faces.event.ValueChangeEvent;
/**
 *
 * @author homer
 */
@ManagedBean
@ViewScoped
public class ControladorTipoServicio implements Serializable{
    @EJB
    private TipoServicioFacade serTsv;
    @EJB
    private ProveedorFacade serPro;
    private TipoServicio tipoServicio;
    private List<TipoServicio> tipoServicios;
    
    private int codPro;

    public int getCodPro() {
        return codPro;
    }

    public void setCodPro(int codPro) {
        this.codPro = codPro;
        Proveedor pro=serPro.find(codPro);
        
        tipoServicio.setProCodigo(pro);
    }

    public ControladorTipoServicio() {
        tipoServicio= new TipoServicio();
        tipoServicios= new ArrayList<>();
    }
   
    @PostConstruct
    public void cargarDatos() {
        tipoServicios = serTsv.findAll();
    }
    public void limpiar() {
        tipoServicio = new TipoServicio();
    }
    public void actualizarTipoServicio() {
        //System.out.println("*************" + opcion.getCodigoSistema());
        serTsv.edit(tipoServicio);
        System.out.println("Actualizando Opcion: "+tipoServicio+tipoServicio.getTsvCodigo());
        limpiar();
    }
    public void eliminarTipoServicio() {
        try {
            serTsv.remove(tipoServicio);
            this.tipoServicios = serTsv.findAll();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "TipoServicio Eliminado", ""));
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), ""));
        }
        limpiar();

    }
    public void valueChangeMethod(ValueChangeEvent e) {

        for (int i = 0; i < tipoServicios.size(); i++) {
            TipoServicio opc = tipoServicios.get(i);
            if (opc.getTsvCodigo() == Integer.parseInt(e.getNewValue().toString())) {
                tipoServicio = opc;
                }
        }
         
    }
    public void valueChangeMethod1(ValueChangeEvent e) {

        Proveedor pro=serPro.find(Integer.parseInt(e.getNewValue().toString()));
        
        tipoServicio.setProCodigo(pro);
        System.out.println("Proveedor: "+pro.getProCodigo());    
    }
    
    public void ingresarTipoServicio() {
        try {
            System.out.println("TipoServicio:" + tipoServicio+tipoServicio.getTsvCodigo());
            serTsv.create(tipoServicio);
            this.tipoServicios = serTsv.findAll();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "TipoServicio Ingresado", ""));
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), "-_-"));
        }
        limpiar();
    }

    public TipoServicioFacade getSerTsv() {
        return serTsv;
    }

    public void setSerTsv(TipoServicioFacade serTsv) {
        this.serTsv = serTsv;
    }

    public TipoServicio getTipoServicio() {
        return tipoServicio;
    }

    public void setTipoServicio(TipoServicio tipoServicio) {
        this.tipoServicio = tipoServicio;
    }

    public List<TipoServicio> getTipoServicios() {
        return tipoServicios;
    }

    public void setTipoServicios(List<TipoServicio> tipoServicios) {
        this.tipoServicios = tipoServicios;
    }
    
}
