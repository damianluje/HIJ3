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
    @NamedQuery(name = "Rol.findByXeperCodigo", query = "SELECT r FROM Rol r WHERE r.rolPK.per_codigo = :per_codigo"),
    @NamedQuery(name = "Rol.findByXeopcCodigo", query = "SELECT r FROM Rol r WHERE r.rolPK.opc_codigo = :opc_codigo"),
    @NamedQuery(name = "Rol.findByRolInsert", query = "SELECT r FROM Rol r WHERE r.lIn_sert = :lIn_sert"),
    @NamedQuery(name = "Rol.findByRolUpdate", query = "SELECT r FROM Rol r WHERE r.lUp_date = :lUp_date"),
    @NamedQuery(name = "Rol.findByRolDelete", query = "SELECT r FROM Rol r WHERE r.lDe_lete = :lDe_lete"),
    @NamedQuery(name = "Rol.findByRolSelect", query = "SELECT r FROM Rol r WHERE r.lSe_lect = :lSe_lect")})
public class Rol implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected RolPK rolPK;
    @Column(name = "ROL_INSERT")
    private Boolean lIn_sert;
    @Column(name = "ROL_UPDATE")
    private Boolean lUp_date;
    @Column(name = "ROL_DELETE")
    private Boolean lDe_lete;
    @Column(name = "ROL_SELECT")
    private Boolean lSe_lect;
    @JoinColumns({
        @JoinColumn(name = "XEPER_CODIGO", referencedColumnName = "XEPER_CODIGO", insertable = false, updatable = false),
        @JoinColumn(name = "XEOPC_CODIGO", referencedColumnName = "XEOPC_CODIGO", insertable = false, updatable = false)})
    @OneToOne(optional = false)
    private OpcionesPerfil opcionesPerfil;

    public Rol() {
    }

    public Rol(RolPK rolPK) {
        this.rolPK = rolPK;
    }

    public Rol(int per_codigo, int opc_codigo) {
        this.rolPK = new RolPK(per_codigo, opc_codigo);
    }

    public RolPK getRolPK() {
        return rolPK;
    }

    public void setRolPK(RolPK rolPK) {
        this.rolPK = rolPK;
    }

    public Boolean getLInsert() {
        return lIn_sert;
    }

    public void setLInsert(Boolean lIn_sert) {
        this.lIn_sert = lIn_sert;
    }

    public Boolean getLUpdate() {
        return lUp_date;
    }

    public void setLUpdate(Boolean lUp_date) {
        this.lUp_date = lUp_date;
    }

    public Boolean getLDelete() {
        return lDe_lete;
    }

    public void setLDelete(Boolean lDe_lete) {
        this.lDe_lete = lDe_lete;
    }

    public Boolean getLSelect() {
        return lSe_lect;
    }

    public void setLSelect(Boolean lSe_lect) {
        this.lSe_lect = lSe_lect;
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
        return "Rol{" + "rolPK=" + rolPK + ", lIn_sert=" + lIn_sert + ", lUp_date=" + lUp_date + ", lDe_lete=" + lDe_lete + ", lSe_lect=" + lSe_lect + '}';
    }
    
}
