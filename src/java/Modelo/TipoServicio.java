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
    @NamedQuery(name = "TipoServicio.findBySetsvCodigo", query = "SELECT t FROM TipoServicio t WHERE t.tsvCodigo = :tsvCodigo"),
    @NamedQuery(name = "TipoServicio.findBySetsvNombre", query = "SELECT t FROM TipoServicio t WHERE t.setNombre = :setNombre"),
    @NamedQuery(name = "TipoServicio.findBySetsvCosto", query = "SELECT t FROM TipoServicio t WHERE t.setCosto = :setCosto"),
    @NamedQuery(name = "TipoServicio.findBySetsvDescripcion", query = "SELECT t FROM TipoServicio t WHERE t.setDescripcion = :setDescripcion")})
public class TipoServicio implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "SETSV_CODIGO")
    private Integer tsvCodigo;
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
    @OneToMany(mappedBy = "tsvCodigo")
    private List<DetalleFactura> detalleFacturaList;

    public TipoServicio() {
    }

    public TipoServicio(Integer tsvCodigo) {
        this.tsvCodigo = tsvCodigo;
    }

    public TipoServicio(Integer tsvCodigo, String setNombre, float setCosto) {
        this.tsvCodigo = tsvCodigo;
        this.setNombre = setNombre;
        this.setCosto = setCosto;
    }

    public Integer getTsvCodigo() {
        return tsvCodigo;
    }

    public void setTsvCodigo(Integer tsvCodigo) {
        this.tsvCodigo = tsvCodigo;
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
    public List<DetalleFactura> getDetalleFacturaList() {
        return detalleFacturaList;
    }

    public void setDetalleFacturaList(List<DetalleFactura> detalleFacturaList) {
        this.detalleFacturaList = detalleFacturaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tsvCodigo != null ? tsvCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoServicio)) {
            return false;
        }
        TipoServicio other = (TipoServicio) object;
        if ((this.tsvCodigo == null && other.tsvCodigo != null) || (this.tsvCodigo != null && !this.tsvCodigo.equals(other.tsvCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.TipoServicio[ tsvCodigo=" + tsvCodigo + " ]";
    }
    
}
