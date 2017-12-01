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
@Table(name = "teaching_timetable")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TeachingTimetable.findAll", query = "SELECT t FROM TeachingTimetable t")
    , @NamedQuery(name = "TeachingTimetable.findById", query = "SELECT t FROM TeachingTimetable t WHERE t.id = :id")
    , @NamedQuery(name = "TeachingTimetable.findByTeacherId", query = "SELECT t FROM TeachingTimetable t WHERE t.teacherId = :teacherId")
    , @NamedQuery(name = "TeachingTimetable.findByStatus", query = "SELECT t FROM TeachingTimetable t WHERE t.status = :status")
    , @NamedQuery(name = "TeachingTimetable.findByDateCreated", query = "SELECT t FROM TeachingTimetable t WHERE t.dateCreated = :dateCreated")})
public class TeachingTimetable implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "teacher_id")
    private long teacherId;
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
    @JoinColumn(name = "paper_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private SubjectPapers paperId;
    @JoinColumn(name = "subject_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Subjects subjectId;
    @JoinColumn(name = "term_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Terms termId;
    @JoinColumn(name = "author_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Users authorId;

    public TeachingTimetable() {
    }

    public TeachingTimetable(Long id) {
        this.id = id;
    }

    public TeachingTimetable(Long id, long teacherId, String status, Date dateCreated) {
        this.id = id;
        this.teacherId = teacherId;
        this.status = status;
        this.dateCreated = dateCreated;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(long teacherId) {
        this.teacherId = teacherId;
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
        if (!(object instanceof TeachingTimetable)) {
            return false;
        }
        TeachingTimetable other = (TeachingTimetable) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.codemovers.scholar.engine.db.entities.TeachingTimetable[ id=" + id + " ]";
    }
    
}
