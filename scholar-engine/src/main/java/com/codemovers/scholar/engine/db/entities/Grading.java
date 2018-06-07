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
@Table(name = "grading")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Grading.findAll", query = "SELECT g FROM Grading g")
    , @NamedQuery(name = "Grading.findById", query = "SELECT g FROM Grading g WHERE g.id = :id")
    , @NamedQuery(name = "Grading.findByName", query = "SELECT g FROM Grading g WHERE g.name = :name")
    , @NamedQuery(name = "Grading.findByCode", query = "SELECT g FROM Grading g WHERE g.code = :code")
    , @NamedQuery(name = "Grading.findByDescription", query = "SELECT g FROM Grading g WHERE g.description = :description")
    , @NamedQuery(name = "Grading.findByStatus", query = "SELECT g FROM Grading g WHERE g.status = :status")
    , @NamedQuery(name = "Grading.findByDateCreated", query = "SELECT g FROM Grading g WHERE g.dateCreated = :dateCreated")
    , @NamedQuery(name = "Grading.findByNameOrCode", query = "SELECT g FROM Grading g WHERE g.name = :name  OR g.code = :code")

})
public class Grading implements Serializable {

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
    @Size(min = 1, max = 500)
    @Column(name = "description")
    private String description;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "gradingId")
    private Collection<SubjectGrading> subjectGradingCollection;
    @JoinColumn(name = "author_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Users author;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "gradingId")
    private Collection<GradingDetails> gradingDetailsCollection;

    public Grading() {
    }

    public Grading(Long id) {
        this.id = id;
    }

    public Grading(Long id, String name, String code, String description, String status, Date dateCreated) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.description = description;
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
    public Collection<SubjectGrading> getSubjectGradingCollection() {
        return subjectGradingCollection;
    }

    public void setSubjectGradingCollection(Collection<SubjectGrading> subjectGradingCollection) {
        this.subjectGradingCollection = subjectGradingCollection;
    }

    public Users getAuthor() {
        return author;
    }

    public void setAuthor(Users author) {
        this.author = author;
    }

    @XmlTransient
    public Collection<GradingDetails> getGradingDetailsCollection() {
        return gradingDetailsCollection;
    }

    public void setGradingDetailsCollection(Collection<GradingDetails> gradingDetailsCollection) {
        this.gradingDetailsCollection = gradingDetailsCollection;
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
        if (!(object instanceof Grading)) {
            return false;
        }
        Grading other = (Grading) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.codemovers.scholar.engine.db.entities.Grading[ id=" + id + " ]";
    }

}
