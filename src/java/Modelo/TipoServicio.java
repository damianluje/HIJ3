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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "setsv_tipo_servicio")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoServicio.findAll", query = "SELECT t FROM TipoServicio t"),
    @NamedQuery(name = "TipoServicio.findBySetsvCodigo", query = "SELECT t FROM TipoServicio t WHERE t.setCodigo = :setCodigo"),
    @NamedQuery(name = "TipoServicio.findBySetsvNombre", query = "SELECT t FROM TipoServicio t WHERE t.setNombre = :setNombre"),
    @NamedQuery(name = "TipoServicio.findBySetsvCosto", query = "SELECT t FROM TipoServicio t WHERE t.setCosto = :setCosto"),
    @NamedQuery(name = "TipoServicio.findBySetsvDescripcion", query = "SELECT t FROM TipoServicio t WHERE t.setDescripcion = :setDescripcion")})
public class TipoServicio implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "SETSV_CODIGO")
    private Integer setCodigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "SETSV_NOMBRE")
    private String setNombre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "SETSV_COSTO")
    private float setCosto;
    @Size(max = 100)
    @Column(name = "SETSV_DESCRIPCION")
    private String setDescripcion;
    @JoinColumn(name = "SEPRO_CODIGO", referencedColumnName = "SEPRO_CODIGO")
    @ManyToOne(optional = false)
    private Proveedor pro_codigo;
    @OneToMany(mappedBy = "setCodigo")
    private Collection<DetalleFactura> detalleFacturaCollection;

    public TipoServicio() {
    }

    public TipoServicio(Integer setCodigo) {
        this.setCodigo = setCodigo;
    }

    public TipoServicio(Integer setCodigo, String setNombre, float setCosto) {
        this.setCodigo = setCodigo;
        this.setNombre = setNombre;
        this.setCosto = setCosto;
    }

    public Integer getTsvCodigo() {
        return setCodigo;
    }

    public void setTsvCodigo(Integer setCodigo) {
        this.setCodigo = setCodigo;
    }

    public String getTsvNombre() {
        return setNombre;
    }

    public void setTsvNombre(String setNombre) {
        this.setNombre = setNombre;
    }

    public float getTsvCosto() {
        return setCosto;
    }

    public void setTsvCosto(float setCosto) {
        this.setCosto = setCosto;
    }

    public String getTsvDescripcion() {
        return setDescripcion;
    }

    public void setTsvDescripcion(String setDescripcion) {
        this.setDescripcion = setDescripcion;
    }

    public Proveedor getProCodigo() {
        return pro_codigo;
    }

    public void setProCodigo(Proveedor pro_codigo) {
        this.pro_codigo = pro_codigo;
    }

    @XmlTransient
    public Collection<DetalleFactura> getDetalleFacturaCollection() {
        return detalleFacturaCollection;
    }

    public void setDetalleFacturaCollection(Collection<DetalleFactura> detalleFacturaCollection) {
        this.detalleFacturaCollection = detalleFacturaCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (setCodigo != null ? setCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoServicio)) {
            return false;
        }
        TipoServicio other = (TipoServicio) object;
        if ((this.setCodigo == null && other.setCodigo != null) || (this.setCodigo != null && !this.setCodigo.equals(other.setCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.TipoServicio[ setCodigo=" + setCodigo + " ]";
    }
    
}
