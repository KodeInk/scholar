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
@Table(name = "student_admission")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "StudentAdmission.findAll", query = "SELECT s FROM StudentAdmission s")
    , @NamedQuery(name = "StudentAdmission.findById", query = "SELECT s FROM StudentAdmission s WHERE s.id = :id")
    , @NamedQuery(name = "StudentAdmission.findByStudentId", query = "SELECT s FROM StudentAdmission s WHERE s.studentId = :studentId")
    , @NamedQuery(name = "StudentAdmission.findByAdmissionNo", query = "SELECT s FROM StudentAdmission s WHERE s.admissionNo = :admissionNo")
    , @NamedQuery(name = "StudentAdmission.findByExternalId", query = "SELECT s FROM StudentAdmission s WHERE s.externalId = :externalId")
    , @NamedQuery(name = "StudentAdmission.findByDateOfAdmission", query = "SELECT s FROM StudentAdmission s WHERE s.dateOfAdmission = :dateOfAdmission")
    , @NamedQuery(name = "StudentAdmission.findByStatus", query = "SELECT s FROM StudentAdmission s WHERE s.status = :status")
    , @NamedQuery(name = "StudentAdmission.findByDateCreated", query = "SELECT s FROM StudentAdmission s WHERE s.dateCreated = :dateCreated")})
public class StudentAdmission implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "student_id")
    private long studentId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "admission_no")
    private String admissionNo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "external_id")
    private String externalId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "date_of_admission")
    @Temporal(TemporalType.DATE)
    private Date dateOfAdmission;
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
    @JoinColumn(name = "class_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Classes admissionClass;
    @JoinColumn(name = "stream_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Streams admissionStream;
    @JoinColumn(name = "term_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Terms admissionTerm;
    @JoinColumn(name = "author_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Users author;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "admissionId")
    private Collection<StudentTermRegistration> studentTermRegistrationCollection;

    public StudentAdmission() {
    }

    public StudentAdmission(Long id) {
        this.id = id;
    }

    public StudentAdmission(Long id, long studentId, String admissionNo, String externalId, Date dateOfAdmission, String status, Date dateCreated) {
        this.id = id;
        this.studentId = studentId;
        this.admissionNo = admissionNo;
        this.externalId = externalId;
        this.dateOfAdmission = dateOfAdmission;
        this.status = status;
        this.dateCreated = dateCreated;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getStudentId() {
        return studentId;
    }

    public void setStudentId(long studentId) {
        this.studentId = studentId;
    }

    public String getAdmissionNo() {
        return admissionNo;
    }

    public void setAdmissionNo(String admissionNo) {
        this.admissionNo = admissionNo;
    }

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public Date getDateOfAdmission() {
        return dateOfAdmission;
    }

    public void setDateOfAdmission(Date dateOfAdmission) {
        this.dateOfAdmission = dateOfAdmission;
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

    public Classes getAdmissionClass() {
        return admissionClass;
    }

    public void setAdmissionClass(Classes admissionClass) {
        this.admissionClass = admissionClass;
    }

    public Streams getAdmissionStream() {
        return admissionStream;
    }

    public void setAdmissionStream(Streams admissionStream) {
        this.admissionStream = admissionStream;
    }

    public Terms getAdmissionTerm() {
        return admissionTerm;
    }

    public void setAdmissionTerm(Terms admissionTerm) {
        this.admissionTerm = admissionTerm;
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
        if (!(object instanceof StudentAdmission)) {
            return false;
        }
        StudentAdmission other = (StudentAdmission) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.codemovers.scholar.engine.db.entities.StudentAdmission[ id=" + id + " ]";
    }

}
