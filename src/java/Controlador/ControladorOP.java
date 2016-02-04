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
import Servicios.SistemaFacade;
import java.util.HashMap;
import javax.faces.event.ValueChangeEvent;

/**
 *
 * @author homer
 */
@ManagedBean
@ViewScoped
public class ControladorOP implements Serializable {

    @EJB
    private SistemaFacade serSis;
    @EJB
    private PerfilFacade serPer;
    @EJB
    private OpcionesFacade serOpc;
    @EJB
    private OpcionesPerfilFacade serOP;

    private List<OpcionesPerfil> listaOP;
    private List<Sistema> listaSis;

    private Perfil perfil;
    private Integer codPerfil;

    public ControladorOP() {
        listaOP = new ArrayList<>();
        listaSis = new ArrayList<>();
    }

    @PostConstruct
    public void cargarDatos() {
        listaOP = serOP.findAll();
        listaSis = serSis.findAll();
    }

    public void crearOPvacios() {
        if (perfil != null) {
            for (Sistema sistema : listaSis) {
                for (Opciones opcion : sistema.getOpcionesList()) {

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
            }

        }
    }

    public Rol getRol(int codOpc) {

        if (perfil != null) {
            for (Sistema sis : listaSis) {
                for (Opciones opc : sis.getOpcionesList()) {
                    for (OpcionesPerfil opp : opc.getOpcionesPerfilList()) {
                        if (opp.getOpcionesPerfilPK().getPerCodigo() == perfil.getPerCodigo()
                                && opp.getOpcionesPerfilPK().getOpcCodigo() == codOpc) {
                            return opp.getRol();
                        }
                    }
                }
            }
        }

        return null;
    }

    public void guardar() {
        for (Sistema sis : listaSis) {
            serSis.edit(sis);
        }
        serSis.flush();
    }

    public void valueChangeMethod(ValueChangeEvent e) {

        codPerfil = Integer.parseInt(e.getNewValue().toString());
        perfil = serPer.find(codPerfil);
        crearOPvacios();
    }

    public SistemaFacade getSerSis() {
        return serSis;
    }

    public void setSerSis(SistemaFacade serSis) {
        this.serSis = serSis;
    }

    public PerfilFacade getSerPer() {
        return serPer;
    }

    public void setSerPer(PerfilFacade serPer) {
        this.serPer = serPer;
    }

    public OpcionesFacade getSerOpc() {
        return serOpc;
    }

    public void setSerOpc(OpcionesFacade serOpc) {
        this.serOpc = serOpc;
    }

    public OpcionesPerfilFacade getSerOP() {
        return serOP;
    }

    public void setSerOP(OpcionesPerfilFacade serOP) {
        this.serOP = serOP;
    }

    public Integer getCodPerfil() {
        return codPerfil;
    }

    public void setCodPerfil(Integer codPerfil) {
        this.codPerfil = codPerfil;
    }

    public List<OpcionesPerfil> getListaOP() {
        return listaOP;
    }

    public void setListaOP(List<OpcionesPerfil> listaOP) {
        this.listaOP = listaOP;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    public List<Sistema> getListaSis() {
        return listaSis;
    }

    public void setListaSis(List<Sistema> listaSis) {
        this.listaSis = listaSis;
    }

}
