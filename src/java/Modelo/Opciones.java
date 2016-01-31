/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
    @NamedQuery(name = "Opciones.findByXeopcCodigo", query = "SELECT o FROM Opciones o WHERE o.opc_codigo = :opc_codigo"),
    @NamedQuery(name = "Opciones.findByXeopcDescipcion", query = "SELECT o FROM Opciones o WHERE o.opc_descipcion = :opc_descipcion")})
public class Opciones implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "XEOPC_CODIGO")
    private Integer opc_codigo;
    @Size(max = 100)
    @Column(name = "XEOPC_DESCIPCION")
    private String opc_descipcion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "opciones")
    private Collection<OpcionesPerfil> opcionesPerfilCollection;
    @JoinColumn(name = "XESIS_CODIGO", referencedColumnName = "XESIS_CODIGO")
    @ManyToOne(optional = false)
    private Sistema sis_codigo;

    public Opciones() {
    }

    public Opciones(Integer opc_codigo) {
        this.opc_codigo = opc_codigo;
    }

    public Integer getOpcCodigo() {
        return opc_codigo;
    }

    public void setOpcCodigo(Integer opc_codigo) {
        this.opc_codigo = opc_codigo;
    }

    public String getXeopcDescipcion() {
        return opc_descipcion;
    }

    public void setXeopcDescipcion(String opc_descipcion) {
        this.opc_descipcion = opc_descipcion;
    }

    @XmlTransient
    public Collection<OpcionesPerfil> getOpcionesPerfilCollection() {
        return opcionesPerfilCollection;
    }

    public void setOpcionesPerfilCollection(Collection<OpcionesPerfil> opcionesPerfilCollection) {
        this.opcionesPerfilCollection = opcionesPerfilCollection;
    }

    public Sistema getSisCodigo() {
        return sis_codigo;
    }

    public void setSisCodigo(Sistema sis_codigo) {
        this.sis_codigo = sis_codigo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (opc_codigo != null ? opc_codigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Opciones)) {
            return false;
        }
        Opciones other = (Opciones) object;
        if ((this.opc_codigo == null && other.opc_codigo != null) || (this.opc_codigo != null && !this.opc_codigo.equals(other.opc_codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.Opciones[ opc_codigo=" + opc_codigo + " ]";
    }
    
}
