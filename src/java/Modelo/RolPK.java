/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Damian
 */
@Embeddable
public class RolPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "XEPER_CODIGO")
    private int xeperCodigo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "XEOPC_CODIGO")
    private int xeopcCodigo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "XESIS_CODIGO")
    private int xesisCodigo;

    public RolPK() {
    }

    public RolPK(int xeperCodigo, int xeopcCodigo, int xesisCodigo) {
        this.xeperCodigo = xeperCodigo;
        this.xeopcCodigo = xeopcCodigo;
        this.xesisCodigo = xesisCodigo;
    }

    public int getXeperCodigo() {
        return xeperCodigo;
    }

    public void setXeperCodigo(int xeperCodigo) {
        this.xeperCodigo = xeperCodigo;
    }

    public int getXeopcCodigo() {
        return xeopcCodigo;
    }

    public void setXeopcCodigo(int xeopcCodigo) {
        this.xeopcCodigo = xeopcCodigo;
    }

    public int getXesisCodigo() {
        return xesisCodigo;
    }

    public void setXesisCodigo(int xesisCodigo) {
        this.xesisCodigo = xesisCodigo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) xeperCodigo;
        hash += (int) xeopcCodigo;
        hash += (int) xesisCodigo;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RolPK)) {
            return false;
        }
        RolPK other = (RolPK) object;
        if (this.xeperCodigo != other.xeperCodigo) {
            return false;
        }
        if (this.xeopcCodigo != other.xeopcCodigo) {
            return false;
        }
        if (this.xesisCodigo != other.xesisCodigo) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.RolPK[ xeperCodigo=" + xeperCodigo + ", xeopcCodigo=" + xeopcCodigo + ", xesisCodigo=" + xesisCodigo + " ]";
    }
    
}
