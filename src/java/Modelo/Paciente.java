/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.io.Serializable;
import java.util.List;
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
    @NamedQuery(name = "Paciente.findBySepacCodigo", query = "SELECT p FROM Paciente p WHERE p.pac_codigo = :pac_codigo"),
    @NamedQuery(name = "Paciente.findBySepacNombre", query = "SELECT p FROM Paciente p WHERE p.pac_nombre = :pac_nombre"),
    @NamedQuery(name = "Paciente.findBySepacAreaGeografica", query = "SELECT p FROM Paciente p WHERE p.pac_areageografica = :pac_areageografica"),
    @NamedQuery(name = "Paciente.findBySepacSeguro", query = "SELECT p FROM Paciente p WHERE p.pac_seguro = :pac_seguro")})
public class Paciente implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "SEPAC_CODIGO")
    private Integer pac_codigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "SEPAC_NOMBRE")
    private String pac_nombre;
    @Size(max = 100)
    @Column(name = "SEPAC_AREA_GEOGRAFICA")
    private String pac_areageografica;
    @Basic(optional = false)
    @NotNull
    @Column(name = "SEPAC_SEGURO")
    private int pac_seguro;
    @OneToMany(mappedBy = "pac_codigo")
    private List<Factura> facturaList;

    public Paciente() {
    }

    public Paciente(Integer pac_codigo) {
        this.pac_codigo = pac_codigo;
    }

    public Paciente(Integer pac_codigo, String pac_nombre, int pac_seguro) {
        this.pac_codigo = pac_codigo;
        this.pac_nombre = pac_nombre;
        this.pac_seguro = pac_seguro;
    }

    public Integer getPacCodigo() {
        return pac_codigo;
    }

    public void setPacCodigo(Integer pac_codigo) {
        this.pac_codigo = pac_codigo;
    }

    public String getPacNombre() {
        return pac_nombre;
    }

    public void setPacNombre(String pac_nombre) {
        this.pac_nombre = pac_nombre;
    }

    public String getPacAreaGeografica() {
        return pac_areageografica;
    }

    public void setPacAreaGeografica(String pac_areageografica) {
        this.pac_areageografica = pac_areageografica;
    }

    public int getPacSeguro() {
        return pac_seguro;
    }

    public void setPacSeguro(int pac_seguro) {
        this.pac_seguro = pac_seguro;
    }

    @XmlTransient
    public List<Factura> getFacturaList() {
        return facturaList;
    }

    public void setFacturaList(List<Factura> facturaList) {
        this.facturaList = facturaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pac_codigo != null ? pac_codigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Paciente)) {
            return false;
        }
        Paciente other = (Paciente) object;
        if ((this.pac_codigo == null && other.pac_codigo != null) || (this.pac_codigo != null && !this.pac_codigo.equals(other.pac_codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.Paciente[ pac_codigo=" + pac_codigo + " ]";
    }
    
}
