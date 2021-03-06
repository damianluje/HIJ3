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
    @NamedQuery(name = "DetalleFactura.findBySedfaCodigo", query = "SELECT d FROM DetalleFactura d WHERE d.dfa_codigo = :dfa_codigo"),
    @NamedQuery(name = "DetalleFactura.findBySedfaCantidad", query = "SELECT d FROM DetalleFactura d WHERE d.dfa_cantidad = :dfa_cantidad")})
public class DetalleFactura implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "SEDFA_CODIGO")
    private Integer dfa_codigo;
    @Column(name = "SEDFA_CANTIDAD")
    private Integer dfa_cantidad;
    @JoinColumn(name = "SETSV_CODIGO", referencedColumnName = "SETSV_CODIGO")
    @ManyToOne
    private TipoServicio tsvCodigo;
    @JoinColumn(name = "SEFAC_CODIGO", referencedColumnName = "SEFAC_CODIGO")
    @ManyToOne
    private Factura fac_codigo;

    public DetalleFactura() {
    }

    public DetalleFactura(Integer dfa_codigo) {
        this.dfa_codigo = dfa_codigo;
    }

    public Integer getDfaCodigo() {
        return dfa_codigo;
    }

    public void setDfaCodigo(Integer dfa_codigo) {
        this.dfa_codigo = dfa_codigo;
    }

    public Integer getDfaCantidad() {
        return dfa_cantidad;
    }

    public void setDfaCantidad(Integer dfa_cantidad) {
        this.dfa_cantidad = dfa_cantidad;
    }

    public TipoServicio getTsvCodigo() {
        return tsvCodigo;
    }

    public void setTsvCodigo(TipoServicio tsvCodigo) {
        this.tsvCodigo = tsvCodigo;
    }

    public Factura getFacCodigo() {
        return fac_codigo;
    }

    public void setFacCodigo(Factura fac_codigo) {
        this.fac_codigo = fac_codigo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dfa_codigo != null ? dfa_codigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetalleFactura)) {
            return false;
        }
        DetalleFactura other = (DetalleFactura) object;
        if ((this.dfa_codigo == null && other.dfa_codigo != null) || (this.dfa_codigo != null && !this.dfa_codigo.equals(other.dfa_codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.DetalleFactura[ dfa_codigo=" + dfa_codigo + " ]";
    }
    
}
