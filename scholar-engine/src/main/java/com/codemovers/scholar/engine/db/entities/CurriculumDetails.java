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
@Table(name = "curriculum_details")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CurriculumDetails.findAll", query = "SELECT c FROM CurriculumDetails c")
    , @NamedQuery(name = "CurriculumDetails.findById", query = "SELECT c FROM CurriculumDetails c WHERE c.id = :id")
    , @NamedQuery(name = "CurriculumDetails.findByName", query = "SELECT c FROM CurriculumDetails c WHERE c.name = :name")
    , @NamedQuery(name = "CurriculumDetails.findByCode", query = "SELECT c FROM CurriculumDetails c WHERE c.code = :code")
    , @NamedQuery(name = "CurriculumDetails.findByDescription", query = "SELECT c FROM CurriculumDetails c WHERE c.description = :description")
    , @NamedQuery(name = "CurriculumDetails.findByStatus", query = "SELECT c FROM CurriculumDetails c WHERE c.status = :status")
    , @NamedQuery(name = "CurriculumDetails.findByDateCreated", query = "SELECT c FROM CurriculumDetails c WHERE c.dateCreated = :dateCreated")})
public class CurriculumDetails implements Serializable {

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
    @JoinColumn(name = "curriculum_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Curriculum curriculumId;
    @JoinColumn(name = "author_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Users authorId;

    public CurriculumDetails() {
    }

    public CurriculumDetails(Long id) {
        this.id = id;
    }

    public CurriculumDetails(Long id, String name, String code, String description, String status, Date dateCreated) {
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

    public Curriculum getCurriculumId() {
        return curriculumId;
    }

    public void setCurriculumId(Curriculum curriculumId) {
        this.curriculumId = curriculumId;
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
        if (!(object instanceof CurriculumDetails)) {
            return false;
        }
        CurriculumDetails other = (CurriculumDetails) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.codemovers.scholar.engine.db.entities.CurriculumDetails[ id=" + id + " ]";
    }
    
}
