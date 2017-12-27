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
@Table(name = "streams")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Streams.findAll", query = "SELECT s FROM Streams s")
    , @NamedQuery(name = "Streams.findById", query = "SELECT s FROM Streams s WHERE s.id = :id")
    , @NamedQuery(name = "Streams.findByName", query = "SELECT s FROM Streams s WHERE s.name = :name")
    , @NamedQuery(name = "Streams.findByCode", query = "SELECT s FROM Streams s WHERE s.code = :code")
    , @NamedQuery(name = "Streams.findByStatus", query = "SELECT s FROM Streams s WHERE s.status = :status")
    , @NamedQuery(name = "Streams.findByDateCreated", query = "SELECT s FROM Streams s WHERE s.dateCreated = :dateCreated")})
public class Streams implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "code")
    private String code;
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
    @OneToMany(mappedBy = "streamId")
    private Collection<ClassStream> classStreamCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "streamId")
    private Collection<StudentAdmission> studentAdmissionCollection;
    @JoinColumn(name = "author_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Users author;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "streamId")
    private Collection<StudentTermRegistration> studentTermRegistrationCollection;

    public Streams() {
    }

    public Streams(Long id) {
        this.id = id;
    }

    public Streams(Long id, String name, String code, String status, Date dateCreated) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.status = status;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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
    public Collection<ClassStream> getClassStreamCollection() {
        return classStreamCollection;
    }

    public void setClassStreamCollection(Collection<ClassStream> classStreamCollection) {
        this.classStreamCollection = classStreamCollection;
    }

    @XmlTransient
    public Collection<StudentAdmission> getStudentAdmissionCollection() {
        return studentAdmissionCollection;
    }

    public void setStudentAdmissionCollection(Collection<StudentAdmission> studentAdmissionCollection) {
        this.studentAdmissionCollection = studentAdmissionCollection;
    }

    public Users getAuthor() {
        return author;
    }

    public void setAuthor(Users author) {
        this.author = author;
    }

    @XmlTransient
    public Collection<StudentTermRegistration> getStudentTermRegistrationCollection() {
        return studentTermRegistrationCollection;
    }

    public void setStudentTermRegistrationCollection(Collection<StudentTermRegistration> studentTermRegistrationCollection) {
        this.studentTermRegistrationCollection = studentTermRegistrationCollection;
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
        if (!(object instanceof Streams)) {
            return false;
        }
        Streams other = (Streams) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.codemovers.scholar.engine.db.entities.Streams[ id=" + id + " ]";
    }
    
}
