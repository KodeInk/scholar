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
@Table(name = "library_stock")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LibraryStock.findAll", query = "SELECT l FROM LibraryStock l")
    , @NamedQuery(name = "LibraryStock.findById", query = "SELECT l FROM LibraryStock l WHERE l.id = :id")
    , @NamedQuery(name = "LibraryStock.findByStockNumber", query = "SELECT l FROM LibraryStock l WHERE l.stockNumber = :stockNumber")
    , @NamedQuery(name = "LibraryStock.findByStockTitle", query = "SELECT l FROM LibraryStock l WHERE l.stockTitle = :stockTitle")
    , @NamedQuery(name = "LibraryStock.findByNumberOfBooks", query = "SELECT l FROM LibraryStock l WHERE l.numberOfBooks = :numberOfBooks")
    , @NamedQuery(name = "LibraryStock.findByDateCreated", query = "SELECT l FROM LibraryStock l WHERE l.dateCreated = :dateCreated")})
public class LibraryStock implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "stock_number")
    private String stockNumber;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "stock_title")
    private String stockTitle;
    @Basic(optional = false)
    @NotNull
    @Column(name = "number_of_books")
    private long numberOfBooks;
    @Basic(optional = false)
    @NotNull
    @Column(name = "date_created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;
    @JoinColumn(name = "section_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private LibrarySection sectionId;
    @JoinColumn(name = "author_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Users authorId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "stockId")
    private Collection<LibraryStockInventory> libraryStockInventoryCollection;

    public LibraryStock() {
    }

    public LibraryStock(Long id) {
        this.id = id;
    }

    public LibraryStock(Long id, String stockNumber, String stockTitle, long numberOfBooks, Date dateCreated) {
        this.id = id;
        this.stockNumber = stockNumber;
        this.stockTitle = stockTitle;
        this.numberOfBooks = numberOfBooks;
        this.dateCreated = dateCreated;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStockNumber() {
        return stockNumber;
    }

    public void setStockNumber(String stockNumber) {
        this.stockNumber = stockNumber;
    }

    public String getStockTitle() {
        return stockTitle;
    }

    public void setStockTitle(String stockTitle) {
        this.stockTitle = stockTitle;
    }

    public long getNumberOfBooks() {
        return numberOfBooks;
    }

    public void setNumberOfBooks(long numberOfBooks) {
        this.numberOfBooks = numberOfBooks;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public LibrarySection getSectionId() {
        return sectionId;
    }

    public void setSectionId(LibrarySection sectionId) {
        this.sectionId = sectionId;
    }

    public Users getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Users authorId) {
        this.authorId = authorId;
    }

    @XmlTransient
    public Collection<LibraryStockInventory> getLibraryStockInventoryCollection() {
        return libraryStockInventoryCollection;
    }

    public void setLibraryStockInventoryCollection(Collection<LibraryStockInventory> libraryStockInventoryCollection) {
        this.libraryStockInventoryCollection = libraryStockInventoryCollection;
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
        if (!(object instanceof LibraryStock)) {
            return false;
        }
        LibraryStock other = (LibraryStock) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.codemovers.scholar.engine.db.entities.LibraryStock[ id=" + id + " ]";
    }
    
}
