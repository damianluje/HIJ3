/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Damian
 */
@Entity
@Table(name = "xeopp_opciones_perfil")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OpcionesPerfil.findAll", query = "SELECT o FROM OpcionesPerfil o"),
    @NamedQuery(name = "OpcionesPerfil.findByXeperCodigo", query = "SELECT o FROM OpcionesPerfil o WHERE o.opcionesPerfilPK.xeperCodigo = :xeperCodigo"),
    @NamedQuery(name = "OpcionesPerfil.findByXeopcCodigo", query = "SELECT o FROM OpcionesPerfil o WHERE o.opcionesPerfilPK.xeopcCodigo = :xeopcCodigo"),
    @NamedQuery(name = "OpcionesPerfil.findByXesisCodigo", query = "SELECT o FROM OpcionesPerfil o WHERE o.opcionesPerfilPK.xesisCodigo = :xesisCodigo"),
    @NamedQuery(name = "OpcionesPerfil.findByXeoppFechaAsignacion", query = "SELECT o FROM OpcionesPerfil o WHERE o.xeoppFechaAsignacion = :xeoppFechaAsignacion"),
    @NamedQuery(name = "OpcionesPerfil.findByXeoppActivo", query = "SELECT o FROM OpcionesPerfil o WHERE o.xeoppActivo = :xeoppActivo")})
public class OpcionesPerfil implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected OpcionesPerfilPK opcionesPerfilPK;
    @Column(name = "XEOPP_FECHA_ASIGNACION")
    @Temporal(TemporalType.DATE)
    private Date xeoppFechaAsignacion;
    @Column(name = "XEOPP_ACTIVO")
    private Character xeoppActivo;
    @JoinColumns({
        @JoinColumn(name = "XEOPC_CODIGO", referencedColumnName = "XEOPC_CODIGO", insertable = false, updatable = false),
        @JoinColumn(name = "XESIS_CODIGO", referencedColumnName = "XESIS_CODIGO", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Opciones opciones;
    @JoinColumn(name = "XEPER_CODIGO", referencedColumnName = "XEPER_CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Perfil perfil;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "opcionesPerfil")
    private Rol rol;

    public OpcionesPerfil() {
    }

    public OpcionesPerfil(OpcionesPerfilPK opcionesPerfilPK) {
        this.opcionesPerfilPK = opcionesPerfilPK;
    }

    public OpcionesPerfil(int xeperCodigo, int xeopcCodigo, int xesisCodigo) {
        this.opcionesPerfilPK = new OpcionesPerfilPK(xeperCodigo, xeopcCodigo, xesisCodigo);
    }

    public OpcionesPerfilPK getOpcionesPerfilPK() {
        return opcionesPerfilPK;
    }

    public void setOpcionesPerfilPK(OpcionesPerfilPK opcionesPerfilPK) {
        this.opcionesPerfilPK = opcionesPerfilPK;
    }

    public Date getXeoppFechaAsignacion() {
        return xeoppFechaAsignacion;
    }

    public void setXeoppFechaAsignacion(Date xeoppFechaAsignacion) {
        this.xeoppFechaAsignacion = xeoppFechaAsignacion;
    }

    public Character getXeoppActivo() {
        return xeoppActivo;
    }

    public void setXeoppActivo(Character xeoppActivo) {
        this.xeoppActivo = xeoppActivo;
    }

    public Opciones getOpciones() {
        return opciones;
    }

    public void setOpciones(Opciones opciones) {
        this.opciones = opciones;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (opcionesPerfilPK != null ? opcionesPerfilPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OpcionesPerfil)) {
            return false;
        }
        OpcionesPerfil other = (OpcionesPerfil) object;
        if ((this.opcionesPerfilPK == null && other.opcionesPerfilPK != null) || (this.opcionesPerfilPK != null && !this.opcionesPerfilPK.equals(other.opcionesPerfilPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.OpcionesPerfil[ opcionesPerfilPK=" + opcionesPerfilPK + " ]";
    }
    
}
