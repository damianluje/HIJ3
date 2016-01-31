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
@Table(name = "xeusu_usuario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u"),
    @NamedQuery(name = "Usuario.findByXeusuCodigo", query = "SELECT u FROM Usuario u WHERE u.usu_codigo = :usu_codigo"),
    @NamedQuery(name = "Usuario.findByXeusuId", query = "SELECT u FROM Usuario u WHERE u.usu_id = :usu_id"),
    @NamedQuery(name = "Usuario.findByXeusuPassword", query = "SELECT u FROM Usuario u WHERE u.usu_password = :usu_password")})
public class Usuario implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "XEUSU_CODIGO")
    private Integer usu_codigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "XEUSU_ID")
    private String usu_id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "XEUSU_PASSWORD")
    private String usu_password;
    @JoinColumn(name = "PEEMP_CODIGO", referencedColumnName = "PEEMP_CODIGO")
    @ManyToOne(optional = false)
    private Empleado emp_codigo;
    @JoinColumn(name = "XEPER_CODIGO", referencedColumnName = "XEPER_CODIGO")
    @ManyToOne
    private Perfil per_codigo;

    public Usuario() {
    }

    public Usuario(Integer usu_codigo) {
        this.usu_codigo = usu_codigo;
    }

    public Usuario(Integer usu_codigo, String usu_id, String usu_password) {
        this.usu_codigo = usu_codigo;
        this.usu_id = usu_id;
        this.usu_password = usu_password;
    }

    public Integer getUsuCodigo() {
        return usu_codigo;
    }

    public void setUsuCodigo(Integer usu_codigo) {
        this.usu_codigo = usu_codigo;
    }

    public String getUsuId() {
        return usu_id;
    }

    public void setUsuId(String usu_id) {
        this.usu_id = usu_id;
    }

    public String getUsuPassword() {
        return usu_password;
    }

    public void setUsuPassword(String usu_password) {
        this.usu_password = usu_password;
    }

    public Empleado getEmpCodigo() {
        return emp_codigo;
    }

    public void setEmpCodigo(Empleado emp_codigo) {
        this.emp_codigo = emp_codigo;
    }

    public Perfil getPerCodigo() {
        return per_codigo;
    }

    public void setPerCodigo(Perfil per_codigo) {
        this.per_codigo = per_codigo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (usu_codigo != null ? usu_codigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.usu_codigo == null && other.usu_codigo != null) || (this.usu_codigo != null && !this.usu_codigo.equals(other.usu_codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.Usuario[ usu_codigo=" + usu_codigo + " ]";
    }
    
}
