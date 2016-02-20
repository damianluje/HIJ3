/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Empleado;
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
import Modelo.Usuario;
import Modelo.Usuario;
import Servicios.EmpleadoFacade;
import Servicios.PerfilFacade;
import Servicios.UsuarioFacade;
import Servicios.UsuarioFacade;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.faces.event.ValueChangeEvent;

/**
 *
 * @author homer
 */
@ManagedBean
@ViewScoped
public class ControladorUsuario implements Serializable {

    @EJB
    private UsuarioFacade serUsu;
    @EJB
    private PerfilFacade serPer;
    @EJB
    private EmpleadoFacade serEmp;
    private Usuario usuario;
    private Empleado empleado;
    private Perfil perfil;
    private List<Perfil> perfiles;
    private List<Usuario> usuarios;
    private List<Empleado> empleados;
    private Integer codEmpleado;
    private Integer codPerfil;
    private String password;
    private String passwordOriginal;
    private String hashOriginal;

    public ControladorUsuario() {
        usuario = new Usuario();
        usuarios = new ArrayList<>();
        empleados = new ArrayList<>();
        perfiles = new ArrayList<>();

    }

    @PostConstruct
    public void cargarDatos() {
        //System.out.println("[DEBUG ControladorUsuario] serSis hash:"+serUsu.hashCode());
        usuarios = serUsu.findAll();
        empleados = serEmp.findAll();
        perfiles = serPer.findAll();
//        System.out.println(usuarios);
    }

    public void limpiar() {
        usuario = new Usuario();
    }

    public void actualizarUsuario() {
        //System.out.println("*************" + usuario.getCodigoUsuario());
        serUsu.edit(usuario);
        limpiar();
    }

    public void actualizarPass() {
        System.out.println("[DEBUG ControladorUsuario] usuario.getUsuPassword(): " + usuario.getUsuPassword());
        System.out.println("[DEBUG ControladorUsuario] hashMD5(passwordOriginal): " + hashMD5(passwordOriginal));
        if (hashMD5(passwordOriginal).equals(usuario.getUsuPassword())) {

            usuario.setUsuPassword(hashMD5(password));
            actualizarUsuario();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Contraseña Cambiada", ""));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error al validar la contraseña actual", ""));
        }
    }

    public void eliminarUsuario() {
        try {
            serUsu.remove(usuario);
            this.usuarios = serUsu.findAll();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Usuario Eliminado", ""));
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), ""));
        }
        limpiar();

    }

    public void valueChangeMethod(ValueChangeEvent e) {

        for (int i = 0; i < usuarios.size(); i++) {
            Usuario usu = usuarios.get(i);
            if (usu.getUsuCodigo() == Integer.parseInt(e.getNewValue().toString())) {
                usuario = usu;
                setCodEmpleado(usuario.getEmpCodigo().getEmpCodigo());
                setCodPerfil(usuario.getPerCodigo().getPerCodigo());
            }
        }

    }

    public void valueChangeMethodEmp(ValueChangeEvent e) {

        for (int i = 0; i < empleados.size(); i++) {
            Empleado emp = empleados.get(i);
            if (emp.getEmpCodigo() == Integer.parseInt(e.getNewValue().toString())) {
                empleado = emp;
                usuario.setEmpCodigo(empleado);
            }
        }
    }

    public void valueChangeMethodPer(ValueChangeEvent e) {

        if (e.getNewValue() == null) {
            perfil = null;
            usuario.setPerCodigo(null);
            System.out.println("[DEBUG ControladorUsuario] Seleccionado perfil null");
        } else {
            if (e.getNewValue().toString().isEmpty()) {
                perfil = null;
                usuario.setPerCodigo(null);
                System.out.println("[DEBUG ControladorUsuario] Seleccionado perfil null");
            }
            for (int i = 0; i < perfiles.size(); i++) {
                Perfil per = perfiles.get(i);
                if (per.getPerCodigo() == Integer.parseInt(e.getNewValue().toString())) {
                    perfil = per;
                    usuario.setPerCodigo(perfil);
                }
            }
        }

    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    public Integer getCodEmpleado() {
        return codEmpleado;
    }

    public void setCodEmpleado(Integer codEmpleado) {
        this.codEmpleado = codEmpleado;
        for (Empleado emp : empleados) {
            if (emp.getEmpCodigo() == codEmpleado) {
                empleado = emp;
                usuario.setEmpCodigo(empleado);
            }
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
                usuario.setPerCodigo(perfil);
            }
        }
    }

    public void setCodUsuario(Integer codUsuario) {
//        this.codUsuario = codUsuario;
//        for (int i = 0; i < usuarios.size(); i++) {
//            Usuario usu = usuarios.get(i);
//            if (usu.getUsuCodigo()==codUsuario) {
//                usuario=usu;
//            }
//        }
    }

    public String hashMD5(String pwd) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(pwd.getBytes());
            byte[] digest = md.digest();
            StringBuffer sb = new StringBuffer();
            for (byte b : digest) {
                sb.append(String.format("%02x", b & 0xff));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException noSuchAlgorithmException) {
            System.out.println("Error: MD5");
        }
        return null;
    }

    public void ingresarUsuario() {
        try {
            //usuario.setOpcionesList(new ArrayList<>());
            
            serUsu.create(usuario);
            this.usuarios = serUsu.findAll();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Usuario Ingresada", ""));
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), ""));
        }
        limpiar();
    }

    public Usuario getUsuario(int cod) {
        return serUsu.find(cod);
    }

    public UsuarioFacade getSerPer() {
        return serUsu;
    }

    public void setSerPer(UsuarioFacade serUsu) {
        this.serUsu = serUsu;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public UsuarioFacade getSerUsu() {
        return serUsu;
    }

    public void setSerUsu(UsuarioFacade serUsu) {
        this.serUsu = serUsu;
    }

    public List<Perfil> getPerfiles() {
        return perfiles;
    }

    public void setPerfiles(List<Perfil> perfiles) {
        this.perfiles = perfiles;
    }

    public List<Empleado> getEmpleados() {
        return empleados;
    }

    public void setEmpleados(List<Empleado> empleados) {
        this.empleados = empleados;
    }

    public void setSerPer(PerfilFacade serPer) {
        this.serPer = serPer;
    }

    public EmpleadoFacade getSerEmp() {
        return serEmp;
    }

    public void setSerEmp(EmpleadoFacade serEmp) {
        this.serEmp = serEmp;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {

        this.password = password;

    }

    public void handleKeyEvent() {
        usuario.setUsuPassword(hashMD5(password));
    }

    public void handleKeyEventPassOriginal() {
        this.setHashOriginal(hashMD5(passwordOriginal));
    }

    public String getPasswordOriginal() {
        return passwordOriginal;
    }

    public void setPasswordOriginal(String passwordOriginal) {
        this.passwordOriginal = passwordOriginal;
    }

    public String getHashOriginal() {
        return hashOriginal;
    }

    public void setHashOriginal(String hashOriginal) {
        this.hashOriginal = hashOriginal;
    }

}
