/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codemovers.scholar.engine.db.entities;

import java.io.Serializable;
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
 * @author mover
 */
@Entity
@Table(name = "staff")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Staff.findAll", query = "SELECT s FROM Staff s")
    , @NamedQuery(name = "Staff.findById", query = "SELECT s FROM Staff s WHERE s.id = :id")
    , @NamedQuery(name = "Staff.findByProfile", query = "SELECT s FROM Staff s WHERE s.profile.id = :profileId")
    , @NamedQuery(name = "Staff.findByIsTeacher", query = "SELECT s FROM Staff s WHERE s.isTeacher = :isTeacher")
    , @NamedQuery(name = "Staff.findByJoinDate", query = "SELECT s FROM Staff s WHERE s.joinDate = :joinDate")
    , @NamedQuery(name = "Staff.findByStatus", query = "SELECT s FROM Staff s WHERE s.status = :status")
    , @NamedQuery(name = "Staff.findByDateCreated", query = "SELECT s FROM Staff s WHERE s.dateCreated = :dateCreated")})
public class Staff implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @JoinColumn(name = "profile_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Profile profile;
    @Basic(optional = false)
    @NotNull
    @Column(name = "isTeacher")
    private boolean isTeacher;
    @Basic(optional = false)
    @NotNull
    @Column(name = "join_date")
    @Temporal(TemporalType.DATE)
    private Date joinDate;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "status")
    private String status;
    @Basic(optional = false)
    @NotNull
    @Column(name = "date_created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "staffId")
    private Collection<StaffDepartment> staffDepartmentCollection;
    @JoinColumn(name = "author_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Users author;

    public Staff() {
    }

    public Staff(Long id) {
        this.id = id;
    }

    public Staff(Long id, Profile profile, boolean isTeacher, Date joinDate, String status, Date dateCreated) {
        this.id = id;
        this.profile = profile;
        this.isTeacher = isTeacher;
        this.joinDate = joinDate;
        this.status = status;
        this.dateCreated = dateCreated;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public boolean getIsTeacher() {
        return isTeacher;
    }

    public void setIsTeacher(boolean isTeacher) {
        this.isTeacher = isTeacher;
    }

    public Date getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(Date joinDate) {
        this.joinDate = joinDate;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Staff)) {
            return false;
        }
        Staff other = (Staff) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.codemovers.scholar.engine.db.entities.Staff[ id=" + id + " ]";
    }

}
