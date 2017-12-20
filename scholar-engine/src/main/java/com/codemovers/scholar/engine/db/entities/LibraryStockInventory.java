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
@Table(name = "library_stock_inventory")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LibraryStockInventory.findAll", query = "SELECT l FROM LibraryStockInventory l")
    , @NamedQuery(name = "LibraryStockInventory.findById", query = "SELECT l FROM LibraryStockInventory l WHERE l.id = :id")
    , @NamedQuery(name = "LibraryStockInventory.findByIsbnumber", query = "SELECT l FROM LibraryStockInventory l WHERE l.isbnumber = :isbnumber")
    , @NamedQuery(name = "LibraryStockInventory.findBySerialnumber", query = "SELECT l FROM LibraryStockInventory l WHERE l.serialnumber = :serialnumber")
    , @NamedQuery(name = "LibraryStockInventory.findByDateCreated", query = "SELECT l FROM LibraryStockInventory l WHERE l.dateCreated = :dateCreated")
    , @NamedQuery(name = "LibraryStockInventory.findByStatus", query = "SELECT l FROM LibraryStockInventory l WHERE l.status = :status")})
public class LibraryStockInventory implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "isbnumber")
    private String isbnumber;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "serialnumber")
    private String serialnumber;
    @Basic(optional = false)
    @NotNull
    @Column(name = "date_created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "status")
    private String status;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "inventoryId")
    private Collection<LibraryTransactions> libraryTransactionsCollection;
    @JoinColumn(name = "book_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Books bookId;
    @JoinColumn(name = "stock_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private LibraryStock stockId;
    @JoinColumn(name = "author_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Users authorId;

    public LibraryStockInventory() {
    }

    public LibraryStockInventory(Long id) {
        this.id = id;
    }

    public LibraryStockInventory(Long id, String isbnumber, String serialnumber, Date dateCreated, String status) {
        this.id = id;
        this.isbnumber = isbnumber;
        this.serialnumber = serialnumber;
        this.dateCreated = dateCreated;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIsbnumber() {
        return isbnumber;
    }

    public void setIsbnumber(String isbnumber) {
        this.isbnumber = isbnumber;
    }

    public String getSerialnumber() {
        return serialnumber;
    }

    public void setSerialnumber(String serialnumber) {
        this.serialnumber = serialnumber;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @XmlTransient
    public Collection<LibraryTransactions> getLibraryTransactionsCollection() {
        return libraryTransactionsCollection;
    }

    public void setLibraryTransactionsCollection(Collection<LibraryTransactions> libraryTransactionsCollection) {
        this.libraryTransactionsCollection = libraryTransactionsCollection;
    }

    public Books getBookId() {
        return bookId;
    }

    public void setBookId(Books bookId) {
        this.bookId = bookId;
    }

    public LibraryStock getStockId() {
        return stockId;
    }

    public void setStockId(LibraryStock stockId) {
        this.stockId = stockId;
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
        if (!(object instanceof LibraryStockInventory)) {
            return false;
        }
        LibraryStockInventory other = (LibraryStockInventory) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.codemovers.scholar.engine.db.entities.LibraryStockInventory[ id=" + id + " ]";
    }

}
