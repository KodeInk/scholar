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
@Table(name = "exam_timetable")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ExamTimetable.findAll", query = "SELECT e FROM ExamTimetable e")
    , @NamedQuery(name = "ExamTimetable.findById", query = "SELECT e FROM ExamTimetable e WHERE e.id = :id")
    , @NamedQuery(name = "ExamTimetable.findByStartDate", query = "SELECT e FROM ExamTimetable e WHERE e.startDate = :startDate")
    , @NamedQuery(name = "ExamTimetable.findByEndDate", query = "SELECT e FROM ExamTimetable e WHERE e.endDate = :endDate")
    , @NamedQuery(name = "ExamTimetable.findByDateCreated", query = "SELECT e FROM ExamTimetable e WHERE e.dateCreated = :dateCreated")
    , @NamedQuery(name = "ExamTimetable.findByStatus", query = "SELECT e FROM ExamTimetable e WHERE e.status = :status")})
public class ExamTimetable implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "start_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "end_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDate;
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
    @JoinColumn(name = "exam_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Exams examId;
    @JoinColumn(name = "paper_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private SubjectPapers paperId;
    @JoinColumn(name = "subject_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Subjects subjectId;
    @JoinColumn(name = "teacher_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Users teacherId;
    @JoinColumn(name = "author_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Users authorId;

    public ExamTimetable() {
    }

    public ExamTimetable(Long id) {
        this.id = id;
    }

    public ExamTimetable(Long id, Date startDate, Date endDate, Date dateCreated, String status) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.dateCreated = dateCreated;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
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

    public Exams getExamId() {
        return examId;
    }

    public void setExamId(Exams examId) {
        this.examId = examId;
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

    public Users getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Users teacherId) {
        this.teacherId = teacherId;
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
        if (!(object instanceof ExamTimetable)) {
            return false;
        }
        ExamTimetable other = (ExamTimetable) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.codemovers.scholar.engine.db.entities.ExamTimetable[ id=" + id + " ]";
    }
    
}
