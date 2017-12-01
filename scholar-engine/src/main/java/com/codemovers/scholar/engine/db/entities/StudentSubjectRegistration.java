/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codemovers.scholar.engine.db.entities;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author mover
 */
@Entity
@Table(name = "student_subject_registration")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "StudentSubjectRegistration.findAll", query = "SELECT s FROM StudentSubjectRegistration s")
    , @NamedQuery(name = "StudentSubjectRegistration.findById", query = "SELECT s FROM StudentSubjectRegistration s WHERE s.id = :id")
    , @NamedQuery(name = "StudentSubjectRegistration.findByStatus", query = "SELECT s FROM StudentSubjectRegistration s WHERE s.status = :status")
    , @NamedQuery(name = "StudentSubjectRegistration.findByDateCreated", query = "SELECT s FROM StudentSubjectRegistration s WHERE s.dateCreated = :dateCreated")})
public class StudentSubjectRegistration implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
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
    @JoinColumn(name = "student_term_registration_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private StudentTermRegistration studentTermRegistrationId;
    @JoinColumn(name = "paper_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private SubjectPapers paperId;
    @JoinColumn(name = "subject_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Subjects subjectId;
    @JoinColumn(name = "author_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Users authorId;

    public StudentSubjectRegistration() {
    }

    public StudentSubjectRegistration(Long id) {
        this.id = id;
    }

    public StudentSubjectRegistration(Long id, String status, Date dateCreated) {
        this.id = id;
        this.status = status;
        this.dateCreated = dateCreated;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public StudentTermRegistration getStudentTermRegistrationId() {
        return studentTermRegistrationId;
    }

    public void setStudentTermRegistrationId(StudentTermRegistration studentTermRegistrationId) {
        this.studentTermRegistrationId = studentTermRegistrationId;
    }

    public SubjectPapers getPaperId() {
        return paperId;
    }

    public void setPaperId(SubjectPapers paperId) {
        this.paperId = paperId;
    }

    public Subjects getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Subjects subjectId) {
        this.subjectId = subjectId;
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
        if (!(object instanceof StudentSubjectRegistration)) {
            return false;
        }
        StudentSubjectRegistration other = (StudentSubjectRegistration) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.codemovers.scholar.engine.db.entities.StudentSubjectRegistration[ id=" + id + " ]";
    }
    
}
