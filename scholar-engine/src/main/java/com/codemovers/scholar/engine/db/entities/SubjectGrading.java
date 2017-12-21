/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codemovers.scholar.engine.db.entities;

import java.io.Serializable;
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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author mover
 */
@Entity
@Table(name = "subject_grading")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SubjectGrading.findAll", query = "SELECT s FROM SubjectGrading s")
    , @NamedQuery(name = "SubjectGrading.findById", query = "SELECT s FROM SubjectGrading s WHERE s.id = :id")
    , @NamedQuery(name = "SubjectGrading.findByStatus", query = "SELECT s FROM SubjectGrading s WHERE s.status = :status")})
public class SubjectGrading implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Size(max = 8)
    @Column(name = "status")
    private String status;
    @JoinColumn(name = "grading_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Grading gradingId;
    @JoinColumn(name = "subject_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Subjects subjectId;
    @JoinColumn(name = "author_id", referencedColumnName = "id")
    @ManyToOne
    private Users authorId;

    public SubjectGrading() {
    }

    public SubjectGrading(Long id) {
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

    public Grading getGradingId() {
        return gradingId;
    }

    public void setGradingId(Grading gradingId) {
        this.gradingId = gradingId;
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
        if (!(object instanceof SubjectGrading)) {
            return false;
        }
        SubjectGrading other = (SubjectGrading) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.codemovers.scholar.engine.db.entities.SubjectGrading[ id=" + id + " ]";
    }

}
