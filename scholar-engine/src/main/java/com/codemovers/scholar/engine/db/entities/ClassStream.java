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
@Table(name = "class_stream")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ClassStream.findAll", query = "SELECT c FROM ClassStream c")
    , @NamedQuery(name = "ClassStream.findById", query = "SELECT c FROM ClassStream c WHERE c.id = :id")
    , @NamedQuery(name = "ClassStream.findByStatus", query = "SELECT c FROM ClassStream c WHERE c.status = :status")
    , @NamedQuery(name = "ClassStream.findByDateCreated", query = "SELECT c FROM ClassStream c WHERE c.dateCreated = :dateCreated")})
public class ClassStream implements Serializable {

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
    @JoinColumn(name = "class_id", referencedColumnName = "id")
    @ManyToOne
    private Classes streamClass;
    @JoinColumn(name = "stream_id", referencedColumnName = "id")
    @ManyToOne
    private Streams classStream;
    @JoinColumn(name = "author_id", referencedColumnName = "id")
    @ManyToOne
    private Users author;

    public ClassStream() {
    }

    public ClassStream(Long id) {
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

    public Classes getStreamClass() {
        return streamClass;
    }

    public void setStreamClass(Classes streamClass) {
        this.streamClass = streamClass;
    }

    public Streams getClassStream() {
        return classStream;
    }

    public void setClassStream(Streams classStream) {
        this.classStream = classStream;
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
        if (!(object instanceof ClassStream)) {
            return false;
        }
        ClassStream other = (ClassStream) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.codemovers.scholar.engine.db.entities.ClassStream[ id=" + id + " ]";
    }

}
