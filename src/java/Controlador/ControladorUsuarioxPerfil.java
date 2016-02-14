/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Perfil;
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
import Modelo.Usuario;
import Servicios.PerfilFacade;
import Servicios.SistemaFacade;
import Servicios.UsuarioFacade;
import javax.faces.event.ValueChangeEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.TransferEvent;
import org.primefaces.event.UnselectEvent;
import org.primefaces.model.DualListModel;

/**
 *
 * @author Damian
 */
@ManagedBean
@ViewScoped
public class ControladorUsuarioxPerfil implements Serializable {

    @EJB
    private PerfilFacade serPer;
    @EJB
    private UsuarioFacade serUsu;

    private Perfil perfil;
    private List<Perfil> perfiles;
    private List<Usuario> usuariosList;
    private DualListModel<Usuario> usuarios;
    private Integer codPerfil;

    public ControladorUsuarioxPerfil() {
        perfil = new Perfil();
        perfiles = new ArrayList<>();
        usuariosList = new ArrayList<>();
    }

    @PostConstruct
    public void cargarDatos() {
        perfiles = serPer.findAll();
        usuariosList = serUsu.findAll();
        List<Usuario> usuSource = new ArrayList<Usuario>();
        List<Usuario> usuTarget = new ArrayList<Usuario>();

        for (Usuario usu : usuariosList) {
            if (usu.getPerCodigo() == null) {
                usuSource.add(usu);
            }
        }
        usuarios = new DualListModel<Usuario>(usuSource, usuTarget);

    }

    public void limpiar() {
        perfil = new Perfil();
    }

    public void valueChangeMethod(ValueChangeEvent e) {

        for (Perfil per : perfiles) {
            if (per.getPerCodigo() == Integer.parseInt(e.getNewValue().toString())) {
                System.out.println("[DEBUG ControladorUsuarioxPerfil valueChangeMethod] Perfil asignado:"+per);
                perfil = per;
                cargarUsuarios();
            }
        }

    }

    public void cargarUsuarios() {
        if (perfil != null) {
            usuariosList = serUsu.findAll();
            List<Usuario> usuSource = new ArrayList<Usuario>();
            List<Usuario> usuTarget = new ArrayList<Usuario>();
            //System.out.println("[DEBUG ControladorUsuarioxPerfil cargarUsuarios] perfil"+perfil);
            for (Usuario usu : usuariosList) {
                //System.out.println("[DEBUG ControladorUsuarioxPerfil cargarUsuarios] perfil usu: "+usu.getPerCodigo());
                if (usu.getPerCodigo() == null) {
                    usuSource.add(usu);
                }else if (usu.getPerCodigo().getPerCodigo()==perfil.getPerCodigo()) {
                    usuTarget.add(usu);
                }
            }
            //System.out.println("[DEBUG ControladorUsuarioxPerfil cargarUsuarios] usuTarget:"+ usuTarget);
            usuarios = new DualListModel<Usuario>(usuSource, usuTarget);
        }else{
            System.out.println("[ERROR ControladorUsuariosxPerfil cargarUsuarios] perfil null");
        }
    }

    public Integer getCodPerfil() {
        return codPerfil;
    }

    public void setCodPerfil(Integer codPerfil) {
        this.codPerfil = codPerfil;
        for (Perfil per : perfiles) {
            if (per.getPerCodigo() == codPerfil) {
                perfil = per;
            }
        }
    }
    
    public void guardar(){
        System.out.println("[DEBUG onTransfer] source: "+this.usuarios.getSource());
        for (Usuario usuario : usuarios.getSource()) {
            usuario.setPerCodigo(null);
            serUsu.edit(usuario);
        }
        System.out.println("[DEBUG onTransfer] target: "+this.usuarios.getTarget());
        for (Usuario usuario : usuarios.getTarget()) {
            usuario.setPerCodigo(perfil);
            serUsu.edit(usuario);
        }
        
    }
    
    public void onTransfer(TransferEvent event) {
        guardar();
        
        FacesMessage msg = new FacesMessage();
        msg.setSeverity(FacesMessage.SEVERITY_INFO);
        msg.setSummary("Usuarios cambiados");
         
        FacesContext.getCurrentInstance().addMessage(null, msg);
        System.out.println("");
    } 
 
    public void onSelect(SelectEvent event) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Item Selected", event.getObject().toString()));
    }
     
    public void onUnselect(UnselectEvent event) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Item Unselected", event.getObject().toString()));
    }
     
    public void onReorder() {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "List Reordered", null));
    } 

    public PerfilFacade getSerPer() {
        return serPer;
    }

    public void setSerPer(PerfilFacade serPer) {
        this.serPer = serPer;
    }

    public UsuarioFacade getSerUsu() {
        return serUsu;
    }

    public void setSerUsu(UsuarioFacade serUsu) {
        this.serUsu = serUsu;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    public List<Perfil> getPerfiles() {
        return perfiles;
    }

    public void setPerfiles(List<Perfil> perfiles) {
        this.perfiles = perfiles;
    }

    public DualListModel<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(DualListModel<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

}
