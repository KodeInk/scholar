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
@Table(name = "exam_tem")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ExamTem.findAll", query = "SELECT e FROM ExamTem e")
    , @NamedQuery(name = "ExamTem.findById", query = "SELECT e FROM ExamTem e WHERE e.id = :id")
    , @NamedQuery(name = "ExamTem.findByStatus", query = "SELECT e FROM ExamTem e WHERE e.status = :status")
    , @NamedQuery(name = "ExamTem.findByDateCreated", query = "SELECT e FROM ExamTem e WHERE e.dateCreated = :dateCreated")})
public class ExamTem implements Serializable {

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
    @JoinColumn(name = "exam_id", referencedColumnName = "id")
    @ManyToOne
    private Exams examId;
    @JoinColumn(name = "term_id", referencedColumnName = "id")
    @ManyToOne
    private Terms termId;
    @JoinColumn(name = "author_id", referencedColumnName = "id")
    @ManyToOne
    private Users authorId;

    public ExamTem() {
    }

    public ExamTem(Long id) {
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

    public Exams getExamId() {
        return examId;
    }

    public void setExamId(Exams examId) {
        this.examId = examId;
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
        if (!(object instanceof ExamTem)) {
            return false;
        }
        ExamTem other = (ExamTem) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.codemovers.scholar.engine.db.entities.ExamTem[ id=" + id + " ]";
    }

}
