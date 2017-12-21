/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codemovers.scholar.engine.api.v1.library.stock.entities;

import java.util.Date;
import java.util.Objects;

/**
 *
 * @author mover 12/20/2017
 */
public class LibraryStockResponse {

    private Integer id;
    private String stock_number;
    private String stock_title;
    private Integer number_of_items;
    private Integer section_id;
    private Date date_created;
    private Integer author_id;

    public LibraryStockResponse() {
    }

    public LibraryStockResponse(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStock_number() {
        return stock_number;
    }

    public void setStock_number(String stock_number) {
        this.stock_number = stock_number;
    }

    public String getStock_title() {
        return stock_title;
    }

    public void setStock_title(String stock_title) {
        this.stock_title = stock_title;
    }

    public Integer getNumber_of_items() {
        return number_of_items;
    }

    public void setNumber_of_items(Integer number_of_items) {
        this.number_of_items = number_of_items;
    }

    public Integer getSection_id() {
        return section_id;
    }

    public void setSection_id(Integer section_id) {
        this.section_id = section_id;
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

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 71 * hash + Objects.hashCode(this.id);
        hash = 71 * hash + Objects.hashCode(this.stock_number);
        hash = 71 * hash + Objects.hashCode(this.stock_title);
        hash = 71 * hash + Objects.hashCode(this.number_of_items);
        hash = 71 * hash + Objects.hashCode(this.section_id);
        hash = 71 * hash + Objects.hashCode(this.date_created);
        hash = 71 * hash + Objects.hashCode(this.author_id);
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
        final LibraryStockResponse other = (LibraryStockResponse) obj;
        if (!Objects.equals(this.stock_number, other.stock_number)) {
            return false;
        }
        if (!Objects.equals(this.stock_title, other.stock_title)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.number_of_items, other.number_of_items)) {
            return false;
        }
        if (!Objects.equals(this.section_id, other.section_id)) {
            return false;
        }
        if (!Objects.equals(this.date_created, other.date_created)) {
            return false;
        }
        return Objects.equals(this.author_id, other.author_id);
    }

    @Override
    public String toString() {
        return "LibraryStockResponse{"
                + "id=" + id
                + ", stock_number=" + stock_number
                + ", stock_title=" + stock_title
                + ", number_of_items=" + number_of_items
                + ", section_id=" + section_id
                + ", date_created=" + date_created
                + ", author_id=" + author_id
                + "}";
    }



}
