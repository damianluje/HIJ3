/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
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
@Table(name = "sepac_paciente")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Paciente.findAll", query = "SELECT p FROM Paciente p"),
    @NamedQuery(name = "Paciente.findBySepacCodigo", query = "SELECT p FROM Paciente p WHERE p.sepacCodigo = :sepacCodigo"),
    @NamedQuery(name = "Paciente.findBySepacNombre", query = "SELECT p FROM Paciente p WHERE p.sepacNombre = :sepacNombre"),
    @NamedQuery(name = "Paciente.findBySepacAreaGeografica", query = "SELECT p FROM Paciente p WHERE p.sepacAreaGeografica = :sepacAreaGeografica"),
    @NamedQuery(name = "Paciente.findBySepacSeguro", query = "SELECT p FROM Paciente p WHERE p.sepacSeguro = :sepacSeguro")})
public class Paciente implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "SEPAC_CODIGO")
    private Integer sepacCodigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "SEPAC_NOMBRE")
    private String sepacNombre;
    @Size(max = 100)
    @Column(name = "SEPAC_AREA_GEOGRAFICA")
    private String sepacAreaGeografica;
    @Basic(optional = false)
    @NotNull
    @Column(name = "SEPAC_SEGURO")
    private int sepacSeguro;
    @OneToMany(mappedBy = "sepacCodigo")
    private Collection<Factura> facturaCollection;

    public Paciente() {
    }

    public Paciente(Integer sepacCodigo) {
        this.sepacCodigo = sepacCodigo;
    }

    public Paciente(Integer sepacCodigo, String sepacNombre, int sepacSeguro) {
        this.sepacCodigo = sepacCodigo;
        this.sepacNombre = sepacNombre;
        this.sepacSeguro = sepacSeguro;
    }

    public Integer getSepacCodigo() {
        return sepacCodigo;
    }

    public void setSepacCodigo(Integer sepacCodigo) {
        this.sepacCodigo = sepacCodigo;
    }

    public String getSepacNombre() {
        return sepacNombre;
    }

    public void setSepacNombre(String sepacNombre) {
        this.sepacNombre = sepacNombre;
    }

    public String getSepacAreaGeografica() {
        return sepacAreaGeografica;
    }

    public void setSepacAreaGeografica(String sepacAreaGeografica) {
        this.sepacAreaGeografica = sepacAreaGeografica;
    }

    public int getSepacSeguro() {
        return sepacSeguro;
    }

    public void setSepacSeguro(int sepacSeguro) {
        this.sepacSeguro = sepacSeguro;
    }

    @XmlTransient
    public Collection<Factura> getFacturaCollection() {
        return facturaCollection;
    }

    public void setFacturaCollection(Collection<Factura> facturaCollection) {
        this.facturaCollection = facturaCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (sepacCodigo != null ? sepacCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Paciente)) {
            return false;
        }
        Paciente other = (Paciente) object;
        if ((this.sepacCodigo == null && other.sepacCodigo != null) || (this.sepacCodigo != null && !this.sepacCodigo.equals(other.sepacCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.Paciente[ sepacCodigo=" + sepacCodigo + " ]";
    }
    
}
