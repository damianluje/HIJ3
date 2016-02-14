/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Opciones;
import Modelo.OpcionesPerfil;
import Modelo.Perfil;
import Modelo.Rol;
import Modelo.Sistema;
import Modelo.Usuario;
import Servicios.OpcionesFacade;
import Servicios.OpcionesPerfilFacade;
import Servicios.PerfilFacade;
import Servicios.SistemaFacade;
import Servicios.UsuarioFacade;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Damian
 */
@ManagedBean
@SessionScoped
public class ControladorPermisos {

    @EJB
    private SistemaFacade serSis;
    @EJB
    private PerfilFacade serPer;

    private List<Sistema> listaSis;
    private List<Perfil> listaPer;
    private List<Usuario> listaUsu;

    private Perfil perfil;
    private Integer codPerfil;
    private String username;
    private Usuario usuario;

    @EJB
    private UsuarioFacade serUsu;

    public ControladorPermisos() {
        listaSis = new ArrayList<>();
    }

    @PostConstruct
    public void cargarDatos() {
        //System.out.println("[DEBUG ControladorPermisos] serSis hash:"+serSis.hashCode());

        listaSis = serSis.findAll();

    }

    public String cargarUsuarioActual() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
        Object objUser = session.getAttribute("username");
        if (objUser == null) {
            System.out.println("No se ha iniciado sesion");
            return "No se ha iniciado sesion";
        }
        username = objUser.toString();
        System.out.println("[INFO] Usuario:" + username);
        getPerfilByUsername();
        cargarDatos();
        crearOPvacios();
        return username;

    }

    public Rol getRol(String mod) {
        //System.out.println("[DEBUG ControladorPermisos getRol] mod: " + mod);
        if (perfil != null) {
            listaSis = serSis.findAll();

            for (Sistema sis : listaSis) {
                for (Opciones opc : sis.getOpcionesList()) {
                    if (opc.getOpcDescripcion().equals(mod)) {
                        for (OpcionesPerfil opp : opc.getOpcionesPerfilList()) {
                            if (opp.getOpcionesPerfilPK().getPerCodigo() == perfil.getPerCodigo()) {
                                //System.out.println("[DEBUG ControladorPermisos] "+opp.getRol());
                                return opp.getRol();
                            }
                        }
                    }
                }
            }
        } else {
            Rol r = new Rol();
            r.setLDelete(Boolean.FALSE);
            r.setLInsert(Boolean.FALSE);
            r.setLSelect(Boolean.FALSE);
            r.setLUpdate(Boolean.FALSE);
            //System.out.println("Perfil null en getRol");
            
            return r;
        }

        return null;
    }

    public Perfil getPerfilByUsername() {
        if (username != null) {
            listaPer = serPer.findAll();
            listaUsu = serUsu.findAll();
            for (Usuario usu : listaUsu) {
                if (usu.getUsuId().equals(username)) {
                    usuario = usu;
                    break;
                }
            }
            if (usuario != null) {
                for (Perfil per : listaPer) {
                    if (usuario.getPerCodigo() == null) {
                        return null;
                    }
                    if (per.getPerCodigo() == usuario.getPerCodigo().getPerCodigo()) {
                        perfil = per;
                        return perfil;
                    }
                }
            } else {
                System.out.println("usuario null en getPerfilByUsername");
            }
            return null;
        } else {
            System.out.println("username null en getPerfilByUsername");
        }
        return null;
    }

    public void crearOPvacios() {
        if (perfil != null) {
            serSis.clear();
            cargarDatos();

            for (Sistema sistema : listaSis) {
                //System.out.println("**[DEBUG ControladorPermisos] contains "+serSis.contains(sistema));
                //System.out.println("[DEBUG ControladorPermisos] Sistema:"+sistema.getSisDescripcion());
                for (Opciones opcion : sistema.getOpcionesList()) {
                    //System.out.println("[DEBUG ControladorPermisos] "+opcion.getOpcDescripcion());
                    if (opcion.getOpcionesPerfilList() == null) {
                        opcion.setOpcionesPerfilList(new ArrayList<>());
                    }
                    boolean exist = false;
                    for (OpcionesPerfil opl : opcion.getOpcionesPerfilList()) {
                        if (opl.getOpcionesPerfilPK().getPerCodigo() == perfil.getPerCodigo()
                                && opl.getOpcionesPerfilPK().getOpcCodigo() == opcion.getOpcCodigo()) {
                            exist = true;
                        }
                    }
                    if (!exist) {
                        OpcionesPerfil op = new OpcionesPerfil(perfil.getPerCodigo(), opcion.getOpcCodigo());
                        Rol rol = new Rol(perfil.getPerCodigo(), opcion.getOpcCodigo());
                        rol.setLSelect(Boolean.FALSE);
                        rol.setLInsert(Boolean.FALSE);
                        rol.setLUpdate(Boolean.FALSE);
                        rol.setLDelete(Boolean.FALSE);
                        op.setRol(rol);
                        op.setPerfil(perfil);
                        opcion.getOpcionesPerfilList().add(op);
                    }

                }
                serSis.edit(sistema);
                //System.out.println("**[DEBUG ControladorPermisos] contains "+sistema.getSisDescripcion()+" "+serSis.contains(sistema));
            }
            //System.out.println("-----------------------------------------------");
            //serSis.clear();
        } else {
            //System.out.println("perfil null en crearOPvacios");

        }
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    
}
