/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.io.Serializable;
import java.util.List;
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
@Table(name = "xesis_sistema")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Sistema.findAll", query = "SELECT s FROM Sistema s"),
    @NamedQuery(name = "Sistema.findByXesisCodigo", query = "SELECT s FROM Sistema s WHERE s.sis_codigo = :sis_codigo"),
    @NamedQuery(name = "Sistema.findByXesisDescripcion", query = "SELECT s FROM Sistema s WHERE s.sis_descripcion = :sis_descripcion")})
public class Sistema implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "XESIS_CODIGO")
    private Integer sis_codigo;
    @Size(max = 100)
    @Column(name = "XESIS_DESCRIPCION")
    private String sis_descripcion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sis_codigo")
    private List<Opciones> opcionesList;

    public Sistema() {
    }

    public Sistema(Integer sis_codigo) {
        this.sis_codigo = sis_codigo;
    }

    public Integer getSisCodigo() {
        return sis_codigo;
    }

    public void setSisCodigo(Integer sis_codigo) {
        this.sis_codigo = sis_codigo;
    }

    public String getSisDescripcion() {
        return sis_descripcion;
    }

    public void setSisDescripcion(String sis_descripcion) {
        this.sis_descripcion = sis_descripcion;
    }

    @XmlTransient
    public List<Opciones> getOpcionesList() {
        return opcionesList;
    }

    public void setOpcionesList(List<Opciones> opcionesList) {
        this.opcionesList = opcionesList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (sis_codigo != null ? sis_codigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sistema)) {
            return false;
        }
        Sistema other = (Sistema) object;
        if ((this.sis_codigo == null && other.sis_codigo != null) || (this.sis_codigo != null && !this.sis_codigo.equals(other.sis_codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.Sistema[ sis_codigo=" + sis_codigo + " ]";
    }
    
}
