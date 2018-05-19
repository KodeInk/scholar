/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codemovers.scholar.engine.db.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author mover
 */
@Entity
@Table(name = "permissions")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Permissions.findAll", query = "SELECT p FROM Permissions p")
    , @NamedQuery(name = "Permissions.findById", query = "SELECT p FROM Permissions p WHERE p.id = :id")
    , @NamedQuery(name = "Permissions.findByName", query = "SELECT p FROM Permissions p WHERE p.name = :name")
    , @NamedQuery(name = "Permissions.findByCode", query = "SELECT p FROM Permissions p WHERE p.code = :code")})
public class Permissions implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Size(max = 255)
    @Column(name = "name")
    private String name;
    @Size(max = 255)
    @Column(name = "code")
    private String code;
    @Size(max = 255)
    @Column(name = "category")
    private String category;
    @OneToMany(mappedBy = "permission", fetch = FetchType.LAZY)
    private Collection<RolePermission> rolePermissionCollection;

    public Permissions() {
    }

    public Permissions(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
    

    @XmlTransient
    public Collection<RolePermission> getRolePermissionCollection() {
        return rolePermissionCollection;
    }

    public void setRolePermissionCollection(Collection<RolePermission> rolePermissionCollection) {
        this.rolePermissionCollection = rolePermissionCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Permissions)) {
            return false;
        }
        Permissions other = (Permissions) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "com.codemovers.scholar.engine.db.entities.Permissions[ id=" + id + " ]";
    }

}
