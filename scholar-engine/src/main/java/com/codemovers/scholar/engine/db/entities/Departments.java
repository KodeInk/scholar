/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codemovers.scholar.engine.db.entities;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author mover 3/15/2018
 */
@Entity
@Table(name = "departments")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Departments.findAll", query = "SELECT d FROM Departments d")
    , @NamedQuery(name = "Departments.findById", query = "SELECT d FROM Departments d WHERE d.id = :id")
    , @NamedQuery(name = "Departments.findByName", query = "SELECT d FROM Departments d WHERE d.name = :name")
    , @NamedQuery(name = "Departments.findByDescription", query = "SELECT d FROM Departments d WHERE d.description = :description")
    , @NamedQuery(name = "Departments.findByIsSystem", query = "SELECT d FROM Departments d WHERE d.isSystem = :isSystem")
    , @NamedQuery(name = "Departments.findByStatus", query = "SELECT d FROM Departments d WHERE d.status = :status")
    , @NamedQuery(name = "Departments.findByDateCreated", query = "SELECT d FROM Departments d WHERE d.dateCreated = :dateCreated")})
public class Departments implements Serializable {


    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Size(max = 255)
    @Column(name = "name")
    private String name;
    @Size(max = 500)
    @Column(name = "description")
    private String description;
    @Size(max = 255)
    @Column(name = "code")
    private String code;
    @OneToMany(mappedBy = "parent")
    private Collection<Departments> departmentsCollection;
    @JoinColumn(name = "parent_id", referencedColumnName = "id")
    @ManyToOne
    private Departments parent;

    @Column(name = "isSystem")
    private Boolean isSystem;
    @Size(max = 8)
    @Column(name = "status")
    private String status;
    @Basic(optional = false)
    @NotNull
    @Column(name = "date_created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "departmentId")
    private Collection<StaffDepartment> staffDepartmentCollection;
    @JoinColumn(name = "author_id", referencedColumnName = "id")
    @ManyToOne
    private Users author;

    public Departments() {
    }

    public Departments(Long id) {
        this.id = id;
    }

    public Departments(Long id, Date dateCreated) {
        this.id = id;
        this.dateCreated = dateCreated;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getIsSystem() {
        return isSystem;
    }

    public void setIsSystem(Boolean isSystem) {
        this.isSystem = isSystem;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    @XmlTransient
    public Collection<StaffDepartment> getStaffDepartmentCollection() {
        return staffDepartmentCollection;
    }

    public void setStaffDepartmentCollection(Collection<StaffDepartment> staffDepartmentCollection) {
        this.staffDepartmentCollection = staffDepartmentCollection;
    }

    public Users getAuthor() {
        return author;
    }

    public void setAuthor(Users author) {
        this.author = author;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @XmlTransient
    public Collection<Departments> getDepartmentsCollection() {
        return departmentsCollection;
    }

    public void setDepartmentsCollection(Collection<Departments> departmentsCollection) {
        this.departmentsCollection = departmentsCollection;
    }

    public Departments getParent() {
        return parent;
    }

    public void setParent(Departments parent) {
        this.parent = parent;
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
        if (!(object instanceof Departments)) {
            return false;
        }
        Departments other = (Departments) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.codemovers.scholar.engine.db.entities.Departments[ id=" + id + " ]";
    }


}
