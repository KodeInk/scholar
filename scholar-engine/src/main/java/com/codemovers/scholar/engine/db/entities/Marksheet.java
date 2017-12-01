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
@Table(name = "marksheet")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Marksheet.findAll", query = "SELECT m FROM Marksheet m")
    , @NamedQuery(name = "Marksheet.findById", query = "SELECT m FROM Marksheet m WHERE m.id = :id")
    , @NamedQuery(name = "Marksheet.findByStudentId", query = "SELECT m FROM Marksheet m WHERE m.studentId = :studentId")
    , @NamedQuery(name = "Marksheet.findByStatus", query = "SELECT m FROM Marksheet m WHERE m.status = :status")
    , @NamedQuery(name = "Marksheet.findByMark", query = "SELECT m FROM Marksheet m WHERE m.mark = :mark")
    , @NamedQuery(name = "Marksheet.findByComment", query = "SELECT m FROM Marksheet m WHERE m.comment = :comment")
    , @NamedQuery(name = "Marksheet.findByDateCreated", query = "SELECT m FROM Marksheet m WHERE m.dateCreated = :dateCreated")})
public class Marksheet implements Serializable {

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
    @Size(min = 1, max = 8)
    @Column(name = "status")
    private String status;
    @Basic(optional = false)
    @NotNull
    @Column(name = "mark")
    private long mark;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "comment")
    private String comment;
    @Basic(optional = false)
    @NotNull
    @Column(name = "date_created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;
    @JoinColumn(name = "class_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Classes classId;
    @JoinColumn(name = "exam_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Exams examId;
    @JoinColumn(name = "paper_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private SubjectPapers paperId;
    @JoinColumn(name = "subject_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Subjects subjectId;
    @JoinColumn(name = "author_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Users authorId;

    public Marksheet() {
    }

    public Marksheet(Long id) {
        this.id = id;
    }

    public Marksheet(Long id, long studentId, String status, long mark, String comment, Date dateCreated) {
        this.id = id;
        this.studentId = studentId;
        this.status = status;
        this.mark = mark;
        this.comment = comment;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public long getMark() {
        return mark;
    }

    public void setMark(long mark) {
        this.mark = mark;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Classes getClassId() {
        return classId;
    }

    public void setClassId(Classes classId) {
        this.classId = classId;
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
        if (!(object instanceof Marksheet)) {
            return false;
        }
        Marksheet other = (Marksheet) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.codemovers.scholar.engine.db.entities.Marksheet[ id=" + id + " ]";
    }
    
}
