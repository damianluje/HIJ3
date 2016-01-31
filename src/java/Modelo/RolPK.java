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
    private int per_codigo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "XEOPC_CODIGO")
    private int opc_codigo;

    public RolPK() {
    }

    public RolPK(int per_codigo, int opc_codigo) {
        this.per_codigo = per_codigo;
        this.opc_codigo = opc_codigo;
    }

    public int getPerCodigo() {
        return per_codigo;
    }

    public void setPerCodigo(int per_codigo) {
        this.per_codigo = per_codigo;
    }

    public int getOpcCodigo() {
        return opc_codigo;
    }

    public void setOpcCodigo(int opc_codigo) {
        this.opc_codigo = opc_codigo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) per_codigo;
        hash += (int) opc_codigo;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RolPK)) {
            return false;
        }
        RolPK other = (RolPK) object;
        if (this.per_codigo != other.per_codigo) {
            return false;
        }
        if (this.opc_codigo != other.opc_codigo) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.RolPK[ per_codigo=" + per_codigo + ", opc_codigo=" + opc_codigo + " ]";
    }
    
}
