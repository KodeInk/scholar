/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codemovers.scholar.engine.db.entities;

import java.io.Serializable;
import java.math.BigInteger;
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
@Table(name = "exam_class")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ExamClass.findAll", query = "SELECT e FROM ExamClass e")
    , @NamedQuery(name = "ExamClass.findById", query = "SELECT e FROM ExamClass e WHERE e.id = :id")
    , @NamedQuery(name = "ExamClass.findByClassId", query = "SELECT e FROM ExamClass e WHERE e.classId = :classId")
    , @NamedQuery(name = "ExamClass.findByStatus", query = "SELECT e FROM ExamClass e WHERE e.status = :status")
    , @NamedQuery(name = "ExamClass.findByDateCreated", query = "SELECT e FROM ExamClass e WHERE e.dateCreated = :dateCreated")})
public class ExamClass implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Column(name = "class_id")
    private BigInteger classId;
    @Size(max = 8)
    @Column(name = "status")
    private String status;
    @Column(name = "date_created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;
    @JoinColumn(name = "exam_id", referencedColumnName = "id")
    @ManyToOne
    private Classes examId;
    @JoinColumn(name = "exam_id", referencedColumnName = "id")
    @ManyToOne
    private Exams examId1;
    @JoinColumn(name = "author_id", referencedColumnName = "id")
    @ManyToOne
    private Users authorId;

    public ExamClass() {
    }

    public ExamClass(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigInteger getClassId() {
        return classId;
    }

    public void setClassId(BigInteger classId) {
        this.classId = classId;
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

    public Classes getExamId() {
        return examId;
    }

    public void setExamId(Classes examId) {
        this.examId = examId;
    }

    public Exams getExamId1() {
        return examId1;
    }

    public void setExamId1(Exams examId1) {
        this.examId1 = examId1;
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
        if (!(object instanceof ExamClass)) {
            return false;
        }
        ExamClass other = (ExamClass) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.codemovers.scholar.engine.db.entities.ExamClass[ id=" + id + " ]";
    }
    
}
