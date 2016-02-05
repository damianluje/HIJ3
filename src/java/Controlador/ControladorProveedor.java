
package Controlador;
import Modelo.Proveedor;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import Servicios.ProveedorFacade;
import java.util.AbstractList;
import javax.faces.event.ValueChangeEvent;
/**
 *
 * @author homer
 */
@ManagedBean
@ViewScoped
public class ControladorProveedor implements Serializable{
    @EJB
    private ProveedorFacade serPro;
    private Proveedor proveedor;
    private List<Proveedor> proveedores;

    public ControladorProveedor() {
        proveedor=new Proveedor();
        proveedores = new ArrayList<>();
    }
    @PostConstruct
    public void cargarDatos() {
        proveedores = serPro.findAll();
    }
    public void limpiar() {
        proveedor = new Proveedor();
    }
    public void actualizarProveedor() {
        //System.out.println("*************" + opcion.getCodigoSistema());
        serPro.edit(proveedor);
        System.out.println("Actualizando Opcion: "+proveedor+proveedor.getProCodigo());
        limpiar();
    }
    public void eliminarProveedor() {
        try {
            serPro.remove(proveedor);
            this.proveedores = serPro.findAll();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Proveedor Eliminado", ""));
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), ""));
        }
        limpiar();

    }
    public void valueChangeMethod(ValueChangeEvent e) {

        for (int i = 0; i < proveedores.size(); i++) {
            Proveedor opc = proveedores.get(i);
            if (opc.getProCodigo() == Integer.parseInt(e.getNewValue().toString())) {
                proveedor = opc;
                }
        }

    }
    public void ingresarProveedor() {
        try {
            System.out.println("Proveedor:" + proveedor+proveedor.getProCodigo());
            serPro.create(proveedor);
            this.proveedores = serPro.findAll();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Proveedor Ingresado", ""));
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), ""));
        }
        limpiar();
    }

    public ProveedorFacade getSerPro() {
        return serPro;
    }

    public void setSerPro(ProveedorFacade serPro) {
        this.serPro = serPro;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public List<Proveedor> getProveedors() {
        return proveedores;
    }

    public void setProveedors(List<Proveedor> proveedores) {
        this.proveedores = proveedores;
    }
}
