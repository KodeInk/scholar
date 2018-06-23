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
@Table(name = "classes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Classes.findAll", query = "SELECT c FROM Classes c")
    , @NamedQuery(name = "Classes.findById", query = "SELECT c FROM Classes c WHERE c.id = :id")
    , @NamedQuery(name = "Classes.findByName", query = "SELECT c FROM Classes c WHERE c.name = :name")
    , @NamedQuery(name = "Classes.findByCode", query = "SELECT c FROM Classes c WHERE c.code = :code")
    , @NamedQuery(name = "Classes.findByRanking", query = "SELECT c FROM Classes c WHERE c.ranking = :ranking")
    , @NamedQuery(name = "Classes.findByStatus", query = "SELECT c FROM Classes c WHERE c.status = :status")
    , @NamedQuery(name = "Classes.findByDateCreated", query = "SELECT c FROM Classes c WHERE c.dateCreated = :dateCreated")
          , @NamedQuery(name = "Classes.findClassByNameRankCode", query = "SELECT c FROM Classes c WHERE (c.name = :name OR c.ranking = :rank OR c.code = :code )")
})
public class Classes implements Serializable {

   
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
    @Column(name = "ranking")
    private long ranking;
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
    @JoinColumn(name = "author_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Users author;
    @OneToMany(mappedBy = "classId")
    private Collection<ClassStream> classStreamCollection;
    @OneToMany(mappedBy = "classId")
    private Collection<SubjectClass> subjectClassCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "admissionClass")
    private Collection<StudentAdmission> studentAdmissionCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "classId")
    private Collection<Marksheet> marksheetCollection;
    @OneToMany(mappedBy = "examId")
    private Collection<ExamClass> examClassCollection;

    public Classes() {
    }

    public Classes(Long id) {
        this.id = id;
    }

    public Classes(Long id, String name, String code, long ranking, String status, Date dateCreated) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.ranking = ranking;
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

    public long getRanking() {
        return ranking;
    }

    public void setRanking(long ranking) {
        this.ranking = ranking;
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

    public Users getAuthor() {
        return author;
    }

    public void setAuthor(Users author) {
        this.author = author;
    }

    @XmlTransient
    public Collection<ClassStream> getClassStreamCollection() {
        return classStreamCollection;
    }

    public void setClassStreamCollection(Collection<ClassStream> classStreamCollection) {
        this.classStreamCollection = classStreamCollection;
    }

    @XmlTransient
    public Collection<SubjectClass> getSubjectClassCollection() {
        return subjectClassCollection;
    }

    public void setSubjectClassCollection(Collection<SubjectClass> subjectClassCollection) {
        this.subjectClassCollection = subjectClassCollection;
    }

    @XmlTransient
    public Collection<StudentAdmission> getStudentAdmissionCollection() {
        return studentAdmissionCollection;
    }

    public void setStudentAdmissionCollection(Collection<StudentAdmission> studentAdmissionCollection) {
        this.studentAdmissionCollection = studentAdmissionCollection;
    }

    @XmlTransient
    public Collection<Marksheet> getMarksheetCollection() {
        return marksheetCollection;
    }

    public void setMarksheetCollection(Collection<Marksheet> marksheetCollection) {
        this.marksheetCollection = marksheetCollection;
    }

    @XmlTransient
    public Collection<ExamClass> getExamClassCollection() {
        return examClassCollection;
    }

    public void setExamClassCollection(Collection<ExamClass> examClassCollection) {
        this.examClassCollection = examClassCollection;
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
        if (!(object instanceof Classes)) {
            return false;
        }
        Classes other = (Classes) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.codemovers.scholar.engine.db.entities.Classes[ id=" + id + " ]";
    }

    

}
