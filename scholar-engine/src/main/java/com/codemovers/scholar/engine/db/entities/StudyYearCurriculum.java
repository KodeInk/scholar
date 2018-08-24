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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author mover
 */
@Entity
@Table(name = "study_year_curriculum")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "StudyYearCurriculum.findAll", query = "SELECT s FROM StudyYearCurriculum s")
    , @NamedQuery(name = "StudyYearCurriculum.findById", query = "SELECT s FROM StudyYearCurriculum s WHERE s.id = :id")
    , @NamedQuery(name = "StudyYearCurriculum.findByStatus", query = "SELECT s FROM StudyYearCurriculum s WHERE s.status = :status")
    , @NamedQuery(name = "StudyYearCurriculum.findByDateCreated", query = "SELECT s FROM StudyYearCurriculum s WHERE s.dateCreated = :dateCreated")})
public class StudyYearCurriculum implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Size(max = 8)
    @Column(name = "status")
    private String status;
    @Column(name = "date_created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;
    @JoinColumn(name = "curriculum_id", referencedColumnName = "id")
    @ManyToOne
    private Curriculum curriculum;
    @JoinColumn(name = "study_year_id", referencedColumnName = "id")
    @ManyToOne
    private StudyYear studyYear;
    @JoinColumn(name = "author_id", referencedColumnName = "id")
    @ManyToOne
    private Users authorId;

    public StudyYearCurriculum() {
    }

    public StudyYearCurriculum(Long id) {
        this.id = id;
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

    public Curriculum getCurriculum() {
        return curriculum;
    }

    public void setCurriculum(Curriculum curriculum) {
        this.curriculum = curriculum;
    }

    public StudyYear getStudyYear() {
        return studyYear;
    }

    public void setStudyYear(StudyYear studyYear) {
        this.studyYear = studyYear;
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
        if (!(object instanceof StudyYearCurriculum)) {
            return false;
        }
        StudyYearCurriculum other = (StudyYearCurriculum) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.codemovers.scholar.engine.db.entities.StudyYearCurriculum[ id=" + id + " ]";
    }

}
