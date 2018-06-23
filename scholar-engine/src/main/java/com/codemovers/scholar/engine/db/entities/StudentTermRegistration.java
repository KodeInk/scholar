/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codemovers.scholar.engine.db.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Manny
 */
@Entity
@Table(name = "student_term_registration")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "StudentTermRegistration.findAll", query = "SELECT s FROM StudentTermRegistration s")
    , @NamedQuery(name = "StudentTermRegistration.findById", query = "SELECT s FROM StudentTermRegistration s WHERE s.id = :id")
    , @NamedQuery(name = "StudentTermRegistration.findByDateRegistered", query = "SELECT s FROM StudentTermRegistration s WHERE s.dateRegistered = :dateRegistered")
    , @NamedQuery(name = "StudentTermRegistration.findByDateCreated", query = "SELECT s FROM StudentTermRegistration s WHERE s.dateCreated = :dateCreated")
    , @NamedQuery(name = "StudentTermRegistration.findByStatus", query = "SELECT s FROM StudentTermRegistration s WHERE s.status = :status")})
public class StudentTermRegistration implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "date_registered")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateRegistered;
    @Basic(optional = false)
    @NotNull
    @Column(name = "date_created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "status")
    private String status;
    @JoinColumn(name = "class_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Classes registrationClass;
    @JoinColumn(name = "stream_id", referencedColumnName = "id")
    @ManyToOne
    private Streams registrationStream;
    @JoinColumn(name = "admission_id", referencedColumnName = "id")
    @ManyToOne(optional = false,fetch = FetchType.LAZY)
    private StudentAdmission studentAdmission;
    @JoinColumn(name = "term_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Terms registrationTerm;
    @JoinColumn(name = "author_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Users author;

    public StudentTermRegistration() {
    }

    public StudentTermRegistration(Long id) {
        this.id = id;
    }

    public StudentTermRegistration(Long id, Date dateRegistered, Date dateCreated, String status) {
        this.id = id;
        this.dateRegistered = dateRegistered;
        this.dateCreated = dateCreated;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDateRegistered() {
        return dateRegistered;
    }

    public void setDateRegistered(Date dateRegistered) {
        this.dateRegistered = dateRegistered;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Classes getRegistrationClass() {
        return registrationClass;
    }

    public void setRegistrationClass(Classes registrationClass) {
        this.registrationClass = registrationClass;
    }

    public Streams getRegistrationStream() {
        return registrationStream;
    }

    public void setRegistrationStream(Streams registrationStream) {
        this.registrationStream = registrationStream;
    }

    public StudentAdmission getStudentAdmission() {
        return studentAdmission;
    }

    public void setStudentAdmission(StudentAdmission studentAdmission) {
        this.studentAdmission = studentAdmission;
    }

    public Terms getRegistrationTerm() {
        return registrationTerm;
    }

    public void setRegistrationTerm(Terms registrationTerm) {
        this.registrationTerm = registrationTerm;
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
        if (!(object instanceof StudentTermRegistration)) {
            return false;
        }
        StudentTermRegistration other = (StudentTermRegistration) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.codemovers.scholar.engine.db.entities.StudentTermRegistration[ id=" + id + " ]";
    }
    
}
