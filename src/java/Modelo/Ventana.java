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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Damian
 */
@Entity
@Table(name = "xeven_ventana")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ventana.findAll", query = "SELECT v FROM Ventana v"),
    @NamedQuery(name = "Ventana.findByVenCodigo", query = "SELECT v FROM Ventana v WHERE v.ven_codigo = :ven_codigo"),
    @NamedQuery(name = "Ventana.findByVenPagina", query = "SELECT v FROM Ventana v WHERE v.ven_pagina = :ven_pagina")})
public class Ventana implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "XEVEN_CODIGO")
    private Integer ven_codigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "XEVEN_PAGINA")
    private String ven_pagina;
    @JoinColumn(name = "XEOPC_CODIGO", referencedColumnName = "XEOPC_CODIGO")
    @ManyToOne
    private Opciones opc_codigo;

    public Ventana() {
    }

    public Ventana(Integer ven_codigo) {
        this.ven_codigo = ven_codigo;
    }

    public Ventana(Integer ven_codigo, String ven_pagina) {
        this.ven_codigo = ven_codigo;
        this.ven_pagina = ven_pagina;
    }

    public Integer getVenCodigo() {
        return ven_codigo;
    }

    public void setVenCodigo(Integer ven_codigo) {
        this.ven_codigo = ven_codigo;
    }

    public String getVenPagina() {
        return ven_pagina;
    }

    public void setVenPagina(String ven_pagina) {
        this.ven_pagina = ven_pagina;
    }

    public Opciones getOpcCodigo() {
        return opc_codigo;
    }

    public void setOpcCodigo(Opciones opc_codigo) {
        this.opc_codigo = opc_codigo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ven_codigo != null ? ven_codigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ventana)) {
            return false;
        }
        Ventana other = (Ventana) object;
        if ((this.ven_codigo == null && other.ven_codigo != null) || (this.ven_codigo != null && !this.ven_codigo.equals(other.ven_codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.Ventana[ ven_codigo=" + ven_codigo + " ]";
    }
    
}
