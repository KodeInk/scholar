/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codemovers.scholar.engine.db.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
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
@Table(name = "study_year")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "StudyYear.findAll", query = "SELECT s FROM StudyYear s")
    , @NamedQuery(name = "StudyYear.findById", query = "SELECT s FROM StudyYear s WHERE s.id = :id")
    , @NamedQuery(name = "StudyYear.findByTheme", query = "SELECT s FROM StudyYear s WHERE s.theme = :theme")
    , @NamedQuery(name = "StudyYear.findByStartDate", query = "SELECT s FROM StudyYear s WHERE s.startDate = :startDate")
    , @NamedQuery(name = "StudyYear.findByEndDate", query = "SELECT s FROM StudyYear s WHERE s.endDate = :endDate")
    , @NamedQuery(name = "StudyYear.findByStatus", query = "SELECT s FROM StudyYear s WHERE s.status = :status")
    , @NamedQuery(name = "StudyYear.findByDateCreated", query = "SELECT s FROM StudyYear s WHERE s.dateCreated = :dateCreated")})
public class StudyYear implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Size(max = 255)
    @Column(name = "theme")
    private String theme;
    @Column(name = "start_date")
    @Temporal(TemporalType.DATE)
    private Date startDate;
    @Column(name = "end_date")
    @Temporal(TemporalType.DATE)
    private Date endDate;
    @Size(max = 8)
    @Column(name = "status")
    private String status;
    @Column(name = "date_created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "studyYear")
    private Collection<Terms> termsCollection;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "study_year_curriculum", joinColumns = {
        @JoinColumn(name = "study_year_id", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "curriculum_id", referencedColumnName = "id")})
    private Set<Curriculum> curricula;

    @JoinColumn(name = "author_id", referencedColumnName = "id")
    @ManyToOne
    private Users author;

    public StudyYear() {
    }

    public StudyYear(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
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
    public Collection<Terms> getTermsCollection() {
        return termsCollection;
    }

    public void setTermsCollection(Collection<Terms> termsCollection) {
        this.termsCollection = termsCollection;
    }

    public Set<Curriculum> getCurricula() {
        return curricula;
    }

    public void setCurricula(Set<Curriculum> curricula) {
        this.curricula = curricula;
    }

    

    public Users getAuthor() {
        return author;
    }

    public void setAuthor(Users author) {
        this.author = author;
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
        if (!(object instanceof StudyYear)) {
            return false;
        }
        StudyYear other = (StudyYear) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.codemovers.scholar.engine.db.entities.StudyYear[ id=" + id + " ]";
    }

}
