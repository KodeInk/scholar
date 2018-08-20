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
 * @author Manny
 */
@Entity
@Table(name = "grading_details")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GradingDetails.findAll", query = "SELECT g FROM GradingDetails g")
    , @NamedQuery(name = "GradingDetails.findById", query = "SELECT g FROM GradingDetails g WHERE g.id = :id")
    , @NamedQuery(name = "GradingDetails.findBySymbol", query = "SELECT g FROM GradingDetails g WHERE g.symbol = :symbol")
    , @NamedQuery(name = "GradingDetails.findByMingrade", query = "SELECT g FROM GradingDetails g WHERE g.mingrade = :mingrade")
    , @NamedQuery(name = "GradingDetails.findByMaxgrade", query = "SELECT g FROM GradingDetails g WHERE g.maxgrade = :maxgrade")
    , @NamedQuery(name = "GradingDetails.findByStatus", query = "SELECT g FROM GradingDetails g WHERE g.status = :status")
    , @NamedQuery(name = "GradingDetails.findByDateCreated", query = "SELECT g FROM GradingDetails g WHERE g.dateCreated = :dateCreated")})
public class GradingDetails implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "symbol")
    private String symbol;
    @Basic(optional = false)
    @NotNull
    @Column(name = "mingrade")
    private long mingrade;
    @Basic(optional = false)
    @NotNull
    @Column(name = "maxgrade")
    private long maxgrade;
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
    @JoinColumn(name = "grading_scale", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Grading gradingScale;
    @JoinColumn(name = "author_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Users author;

    public GradingDetails() {
    }

    public GradingDetails(Long id) {
        this.id = id;
    }

    public GradingDetails(Long id, String symbol, long mingrade, long maxgrade, String status, Date dateCreated) {
        this.id = id;
        this.symbol = symbol;
        this.mingrade = mingrade;
        this.maxgrade = maxgrade;
        this.status = status;
        this.dateCreated = dateCreated;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public long getMingrade() {
        return mingrade;
    }

    public void setMingrade(long mingrade) {
        this.mingrade = mingrade;
    }

    public long getMaxgrade() {
        return maxgrade;
    }

    public void setMaxgrade(long maxgrade) {
        this.maxgrade = maxgrade;
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

    public Grading getGradingScale() {
        return gradingScale;
    }

    public void setGradingScale(Grading gradingScale) {
        this.gradingScale = gradingScale;
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
        if (!(object instanceof GradingDetails)) {
            return false;
        }
        GradingDetails other = (GradingDetails) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.codemovers.scholar.engine.db.entities.GradingDetails[ id=" + id + " ]";
    }
    
}
