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
@Table(name = "student_term_registration")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "StudentTermRegistration.findAll", query = "SELECT s FROM StudentTermRegistration s")
    , @NamedQuery(name = "StudentTermRegistration.findById", query = "SELECT s FROM StudentTermRegistration s WHERE s.id = :id")
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
    @Column(name = "date_created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "status")
    private String status;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "studentTermRegistrationId")
    private Collection<StudentSubjectRegistration> studentSubjectRegistrationCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "termRegistrationId")
    private Collection<StudentExamRegistration> studentExamRegistrationCollection;
    @JoinColumn(name = "stream_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Streams streamId;
    @JoinColumn(name = "admission_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private StudentAdmission admissionId;
    @JoinColumn(name = "term_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Terms termId;
    @JoinColumn(name = "author_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Users authorId;

    public StudentTermRegistration() {
    }

    public StudentTermRegistration(Long id) {
        this.id = id;
    }

    public StudentTermRegistration(Long id, Date dateCreated, String status) {
        this.id = id;
        this.dateCreated = dateCreated;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    @XmlTransient
    public Collection<StudentSubjectRegistration> getStudentSubjectRegistrationCollection() {
        return studentSubjectRegistrationCollection;
    }

    public void setStudentSubjectRegistrationCollection(Collection<StudentSubjectRegistration> studentSubjectRegistrationCollection) {
        this.studentSubjectRegistrationCollection = studentSubjectRegistrationCollection;
    }

    @XmlTransient
    public Collection<StudentExamRegistration> getStudentExamRegistrationCollection() {
        return studentExamRegistrationCollection;
    }

    public void setStudentExamRegistrationCollection(Collection<StudentExamRegistration> studentExamRegistrationCollection) {
        this.studentExamRegistrationCollection = studentExamRegistrationCollection;
    }

    public Streams getStreamId() {
        return streamId;
    }

    public void setStreamId(Streams streamId) {
        this.streamId = streamId;
    }

    public StudentAdmission getAdmissionId() {
        return admissionId;
    }

    public void setAdmissionId(StudentAdmission admissionId) {
        this.admissionId = admissionId;
    }

    public Terms getTermId() {
        return termId;
    }

    public void setTermId(Terms termId) {
        this.termId = termId;
    }

    public Users getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Users authorId) {
        this.authorId = authorId;
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
