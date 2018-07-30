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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author mover
 */
@Entity
@Table(name = "curriculum")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Curriculum.findAll", query = "SELECT c FROM Curriculum c")
    , @NamedQuery(name = "Curriculum.findById", query = "SELECT c FROM Curriculum c WHERE c.id = :id")
    , @NamedQuery(name = "Curriculum.findByName", query = "SELECT c FROM Curriculum c WHERE c.name = :name")
    , @NamedQuery(name = "Curriculum.findByCode", query = "SELECT c FROM Curriculum c WHERE c.code = :code")
    , @NamedQuery(name = "Curriculum.findByDescription", query = "SELECT c FROM Curriculum c WHERE c.description = :description")
    , @NamedQuery(name = "Curriculum.findByStatus", query = "SELECT c FROM Curriculum c WHERE c.status = :status")
    , @NamedQuery(name = "Curriculum.findByDateCreated", query = "SELECT c FROM Curriculum c WHERE c.dateCreated = :dateCreated")
    , @NamedQuery(name = "Curriculum.findByNameOrCode", query = "SELECT c FROM Curriculum c WHERE (c.name = :name OR c.code :code) AND c.id <> :id ")
})
public class Curriculum implements Serializable {

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
    @Size(max = 500)
    @Column(name = "description")
    private String description;
    @Size(max = 8)
    @Column(name = "status")
    private String status;
    @Column(name = "date_created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "curriculumId")
    private Collection<SubjectCurriculum> subjectCurriculumCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "curriculumId")
    private Collection<CurriculumDetails> curriculumDetailsCollection;
    @JoinColumn(name = "author_id", referencedColumnName = "id")
    @ManyToOne
    private Users author;
    @OneToMany(mappedBy = "curriculumId")
    private Collection<StudyYearCurriculum> studyYearCurriculumCollection;

    public Curriculum() {
    }

    public Curriculum(Long id) {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
    public Collection<SubjectCurriculum> getSubjectCurriculumCollection() {
        return subjectCurriculumCollection;
    }

    public void setSubjectCurriculumCollection(Collection<SubjectCurriculum> subjectCurriculumCollection) {
        this.subjectCurriculumCollection = subjectCurriculumCollection;
    }

    @XmlTransient
    public Collection<CurriculumDetails> getCurriculumDetailsCollection() {
        return curriculumDetailsCollection;
    }

    public void setCurriculumDetailsCollection(Collection<CurriculumDetails> curriculumDetailsCollection) {
        this.curriculumDetailsCollection = curriculumDetailsCollection;
    }

    public Users getAuthor() {
        return author;
    }

    public void setAuthor(Users author) {
        this.author = author;
    }

    @XmlTransient
    public Collection<StudyYearCurriculum> getStudyYearCurriculumCollection() {
        return studyYearCurriculumCollection;
    }

    public void setStudyYearCurriculumCollection(Collection<StudyYearCurriculum> studyYearCurriculumCollection) {
        this.studyYearCurriculumCollection = studyYearCurriculumCollection;
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
        if (!(object instanceof Curriculum)) {
            return false;
        }
        Curriculum other = (Curriculum) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.codemovers.scholar.engine.db.entities.Curriculum[ id=" + id + " ]";
    }

}
