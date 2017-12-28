/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codemovers.scholar.engine.api.v1.library.inventory.entities;

import com.codemovers.scholar.engine.api.v1.abstracts.AbstractEntity;
import static com.codemovers.scholar.engine.helper.Utilities.validateMandatoryFields;
import com.codemovers.scholar.engine.helper.enums.StatusEnum;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author mover 12/20/2017
 */
public class _LibraryStockInventory extends AbstractEntity {

    private Integer id;
    private Integer stock_id;
    private Integer book_id;
    private String isb_number;
    private String serial_number;
    private Date date_created;
    private Integer author_id;
    private StatusEnum status;

    public _LibraryStockInventory() {
    }

    public _LibraryStockInventory(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStock_id() {
        return stock_id;
    }

    public void setStock_id(Integer stock_id) {
        this.stock_id = stock_id;
    }

    public Integer getBook_id() {
        return book_id;
    }

    public void setBook_id(Integer book_id) {
        this.book_id = book_id;
    }

    public String getIsb_number() {
        return isb_number;
    }

    public void setIsb_number(String isb_number) {
        this.isb_number = isb_number;
    }

    public String getSerial_number() {
        return serial_number;
    }

    public void setSerial_number(String serial_number) {
        this.serial_number = serial_number;
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

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + Objects.hashCode(this.id);
        hash = 31 * hash + Objects.hashCode(this.stock_id);
        hash = 31 * hash + Objects.hashCode(this.book_id);
        hash = 31 * hash + Objects.hashCode(this.isb_number);
        hash = 31 * hash + Objects.hashCode(this.serial_number);
        hash = 31 * hash + Objects.hashCode(this.date_created);
        hash = 31 * hash + Objects.hashCode(this.author_id);
        hash = 31 * hash + Objects.hashCode(this.status);
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
        final _LibraryStockInventory other = (_LibraryStockInventory) obj;
        if (!Objects.equals(this.isb_number, other.isb_number)) {
            return false;
        }
        if (!Objects.equals(this.serial_number, other.serial_number)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.stock_id, other.stock_id)) {
            return false;
        }
        if (!Objects.equals(this.book_id, other.book_id)) {
            return false;
        }
        if (!Objects.equals(this.date_created, other.date_created)) {
            return false;
        }
        if (!Objects.equals(this.author_id, other.author_id)) {
            return false;
        }
        return this.status == other.status;
    }

    @Override
    public void validate() {
        validateMandatoryFields(this.getClass(), this);
    }

    @Override
    public String toString() {
        return "_LibraryStockInventory{"
                + "id=" + id
                + ", stock_id=" + stock_id
                + ", book_id=" + book_id
                + ", isb_number=" + isb_number
                + ", serial_number=" + serial_number
                + ", date_created=" + date_created
                + ", author_id=" + author_id
                + ", status=" + status
                + "}";
    }

}
