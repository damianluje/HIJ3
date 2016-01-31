/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Damian
 */
@Entity
@Table(name = "xeopc_opciones")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Opciones.findAll", query = "SELECT o FROM Opciones o"),
    @NamedQuery(name = "Opciones.findByXeopcCodigo", query = "SELECT o FROM Opciones o WHERE o.opcionesPK.xeopcCodigo = :xeopcCodigo"),
    @NamedQuery(name = "Opciones.findByXesisCodigo", query = "SELECT o FROM Opciones o WHERE o.opcionesPK.xesisCodigo = :xesisCodigo"),
    @NamedQuery(name = "Opciones.findByXeopcDescipcion", query = "SELECT o FROM Opciones o WHERE o.xeopcDescipcion = :xeopcDescipcion")})
public class Opciones implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected OpcionesPK opcionesPK;
    @Size(max = 100)
    @Column(name = "XEOPC_DESCIPCION")
    private String xeopcDescipcion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "opciones")
    private Collection<OpcionesPerfil> opcionesPerfilCollection;
    @JoinColumn(name = "XESIS_CODIGO", referencedColumnName = "XESIS_CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Sistema sistema;

    public Opciones() {
    }

    public Opciones(OpcionesPK opcionesPK) {
        this.opcionesPK = opcionesPK;
    }

    public Opciones(int xeopcCodigo, int xesisCodigo) {
        this.opcionesPK = new OpcionesPK(xeopcCodigo, xesisCodigo);
    }

    public OpcionesPK getOpcionesPK() {
        return opcionesPK;
    }

    public void setOpcionesPK(OpcionesPK opcionesPK) {
        this.opcionesPK = opcionesPK;
    }

    public String getXeopcDescipcion() {
        return xeopcDescipcion;
    }

    public void setXeopcDescipcion(String xeopcDescipcion) {
        this.xeopcDescipcion = xeopcDescipcion;
    }

    @XmlTransient
    public Collection<OpcionesPerfil> getOpcionesPerfilCollection() {
        return opcionesPerfilCollection;
    }

    public void setOpcionesPerfilCollection(Collection<OpcionesPerfil> opcionesPerfilCollection) {
        this.opcionesPerfilCollection = opcionesPerfilCollection;
    }

    public Sistema getSistema() {
        return sistema;
    }

    public void setSistema(Sistema sistema) {
        this.sistema = sistema;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (opcionesPK != null ? opcionesPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Opciones)) {
            return false;
        }
        Opciones other = (Opciones) object;
        if ((this.opcionesPK == null && other.opcionesPK != null) || (this.opcionesPK != null && !this.opcionesPK.equals(other.opcionesPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.Opciones[ opcionesPK=" + opcionesPK + " ]";
    }
    
}
