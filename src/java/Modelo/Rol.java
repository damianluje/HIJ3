/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Damian
 */
@Entity
@Table(name = "rol")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Rol.findAll", query = "SELECT r FROM Rol r"),
    @NamedQuery(name = "Rol.findByXeperCodigo", query = "SELECT r FROM Rol r WHERE r.rolPK.xeperCodigo = :xeperCodigo"),
    @NamedQuery(name = "Rol.findByXeopcCodigo", query = "SELECT r FROM Rol r WHERE r.rolPK.xeopcCodigo = :xeopcCodigo"),
    @NamedQuery(name = "Rol.findByXesisCodigo", query = "SELECT r FROM Rol r WHERE r.rolPK.xesisCodigo = :xesisCodigo"),
    @NamedQuery(name = "Rol.findByRolInsert", query = "SELECT r FROM Rol r WHERE r.rolInsert = :rolInsert"),
    @NamedQuery(name = "Rol.findByRolUpdate", query = "SELECT r FROM Rol r WHERE r.rolUpdate = :rolUpdate"),
    @NamedQuery(name = "Rol.findByRolDelete", query = "SELECT r FROM Rol r WHERE r.rolDelete = :rolDelete"),
    @NamedQuery(name = "Rol.findByRolSelect", query = "SELECT r FROM Rol r WHERE r.rolSelect = :rolSelect")})
public class Rol implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected RolPK rolPK;
    @Column(name = "ROL_INSERT")
    private Character rolInsert;
    @Column(name = "ROL_UPDATE")
    private Character rolUpdate;
    @Column(name = "ROL_DELETE")
    private Character rolDelete;
    @Column(name = "ROL_SELECT")
    private Character rolSelect;
    @JoinColumns({
        @JoinColumn(name = "XEPER_CODIGO", referencedColumnName = "XEPER_CODIGO", insertable = false, updatable = false),
        @JoinColumn(name = "XEOPC_CODIGO", referencedColumnName = "XEOPC_CODIGO", insertable = false, updatable = false),
        @JoinColumn(name = "XESIS_CODIGO", referencedColumnName = "XESIS_CODIGO", insertable = false, updatable = false)})
    @OneToOne(optional = false)
    private OpcionesPerfil opcionesPerfil;

    public Rol() {
    }

    public Rol(RolPK rolPK) {
        this.rolPK = rolPK;
    }

    public Rol(int xeperCodigo, int xeopcCodigo, int xesisCodigo) {
        this.rolPK = new RolPK(xeperCodigo, xeopcCodigo, xesisCodigo);
    }

    public RolPK getRolPK() {
        return rolPK;
    }

    public void setRolPK(RolPK rolPK) {
        this.rolPK = rolPK;
    }

    public Character getRolInsert() {
        return rolInsert;
    }

    public void setRolInsert(Character rolInsert) {
        this.rolInsert = rolInsert;
    }

    public Character getRolUpdate() {
        return rolUpdate;
    }

    public void setRolUpdate(Character rolUpdate) {
        this.rolUpdate = rolUpdate;
    }

    public Character getRolDelete() {
        return rolDelete;
    }

    public void setRolDelete(Character rolDelete) {
        this.rolDelete = rolDelete;
    }

    public Character getRolSelect() {
        return rolSelect;
    }

    public void setRolSelect(Character rolSelect) {
        this.rolSelect = rolSelect;
    }

    public OpcionesPerfil getOpcionesPerfil() {
        return opcionesPerfil;
    }

    public void setOpcionesPerfil(OpcionesPerfil opcionesPerfil) {
        this.opcionesPerfil = opcionesPerfil;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (rolPK != null ? rolPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Rol)) {
            return false;
        }
        Rol other = (Rol) object;
        if ((this.rolPK == null && other.rolPK != null) || (this.rolPK != null && !this.rolPK.equals(other.rolPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.Rol[ rolPK=" + rolPK + " ]";
    }
    
}
