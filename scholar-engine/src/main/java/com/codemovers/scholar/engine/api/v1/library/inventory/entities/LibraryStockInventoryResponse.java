/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codemovers.scholar.engine.api.v1.library.inventory.entities;

import com.codemovers.scholar.engine.helper.enums.StatusEnum;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author mover 12/20/2017
 */
public class LibraryStockInventoryResponse {

    private Integer id;
    private Integer stock_id;
    private Integer book_id;
    private String isb_number;
    private String serial_number;
    private Date date_created;
    private String author;
    private StatusEnum status;

    public LibraryStockInventoryResponse() {
    }

    public LibraryStockInventoryResponse(Integer id) {
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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 23 * hash + Objects.hashCode(this.id);
        hash = 23 * hash + Objects.hashCode(this.stock_id);
        hash = 23 * hash + Objects.hashCode(this.book_id);
        hash = 23 * hash + Objects.hashCode(this.isb_number);
        hash = 23 * hash + Objects.hashCode(this.serial_number);
        hash = 23 * hash + Objects.hashCode(this.date_created);
        hash = 23 * hash + Objects.hashCode(this.author);
        hash = 23 * hash + Objects.hashCode(this.status);
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
        final LibraryStockInventoryResponse other = (LibraryStockInventoryResponse) obj;
        if (!Objects.equals(this.isb_number, other.isb_number)) {
            return false;
        }
        if (!Objects.equals(this.serial_number, other.serial_number)) {
            return false;
        }
        if (!Objects.equals(this.author, other.author)) {
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
        return this.status == other.status;
    }

    @Override
    public String toString() {
        return "LibraryStockInventoryResponse{"
                + "id=" + id
                + ", stock_id=" + stock_id
                + ", book_id=" + book_id
                + ", isb_number=" + isb_number
                + ", serial_number=" + serial_number
                + ", date_created=" + date_created
                + ", author=" + author
                + ", status=" + status
                + "}";
    }

}
