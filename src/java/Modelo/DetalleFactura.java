/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.io.Serializable;
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
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Damian
 */
@Entity
@Table(name = "sedfa_detalle_factura")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DetalleFactura.findAll", query = "SELECT d FROM DetalleFactura d"),
    @NamedQuery(name = "DetalleFactura.findBySedfaCodigo", query = "SELECT d FROM DetalleFactura d WHERE d.sedfaCodigo = :sedfaCodigo"),
    @NamedQuery(name = "DetalleFactura.findBySedfaCantidad", query = "SELECT d FROM DetalleFactura d WHERE d.sedfaCantidad = :sedfaCantidad")})
public class DetalleFactura implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "SEDFA_CODIGO")
    private Integer sedfaCodigo;
    @Column(name = "SEDFA_CANTIDAD")
    private Integer sedfaCantidad;
    @JoinColumn(name = "SETSV_CODIGO", referencedColumnName = "SETSV_CODIGO")
    @ManyToOne
    private TipoServicio setsvCodigo;
    @JoinColumn(name = "SEFAC_CODIGO", referencedColumnName = "SEFAC_CODIGO")
    @ManyToOne
    private Factura sefacCodigo;

    public DetalleFactura() {
    }

    public DetalleFactura(Integer sedfaCodigo) {
        this.sedfaCodigo = sedfaCodigo;
    }

    public Integer getSedfaCodigo() {
        return sedfaCodigo;
    }

    public void setSedfaCodigo(Integer sedfaCodigo) {
        this.sedfaCodigo = sedfaCodigo;
    }

    public Integer getSedfaCantidad() {
        return sedfaCantidad;
    }

    public void setSedfaCantidad(Integer sedfaCantidad) {
        this.sedfaCantidad = sedfaCantidad;
    }

    public TipoServicio getSetsvCodigo() {
        return setsvCodigo;
    }

    public void setSetsvCodigo(TipoServicio setsvCodigo) {
        this.setsvCodigo = setsvCodigo;
    }

    public Factura getSefacCodigo() {
        return sefacCodigo;
    }

    public void setSefacCodigo(Factura sefacCodigo) {
        this.sefacCodigo = sefacCodigo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (sedfaCodigo != null ? sedfaCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetalleFactura)) {
            return false;
        }
        DetalleFactura other = (DetalleFactura) object;
        if ((this.sedfaCodigo == null && other.sedfaCodigo != null) || (this.sedfaCodigo != null && !this.sedfaCodigo.equals(other.sedfaCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.DetalleFactura[ sedfaCodigo=" + sedfaCodigo + " ]";
    }
    
}
