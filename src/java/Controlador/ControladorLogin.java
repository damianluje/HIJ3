/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

/**
 *
 * @author Damian
 */
import Modelo.Usuario;
import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import Servicios.UsuarioFacade;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import javax.ejb.EJB;

@ManagedBean
@SessionScoped
public class ControladorLogin implements Serializable {

    private static final long serialVersionUID = 1094801825228386363L;

    @EJB
    private UsuarioFacade serUsu;
    private String pwd;
    private String msg;
    private String user;

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    //validate login
    public String validateUsernamePassword() {

        ArrayList<Usuario> lista = new ArrayList<>(serUsu.findAll());

        boolean valid = false;

        for (int i = 0; i < lista.size(); i++) {
            Usuario usuario = lista.get(i);
            try {
                MessageDigest md = MessageDigest.getInstance("MD5");
                md.update(pwd.getBytes());
                byte[] digest = md.digest();
                StringBuffer sb = new StringBuffer();
                for (byte b : digest) {
                    sb.append(String.format("%02x", b & 0xff));
                }
                if (usuario.getUsuId().equals(user) && usuario.getUsuPassword().equals(sb.toString())) {
                    valid = true;
                    break;
                }
            } catch (NoSuchAlgorithmException noSuchAlgorithmException) {
                System.out.println("Error: MD5");
            }

        }

        if (valid) {
            HttpSession session = SessionBean.getSession();
            session.setAttribute("username", user);
            return "admin";
        } else {
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                            "Incorrect Username and Passowrd",
                            "Please enter correct username and Password"));
            return "login";
        }
    }

    //logout event, invalidate session
    public String logout() {
        HttpSession session = SessionBean.getSession();
        session.invalidate();
        return "login";
    }
}
