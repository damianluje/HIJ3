/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Damian
 */
@Entity
@Table(name = "sefac_factura")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Factura.findAll", query = "SELECT f FROM Factura f"),
    @NamedQuery(name = "Factura.findBySefacCodigo", query = "SELECT f FROM Factura f WHERE f.fac_codigo = :fac_codigo"),
    @NamedQuery(name = "Factura.findBySefacFecha", query = "SELECT f FROM Factura f WHERE f.sefacFecha = :sefacFecha"),
    @NamedQuery(name = "Factura.findBySefacDireccion", query = "SELECT f FROM Factura f WHERE f.sefacDireccion = :sefacDireccion"),
    @NamedQuery(name = "Factura.findBySefacSubtotal", query = "SELECT f FROM Factura f WHERE f.sefacSubtotal = :sefacSubtotal"),
    @NamedQuery(name = "Factura.findBySefacTotal", query = "SELECT f FROM Factura f WHERE f.sefacTotal = :sefacTotal"),
    @NamedQuery(name = "Factura.findBySefacIva", query = "SELECT f FROM Factura f WHERE f.sefacIva = :sefacIva"),
    @NamedQuery(name = "Factura.findBySefacFormaPago", query = "SELECT f FROM Factura f WHERE f.sefacFormaPago = :sefacFormaPago")})
public class Factura implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "SEFAC_CODIGO")
    private Integer fac_codigo;
    @Column(name = "SEFAC_FECHA")
    @Temporal(TemporalType.DATE)
    private Date sefacFecha;
    @Size(max = 100)
    @Column(name = "SEFAC_DIRECCION")
    private String sefacDireccion;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "SEFAC_SUBTOTAL")
    private Double sefacSubtotal;
    @Column(name = "SEFAC_TOTAL")
    private Double sefacTotal;
    @Column(name = "SEFAC_IVA")
    private Double sefacIva;
    @Size(max = 100)
    @Column(name = "SEFAC_FORMA_PAGO")
    private String sefacFormaPago;
    @JoinColumn(name = "SEPAC_CODIGO", referencedColumnName = "SEPAC_CODIGO")
    @ManyToOne
    private Paciente pac_codigo;
    @JoinColumn(name = "PEEMP_CODIGO", referencedColumnName = "PEEMP_CODIGO")
    @ManyToOne
    private Empleado emp_codigo;
    @OneToMany(mappedBy = "fac_codigo")
    private List<DetalleFactura> detalleFacturaList;

    public Factura() {
    }

    public Factura(Integer fac_codigo) {
        this.fac_codigo = fac_codigo;
    }

    public Integer getFacCodigo() {
        return fac_codigo;
    }

    public void setFacCodigo(Integer fac_codigo) {
        this.fac_codigo = fac_codigo;
    }

    public Date getSefacFecha() {
        return sefacFecha;
    }

    public void setSefacFecha(Date sefacFecha) {
        this.sefacFecha = sefacFecha;
    }

    public String getSefacDireccion() {
        return sefacDireccion;
    }

    public void setSefacDireccion(String sefacDireccion) {
        this.sefacDireccion = sefacDireccion;
    }

    public Double getSefacSubtotal() {
        return sefacSubtotal;
    }

    public void setSefacSubtotal(Double sefacSubtotal) {
        this.sefacSubtotal = sefacSubtotal;
    }

    public Double getSefacTotal() {
        return sefacTotal;
    }

    public void setSefacTotal(Double sefacTotal) {
        this.sefacTotal = sefacTotal;
    }

    public Double getSefacIva() {
        return sefacIva;
    }

    public void setSefacIva(Double sefacIva) {
        this.sefacIva = sefacIva;
    }

    public String getSefacFormaPago() {
        return sefacFormaPago;
    }

    public void setSefacFormaPago(String sefacFormaPago) {
        this.sefacFormaPago = sefacFormaPago;
    }

    public Paciente getPacCodigo() {
        return pac_codigo;
    }

    public void setPacCodigo(Paciente pac_codigo) {
        this.pac_codigo = pac_codigo;
    }

    public Empleado getEmpCodigo() {
        return emp_codigo;
    }

    public void setEmpCodigo(Empleado emp_codigo) {
        this.emp_codigo = emp_codigo;
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
        hash += (fac_codigo != null ? fac_codigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Factura)) {
            return false;
        }
        Factura other = (Factura) object;
        if ((this.fac_codigo == null && other.fac_codigo != null) || (this.fac_codigo != null && !this.fac_codigo.equals(other.fac_codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.Factura[ fac_codigo=" + fac_codigo + " ]";
    }
    
}
