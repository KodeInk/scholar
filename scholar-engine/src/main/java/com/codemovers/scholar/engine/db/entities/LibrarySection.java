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
@Table(name = "library_section")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LibrarySection.findAll", query = "SELECT l FROM LibrarySection l")
    , @NamedQuery(name = "LibrarySection.findById", query = "SELECT l FROM LibrarySection l WHERE l.id = :id")
    , @NamedQuery(name = "LibrarySection.findByName", query = "SELECT l FROM LibrarySection l WHERE l.name = :name")
    , @NamedQuery(name = "LibrarySection.findByCode", query = "SELECT l FROM LibrarySection l WHERE l.code = :code")
    , @NamedQuery(name = "LibrarySection.findByExternalId", query = "SELECT l FROM LibrarySection l WHERE l.externalId = :externalId")
    , @NamedQuery(name = "LibrarySection.findByStatus", query = "SELECT l FROM LibrarySection l WHERE l.status = :status")
    , @NamedQuery(name = "LibrarySection.findByDateCreated", query = "SELECT l FROM LibrarySection l WHERE l.dateCreated = :dateCreated")})
public class LibrarySection implements Serializable {

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
    @Size(min = 1, max = 255)
    @Column(name = "external_id")
    private String externalId;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sectionId")
    private Collection<LibraryStock> libraryStockCollection;
    @JoinColumn(name = "author_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Users authorId;

    public LibrarySection() {
    }

    public LibrarySection(Long id) {
        this.id = id;
    }

    public LibrarySection(Long id, String name, String code, String externalId, String status, Date dateCreated) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.externalId = externalId;
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

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
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
    public Collection<LibraryStock> getLibraryStockCollection() {
        return libraryStockCollection;
    }

    public void setLibraryStockCollection(Collection<LibraryStock> libraryStockCollection) {
        this.libraryStockCollection = libraryStockCollection;
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
        if (!(object instanceof LibrarySection)) {
            return false;
        }
        LibrarySection other = (LibrarySection) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.codemovers.scholar.engine.db.entities.LibrarySection[ id=" + id + " ]";
    }

}
