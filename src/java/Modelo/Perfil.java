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
@Table(name = "xeper_perfil")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Perfil.findAll", query = "SELECT p FROM Perfil p"),
    @NamedQuery(name = "Perfil.findByXeperCodigo", query = "SELECT p FROM Perfil p WHERE p.per_codigo = :per_codigo"),
    @NamedQuery(name = "Perfil.findByXeperDescripcion", query = "SELECT p FROM Perfil p WHERE p.per_descripcion = :per_descripcion")})
public class Perfil implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "XEPER_CODIGO")
    private Integer per_codigo;
    @Size(max = 100)
    @Column(name = "XEPER_DESCRIPCION")
    private String per_descripcion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "perfil")
    private Collection<OpcionesPerfil> opcionesPerfilCollection;
    @OneToMany(mappedBy = "per_codigo")
    private Collection<Usuario> usuarioCollection;

    public Perfil() {
    }

    public Perfil(Integer per_codigo) {
        this.per_codigo = per_codigo;
    }

    public Integer getPerCodigo() {
        return per_codigo;
    }

    public void setPerCodigo(Integer per_codigo) {
        this.per_codigo = per_codigo;
    }

    public String getPerDescripcion() {
        return per_descripcion;
    }

    public void setPerDescripcion(String per_descripcion) {
        this.per_descripcion = per_descripcion;
    }

    @XmlTransient
    public Collection<OpcionesPerfil> getOpcionesPerfilCollection() {
        return opcionesPerfilCollection;
    }

    public void setOpcionesPerfilCollection(Collection<OpcionesPerfil> opcionesPerfilCollection) {
        this.opcionesPerfilCollection = opcionesPerfilCollection;
    }

    @XmlTransient
    public Collection<Usuario> getUsuarioCollection() {
        return usuarioCollection;
    }

    public void setUsuarioCollection(Collection<Usuario> usuarioCollection) {
        this.usuarioCollection = usuarioCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (per_codigo != null ? per_codigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Perfil)) {
            return false;
        }
        Perfil other = (Perfil) object;
        if ((this.per_codigo == null && other.per_codigo != null) || (this.per_codigo != null && !this.per_codigo.equals(other.per_codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.Perfil[ per_codigo=" + per_codigo + " ]";
    }
    
}
