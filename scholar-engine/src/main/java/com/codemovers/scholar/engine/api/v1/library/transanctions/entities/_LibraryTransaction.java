/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codemovers.scholar.engine.api.v1.library.transanctions.entities;

import com.codemovers.scholar.engine.api.v1.abstracts.AbstractEntity;
import com.codemovers.scholar.engine.api.v1.library.transanctions.entities.enums.Borrower_type_enum;
import com.codemovers.scholar.engine.api.v1.library.transanctions.entities.enums.Transaction_type_enum;
import static com.codemovers.scholar.engine.helper.Utilities.validateMandatoryFields;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author mover 12/20/2017
 */
public class _LibraryTransaction extends AbstractEntity {

    private Integer id;
    private Integer inventory_id;
    private Integer borrower_id;
    private Borrower_type_enum borrower_type;
    private Date expected_return_date;
    private Date actual_return_date;
    private Transaction_type_enum transaction_type;
    private Date date_created;
    private Integer author_id;
    private String comments;
    private Integer parent_transaction_id;

    public _LibraryTransaction() {
    }

    public _LibraryTransaction(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getInventory_id() {
        return inventory_id;
    }

    public void setInventory_id(Integer inventory_id) {
        this.inventory_id = inventory_id;
    }

    public Integer getBorrower_id() {
        return borrower_id;
    }

    public void setBorrower_id(Integer borrower_id) {
        this.borrower_id = borrower_id;
    }

    public Borrower_type_enum getBorrower_type() {
        return borrower_type;
    }

    public void setBorrower_type(Borrower_type_enum borrower_type) {
        this.borrower_type = borrower_type;
    }

    public Date getExpected_return_date() {
        return expected_return_date;
    }

    public void setExpected_return_date(Date expected_return_date) {
        this.expected_return_date = expected_return_date;
    }

    public Date getActual_return_date() {
        return actual_return_date;
    }

    public void setActual_return_date(Date actual_return_date) {
        this.actual_return_date = actual_return_date;
    }

    public Transaction_type_enum getTransaction_type() {
        return transaction_type;
    }

    public void setTransaction_type(Transaction_type_enum transaction_type) {
        this.transaction_type = transaction_type;
    }

    public Date getDate_created() {
        return date_created;
    }

    public void setDate_created(Date date_created) {
        this.date_created = date_created;
    }

    public Integer getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(Integer author_id) {
        this.author_id = author_id;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Integer getParent_transaction_id() {
        return parent_transaction_id;
    }

    public void setParent_transaction_id(Integer parent_transaction_id) {
        this.parent_transaction_id = parent_transaction_id;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.id);
        hash = 97 * hash + Objects.hashCode(this.inventory_id);
        hash = 97 * hash + Objects.hashCode(this.borrower_id);
        hash = 97 * hash + Objects.hashCode(this.borrower_type);
        hash = 97 * hash + Objects.hashCode(this.expected_return_date);
        hash = 97 * hash + Objects.hashCode(this.actual_return_date);
        hash = 97 * hash + Objects.hashCode(this.transaction_type);
        hash = 97 * hash + Objects.hashCode(this.date_created);
        hash = 97 * hash + Objects.hashCode(this.author_id);
        hash = 97 * hash + Objects.hashCode(this.comments);
        hash = 97 * hash + Objects.hashCode(this.parent_transaction_id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final _LibraryTransaction other = (_LibraryTransaction) obj;
        if (!Objects.equals(this.comments, other.comments)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.inventory_id, other.inventory_id)) {
            return false;
        }
        if (!Objects.equals(this.borrower_id, other.borrower_id)) {
            return false;
        }
        if (this.borrower_type != other.borrower_type) {
            return false;
        }
        if (!Objects.equals(this.expected_return_date, other.expected_return_date)) {
            return false;
        }
        if (!Objects.equals(this.actual_return_date, other.actual_return_date)) {
            return false;
        }
        if (this.transaction_type != other.transaction_type) {
            return false;
        }
        if (!Objects.equals(this.date_created, other.date_created)) {
            return false;
        }
        if (!Objects.equals(this.author_id, other.author_id)) {
            return false;
        }
        return Objects.equals(this.parent_transaction_id, other.parent_transaction_id);
    }

    @Override
    public void validate() {
        validateMandatoryFields(this.getClass(), this);
    }

    @Override
    public String toString() {
        return "_LibraryTransaction{"
                + "id=" + id
                + ", inventory_id=" + inventory_id
                + ", borrower_id=" + borrower_id
                + ", borrower_type=" + borrower_type
                + ", expected_return_date=" + expected_return_date
                + ", actual_return_date=" + actual_return_date
                + ", transaction_type=" + transaction_type
                + ", date_created=" + date_created
                + ", author_id=" + author_id
                + ", comments=" + comments
                + ", parent_transaction_id=" + parent_transaction_id
                + "}";
    }

}
