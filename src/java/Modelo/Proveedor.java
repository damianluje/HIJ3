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
@Table(name = "sepro_proveedor")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Proveedor.findAll", query = "SELECT p FROM Proveedor p"),
    @NamedQuery(name = "Proveedor.findBySeproCodigo", query = "SELECT p FROM Proveedor p WHERE p.seproCodigo = :seproCodigo"),
    @NamedQuery(name = "Proveedor.findBySeproNombre", query = "SELECT p FROM Proveedor p WHERE p.seproNombre = :seproNombre"),
    @NamedQuery(name = "Proveedor.findBySeproPorcentajeDeducible", query = "SELECT p FROM Proveedor p WHERE p.seproPorcentajeDeducible = :seproPorcentajeDeducible"),
    @NamedQuery(name = "Proveedor.findBySeproAreaGeografica", query = "SELECT p FROM Proveedor p WHERE p.seproAreaGeografica = :seproAreaGeografica")})
public class Proveedor implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "SEPRO_CODIGO")
    private Integer seproCodigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "SEPRO_NOMBRE")
    private String seproNombre;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "SEPRO_PORCENTAJE_DEDUCIBLE")
    private Float seproPorcentajeDeducible;
    @Size(max = 100)
    @Column(name = "SEPRO_AREA_GEOGRAFICA")
    private String seproAreaGeografica;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "seproCodigo")
    private Collection<TipoServicio> tipoServicioCollection;

    public Proveedor() {
    }

    public Proveedor(Integer seproCodigo) {
        this.seproCodigo = seproCodigo;
    }

    public Proveedor(Integer seproCodigo, String seproNombre) {
        this.seproCodigo = seproCodigo;
        this.seproNombre = seproNombre;
    }

    public Integer getSeproCodigo() {
        return seproCodigo;
    }

    public void setSeproCodigo(Integer seproCodigo) {
        this.seproCodigo = seproCodigo;
    }

    public String getSeproNombre() {
        return seproNombre;
    }

    public void setSeproNombre(String seproNombre) {
        this.seproNombre = seproNombre;
    }

    public Float getSeproPorcentajeDeducible() {
        return seproPorcentajeDeducible;
    }

    public void setSeproPorcentajeDeducible(Float seproPorcentajeDeducible) {
        this.seproPorcentajeDeducible = seproPorcentajeDeducible;
    }

    public String getSeproAreaGeografica() {
        return seproAreaGeografica;
    }

    public void setSeproAreaGeografica(String seproAreaGeografica) {
        this.seproAreaGeografica = seproAreaGeografica;
    }

    @XmlTransient
    public Collection<TipoServicio> getTipoServicioCollection() {
        return tipoServicioCollection;
    }

    public void setTipoServicioCollection(Collection<TipoServicio> tipoServicioCollection) {
        this.tipoServicioCollection = tipoServicioCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (seproCodigo != null ? seproCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Proveedor)) {
            return false;
        }
        Proveedor other = (Proveedor) object;
        if ((this.seproCodigo == null && other.seproCodigo != null) || (this.seproCodigo != null && !this.seproCodigo.equals(other.seproCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.Proveedor[ seproCodigo=" + seproCodigo + " ]";
    }
    
}
