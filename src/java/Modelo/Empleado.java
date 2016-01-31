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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Damian
 */
@Entity
@Table(name = "peemp_empleado")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Empleado.findAll", query = "SELECT e FROM Empleado e"),
    @NamedQuery(name = "Empleado.findByPeempCodigo", query = "SELECT e FROM Empleado e WHERE e.emp_codigo = :emp_codigo"),
    @NamedQuery(name = "Empleado.findByPeempNombres", query = "SELECT e FROM Empleado e WHERE e.peempNombres = :peempNombres"),
    @NamedQuery(name = "Empleado.findByPeempApellidos", query = "SELECT e FROM Empleado e WHERE e.peempApellidos = :peempApellidos"),
    @NamedQuery(name = "Empleado.findByPeempCedula", query = "SELECT e FROM Empleado e WHERE e.peempCedula = :peempCedula"),
    @NamedQuery(name = "Empleado.findByPeempPasaporte", query = "SELECT e FROM Empleado e WHERE e.peempPasaporte = :peempPasaporte")})
public class Empleado implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "PEEMP_CODIGO")
    private Integer emp_codigo;
    @Size(max = 100)
    @Column(name = "PEEMP_NOMBRES")
    private String peempNombres;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "PEEMP_APELLIDOS")
    private String peempApellidos;
    @Size(max = 10)
    @Column(name = "PEEMP_CEDULA")
    private String peempCedula;
    @Size(max = 10)
    @Column(name = "PEEMP_PASAPORTE")
    private String peempPasaporte;
    @OneToMany(mappedBy = "emp_codigo")
    private Collection<Factura> facturaCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "emp_codigo")
    private Collection<Usuario> usuarioCollection;

    public Empleado() {
    }

    public Empleado(Integer emp_codigo) {
        this.emp_codigo = emp_codigo;
    }

    public Empleado(Integer emp_codigo, String peempApellidos) {
        this.emp_codigo = emp_codigo;
        this.peempApellidos = peempApellidos;
    }

    public Integer getPeempCodigo() {
        return emp_codigo;
    }

    public void setPeempCodigo(Integer emp_codigo) {
        this.emp_codigo = emp_codigo;
    }

    public String getPeempNombres() {
        return peempNombres;
    }

    public void setPeempNombres(String peempNombres) {
        this.peempNombres = peempNombres;
    }

    public String getPeempApellidos() {
        return peempApellidos;
    }

    public void setPeempApellidos(String peempApellidos) {
        this.peempApellidos = peempApellidos;
    }

    public String getPeempCedula() {
        return peempCedula;
    }

    public void setPeempCedula(String peempCedula) {
        this.peempCedula = peempCedula;
    }

    public String getPeempPasaporte() {
        return peempPasaporte;
    }

    public void setPeempPasaporte(String peempPasaporte) {
        this.peempPasaporte = peempPasaporte;
    }

    @XmlTransient
    public Collection<Factura> getFacturaCollection() {
        return facturaCollection;
    }

    public void setFacturaCollection(Collection<Factura> facturaCollection) {
        this.facturaCollection = facturaCollection;
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
        hash += (emp_codigo != null ? emp_codigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Empleado)) {
            return false;
        }
        Empleado other = (Empleado) object;
        if ((this.emp_codigo == null && other.emp_codigo != null) || (this.emp_codigo != null && !this.emp_codigo.equals(other.emp_codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.Empleado[ emp_codigo=" + emp_codigo + " ]";
    }
    
}
