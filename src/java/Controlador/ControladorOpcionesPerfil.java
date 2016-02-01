/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

/**
 * /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates and open the template
 * in the editor.
 */
import Modelo.Opciones;
import Modelo.OpcionesPerfil;
import Modelo.Perfil;
import Modelo.Rol;
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
import Servicios.OpcionesPerfilFacade;
import Servicios.PerfilFacade;
import Servicios.RolFacade;
import Servicios.SistemaFacade;
import javax.el.ELContext;
import javax.faces.bean.ManagedProperty;
import javax.faces.component.UIComponent;
import javax.faces.event.ValueChangeEvent;
import org.primefaces.component.panel.Panel;

/**
 *
 * @author homer
 */
@ManagedBean
@ViewScoped
public class ControladorOpcionesPerfil implements Serializable {

    @EJB
    private OpcionesFacade serOpc;

    @EJB
    private OpcionesPerfilFacade oppSer;

    @EJB
    private RolFacade serRol;

    @EJB
    private PerfilFacade serPer;

    @EJB
    private SistemaFacade serSis;

    private OpcionesPerfil opcionPerfil;
    private Opciones opcion;
    private List<Opciones> opciones;
    private List<OpcionesPerfil> opcionesPerfil;
    private List<Perfil> perfiles;
    private List<Rol> roles;
    private List<Sistema> sistemas;

    public ControladorOpcionesPerfil() {
        opcion = new Opciones();
        opcion.setSisCodigo(new Sistema());
        opciones = new ArrayList<>();

    }

    @PostConstruct
    public void cargarDatos() {
        opciones = serOpc.findAll();
        perfiles = serPer.findAll();
        roles = serRol.findAll();
        sistemas = serSis.findAll();
        opcionesPerfil = oppSer.findAll();
//        System.out.println(opciones);
        crearListaVacia();
        opcionesPerfil = oppSer.findAll();
    }

    public void crearListaVacia() {
        for (int x = 0; x < perfiles.size(); x++) {
            Perfil perfil = perfiles.get(x);
            for (int i = 0; i < sistemas.size(); i++) {
                Sistema sistema = sistemas.get(i);

                for (int j = 0; j < sistema.getOpcionesCollection().size(); j++) {
                    Opciones opc = sistema.getOpcionesCollection().get(j);

                    if (exist(perfil.getPerCodigo(), opc.getOpcCodigo())) {
                        break;
                    } else {
                        OpcionesPerfil obj = new OpcionesPerfil(perfil.getPerCodigo(), opc.getOpcCodigo());

                        Rol r = new Rol(perfil.getPerCodigo(), opc.getOpcCodigo());
                        r.setLDelete('0');
                        r.setLInsert('0');
                        r.setLSelect('0');
                        r.setLUpdate('0');
                        obj.setRol(r);
                    }
                }

            }
        }
    }

    public boolean exist(int codPer, int codOpc) {

        opcionesPerfil = oppSer.findAll();
        for (int i = 0; i < opcionesPerfil.size(); i++) {
            OpcionesPerfil opp = opcionesPerfil.get(i);
            if (opp.getOpcionesPerfilPK().getOpcCodigo() == codOpc && opp.getOpcionesPerfilPK().getPerCodigo() == codPer) {
                return true;
            }
        }
        return false;
    }

    public void createUI() {
        UIComponent component = FacesContext.getCurrentInstance().getViewRoot().findComponent("paneles");
        if (component != null) {
            for (int i = 0; i < sistemas.size(); i++) {
                Sistema sistema = sistemas.get(i);
                Panel p = new Panel();
                p.setClosable(true);
                p.setHeader(sistema.getSisCodigo() + "-" + sistema.getSisDescripcion());
                p.setVisible(true);
                p.setId("panelSis" + sistema.getSisCodigo());
                component.getChildren().add(p);
            }
        } else {
            System.out.println("null");
        }

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

        Sistema sis = serSis.find(Integer.parseInt(e.getNewValue().toString()));

        opcion.setSisCodigo(sis);

    }

    public OpcionesPerfilFacade getOppSer() {
        return oppSer;
    }

    public void setOppSer(OpcionesPerfilFacade oppSer) {
        this.oppSer = oppSer;
    }

    public OpcionesPerfil getOpcionPerfil() {
        return opcionPerfil;
    }

    public void setOpcionPerfil(OpcionesPerfil opcionPerfil) {
        this.opcionPerfil = opcionPerfil;
    }

    public List<OpcionesPerfil> getOpcionesPerfil() {
        return opcionesPerfil;
    }

    public void setOpcionesPerfil(List<OpcionesPerfil> opcionesPerfil) {
        this.opcionesPerfil = opcionesPerfil;
    }
    
    public void guardar(){
        
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

    public RolFacade getSerRol() {
        return serRol;
    }

    public void setSerRol(RolFacade serRol) {
        this.serRol = serRol;
    }

    public PerfilFacade getSerPer() {
        return serPer;
    }

    public void setSerPer(PerfilFacade serPer) {
        this.serPer = serPer;
    }

    public SistemaFacade getSerSis() {
        return serSis;
    }

    public void setSerSis(SistemaFacade serSis) {
        this.serSis = serSis;
    }

    public List<Perfil> getPerfiles() {
        return perfiles;
    }

    public void setPerfiles(List<Perfil> perfiles) {
        this.perfiles = perfiles;
    }

    public List<Rol> getRoles() {
        return roles;
    }

    public void setRoles(List<Rol> roles) {
        this.roles = roles;
    }

    public List<Sistema> getSistemas() {
        return sistemas;
    }

    public void setSistemas(List<Sistema> sistemas) {
        this.sistemas = sistemas;
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
