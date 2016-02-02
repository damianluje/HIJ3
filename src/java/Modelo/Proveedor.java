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
@Table(name = "sepro_proveedor")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Proveedor.findAll", query = "SELECT p FROM Proveedor p"),
    @NamedQuery(name = "Proveedor.findBySeproCodigo", query = "SELECT p FROM Proveedor p WHERE p.pro_codigo = :pro_codigo"),
    @NamedQuery(name = "Proveedor.findBySeproNombre", query = "SELECT p FROM Proveedor p WHERE p.pro_nombre = :pro_nombre"),
    @NamedQuery(name = "Proveedor.findBySeproPorcentajeDeducible", query = "SELECT p FROM Proveedor p WHERE p.pro_porcentajededucible = :pro_porcentajededucible"),
    @NamedQuery(name = "Proveedor.findBySeproAreaGeografica", query = "SELECT p FROM Proveedor p WHERE p.pro_areageografica = :pro_areageografica")})
public class Proveedor implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "SEPRO_CODIGO")
    private Integer pro_codigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "SEPRO_NOMBRE")
    private String pro_nombre;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "SEPRO_PORCENTAJE_DEDUCIBLE")
    private Float pro_porcentajededucible;
    @Size(max = 100)
    @Column(name = "SEPRO_AREA_GEOGRAFICA")
    private String pro_areageografica;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pro_codigo")
    private List<TipoServicio> tipoServicioList;

    public Proveedor() {
    }

    public Proveedor(Integer pro_codigo) {
        this.pro_codigo = pro_codigo;
    }

    public Proveedor(Integer pro_codigo, String pro_nombre) {
        this.pro_codigo = pro_codigo;
        this.pro_nombre = pro_nombre;
    }

    public Integer getProCodigo() {
        return pro_codigo;
    }

    public void setProCodigo(Integer pro_codigo) {
        this.pro_codigo = pro_codigo;
    }

    public String getProNombre() {
        return pro_nombre;
    }

    public void setProNombre(String pro_nombre) {
        this.pro_nombre = pro_nombre;
    }

    public Float getProPorcentajeDeducible() {
        return pro_porcentajededucible;
    }

    public void setProPorcentajeDeducible(Float pro_porcentajededucible) {
        this.pro_porcentajededucible = pro_porcentajededucible;
    }

    public String getProAreaGeografica() {
        return pro_areageografica;
    }

    public void setProAreaGeografica(String pro_areageografica) {
        this.pro_areageografica = pro_areageografica;
    }

    @XmlTransient
    public List<TipoServicio> getTipoServicioList() {
        return tipoServicioList;
    }

    public void setTipoServicioList(List<TipoServicio> tipoServicioList) {
        this.tipoServicioList = tipoServicioList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pro_codigo != null ? pro_codigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Proveedor)) {
            return false;
        }
        Proveedor other = (Proveedor) object;
        if ((this.pro_codigo == null && other.pro_codigo != null) || (this.pro_codigo != null && !this.pro_codigo.equals(other.pro_codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.Proveedor[ pro_codigo=" + pro_codigo + " ]";
    }
    
}
