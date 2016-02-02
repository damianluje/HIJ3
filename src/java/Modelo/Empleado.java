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
    @NamedQuery(name = "Empleado.findByPeempNombres", query = "SELECT e FROM Empleado e WHERE e.emp_nombres = :emp_nombres"),
    @NamedQuery(name = "Empleado.findByPeempApellidos", query = "SELECT e FROM Empleado e WHERE e.emp_apellidos = :emp_apellidos"),
    @NamedQuery(name = "Empleado.findByPeempCedula", query = "SELECT e FROM Empleado e WHERE e.emp_cedula = :emp_cedula"),
    @NamedQuery(name = "Empleado.findByPeempPasaporte", query = "SELECT e FROM Empleado e WHERE e.emp_pasaporte = :emp_pasaporte")})
public class Empleado implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "PEEMP_CODIGO")
    private Integer emp_codigo;
    @Size(max = 100)
    @Column(name = "PEEMP_NOMBRES")
    private String emp_nombres;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "PEEMP_APELLIDOS")
    private String emp_apellidos;
    @Size(max = 10)
    @Column(name = "PEEMP_CEDULA")
    private String emp_cedula;
    @Size(max = 10)
    @Column(name = "PEEMP_PASAPORTE")
    private String emp_pasaporte;
    @OneToMany(mappedBy = "emp_codigo")
    private List<Factura> facturaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "emp_codigo")
    private List<Usuario> usuarioList;

    public Empleado() {
    }

    public Empleado(Integer emp_codigo) {
        this.emp_codigo = emp_codigo;
    }

    public Empleado(Integer emp_codigo, String emp_apellidos) {
        this.emp_codigo = emp_codigo;
        this.emp_apellidos = emp_apellidos;
    }

    public Integer getEmpCodigo() {
        return emp_codigo;
    }

    public void setEmpCodigo(Integer emp_codigo) {
        this.emp_codigo = emp_codigo;
    }

    public String getEmpNombres() {
        return emp_nombres;
    }

    public void setEmpNombres(String emp_nombres) {
        this.emp_nombres = emp_nombres;
    }

    public String getEmpApellidos() {
        return emp_apellidos;
    }

    public void setEmpApellidos(String emp_apellidos) {
        this.emp_apellidos = emp_apellidos;
    }

    public String getEmpCedula() {
        return emp_cedula;
    }

    public void setEmpCedula(String emp_cedula) {
        this.emp_cedula = emp_cedula;
    }

    public String getEmpPasaporte() {
        return emp_pasaporte;
    }

    public void setEmpPasaporte(String emp_pasaporte) {
        this.emp_pasaporte = emp_pasaporte;
    }

    @XmlTransient
    public List<Factura> getFacturaList() {
        return facturaList;
    }

    public void setFacturaList(List<Factura> facturaList) {
        this.facturaList = facturaList;
    }

    @XmlTransient
    public List<Usuario> getUsuarioList() {
        return usuarioList;
    }

    public void setUsuarioList(List<Usuario> usuarioList) {
        this.usuarioList = usuarioList;
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
