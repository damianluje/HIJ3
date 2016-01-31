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
    @NamedQuery(name = "TipoServicio.findBySetsvCodigo", query = "SELECT t FROM TipoServicio t WHERE t.setsvCodigo = :setsvCodigo"),
    @NamedQuery(name = "TipoServicio.findBySetsvNombre", query = "SELECT t FROM TipoServicio t WHERE t.setsvNombre = :setsvNombre"),
    @NamedQuery(name = "TipoServicio.findBySetsvCosto", query = "SELECT t FROM TipoServicio t WHERE t.setsvCosto = :setsvCosto"),
    @NamedQuery(name = "TipoServicio.findBySetsvDescripcion", query = "SELECT t FROM TipoServicio t WHERE t.setsvDescripcion = :setsvDescripcion")})
public class TipoServicio implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "SETSV_CODIGO")
    private Integer setsvCodigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "SETSV_NOMBRE")
    private String setsvNombre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "SETSV_COSTO")
    private float setsvCosto;
    @Size(max = 100)
    @Column(name = "SETSV_DESCRIPCION")
    private String setsvDescripcion;
    @JoinColumn(name = "SEPRO_CODIGO", referencedColumnName = "SEPRO_CODIGO")
    @ManyToOne(optional = false)
    private Proveedor seproCodigo;
    @OneToMany(mappedBy = "setsvCodigo")
    private Collection<DetalleFactura> detalleFacturaCollection;

    public TipoServicio() {
    }

    public TipoServicio(Integer setsvCodigo) {
        this.setsvCodigo = setsvCodigo;
    }

    public TipoServicio(Integer setsvCodigo, String setsvNombre, float setsvCosto) {
        this.setsvCodigo = setsvCodigo;
        this.setsvNombre = setsvNombre;
        this.setsvCosto = setsvCosto;
    }

    public Integer getSetsvCodigo() {
        return setsvCodigo;
    }

    public void setSetsvCodigo(Integer setsvCodigo) {
        this.setsvCodigo = setsvCodigo;
    }

    public String getSetsvNombre() {
        return setsvNombre;
    }

    public void setSetsvNombre(String setsvNombre) {
        this.setsvNombre = setsvNombre;
    }

    public float getSetsvCosto() {
        return setsvCosto;
    }

    public void setSetsvCosto(float setsvCosto) {
        this.setsvCosto = setsvCosto;
    }

    public String getSetsvDescripcion() {
        return setsvDescripcion;
    }

    public void setSetsvDescripcion(String setsvDescripcion) {
        this.setsvDescripcion = setsvDescripcion;
    }

    public Proveedor getSeproCodigo() {
        return seproCodigo;
    }

    public void setSeproCodigo(Proveedor seproCodigo) {
        this.seproCodigo = seproCodigo;
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
        hash += (setsvCodigo != null ? setsvCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoServicio)) {
            return false;
        }
        TipoServicio other = (TipoServicio) object;
        if ((this.setsvCodigo == null && other.setsvCodigo != null) || (this.setsvCodigo != null && !this.setsvCodigo.equals(other.setsvCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.TipoServicio[ setsvCodigo=" + setsvCodigo + " ]";
    }
    
}
