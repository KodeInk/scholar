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
@Table(name = "library_transactions")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LibraryTransactions.findAll", query = "SELECT l FROM LibraryTransactions l")
    , @NamedQuery(name = "LibraryTransactions.findById", query = "SELECT l FROM LibraryTransactions l WHERE l.id = :id")
    , @NamedQuery(name = "LibraryTransactions.findByBorrowerId", query = "SELECT l FROM LibraryTransactions l WHERE l.borrowerId = :borrowerId")
    , @NamedQuery(name = "LibraryTransactions.findByBorrowerType", query = "SELECT l FROM LibraryTransactions l WHERE l.borrowerType = :borrowerType")
    , @NamedQuery(name = "LibraryTransactions.findByExpectedReturnDate", query = "SELECT l FROM LibraryTransactions l WHERE l.expectedReturnDate = :expectedReturnDate")
    , @NamedQuery(name = "LibraryTransactions.findByActualReturnDate", query = "SELECT l FROM LibraryTransactions l WHERE l.actualReturnDate = :actualReturnDate")
    , @NamedQuery(name = "LibraryTransactions.findByTransactionType", query = "SELECT l FROM LibraryTransactions l WHERE l.transactionType = :transactionType")
    , @NamedQuery(name = "LibraryTransactions.findByDateCreated", query = "SELECT l FROM LibraryTransactions l WHERE l.dateCreated = :dateCreated")
    , @NamedQuery(name = "LibraryTransactions.findByComments", query = "SELECT l FROM LibraryTransactions l WHERE l.comments = :comments")
    , @NamedQuery(name = "LibraryTransactions.findByParentTransactionId", query = "SELECT l FROM LibraryTransactions l WHERE l.parentTransactionId = :parentTransactionId")})
public class LibraryTransactions implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "borrower_id")
    private long borrowerId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "borrower_type")
    private String borrowerType;
    @Basic(optional = false)
    @NotNull
    @Column(name = "expected_return_date")
    @Temporal(TemporalType.DATE)
    private Date expectedReturnDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "actual_return_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date actualReturnDate;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 7)
    @Column(name = "transaction_type")
    private String transactionType;
    @Basic(optional = false)
    @NotNull
    @Column(name = "date_created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "comments")
    private String comments;
    @Basic(optional = false)
    @NotNull
    @Column(name = "parent_transaction_id")
    private long parentTransactionId;
    @JoinColumn(name = "inventory_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private LibraryStockInventory inventoryId;
    @JoinColumn(name = "author_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Users authorId;

    public LibraryTransactions() {
    }

    public LibraryTransactions(Long id) {
        this.id = id;
    }

    public LibraryTransactions(Long id, long borrowerId, String borrowerType, Date expectedReturnDate, Date actualReturnDate, String transactionType, Date dateCreated, String comments, long parentTransactionId) {
        this.id = id;
        this.borrowerId = borrowerId;
        this.borrowerType = borrowerType;
        this.expectedReturnDate = expectedReturnDate;
        this.actualReturnDate = actualReturnDate;
        this.transactionType = transactionType;
        this.dateCreated = dateCreated;
        this.comments = comments;
        this.parentTransactionId = parentTransactionId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getBorrowerId() {
        return borrowerId;
    }

    public void setBorrowerId(long borrowerId) {
        this.borrowerId = borrowerId;
    }

    public String getBorrowerType() {
        return borrowerType;
    }

    public void setBorrowerType(String borrowerType) {
        this.borrowerType = borrowerType;
    }

    public Date getExpectedReturnDate() {
        return expectedReturnDate;
    }

    public void setExpectedReturnDate(Date expectedReturnDate) {
        this.expectedReturnDate = expectedReturnDate;
    }

    public Date getActualReturnDate() {
        return actualReturnDate;
    }

    public void setActualReturnDate(Date actualReturnDate) {
        this.actualReturnDate = actualReturnDate;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public long getParentTransactionId() {
        return parentTransactionId;
    }

    public void setParentTransactionId(long parentTransactionId) {
        this.parentTransactionId = parentTransactionId;
    }

    public LibraryStockInventory getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(LibraryStockInventory inventoryId) {
        this.inventoryId = inventoryId;
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
        if (!(object instanceof LibraryTransactions)) {
            return false;
        }
        LibraryTransactions other = (LibraryTransactions) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.codemovers.scholar.engine.db.entities.LibraryTransactions[ id=" + id + " ]";
    }

}
