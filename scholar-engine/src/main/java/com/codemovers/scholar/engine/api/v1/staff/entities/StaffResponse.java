/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codemovers.scholar.engine.api.v1.staff.entities;

import java.util.Objects;

/**
 *
 * @author Manny
 */
public class StaffResponse {

    private Integer id;
    private String firstname;
    private String middlename;
    private String lastname;
    private String prefix;
    private Long dateofbirth;
    private String image;
    private String status;
    private Long date_created;
    private String author;

    public StaffResponse() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getMiddlename() {
        return middlename;
    }

    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public Long getDateofbirth() {
        return dateofbirth;
    }

    public void setDateofbirth(Long dateofbirth) {
        this.dateofbirth = dateofbirth;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getDate_created() {
        return date_created;
    }

    public void setDate_created(Long date_created) {
        this.date_created = date_created;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.id);
        hash = 97 * hash + Objects.hashCode(this.firstname);
        hash = 97 * hash + Objects.hashCode(this.middlename);
        hash = 97 * hash + Objects.hashCode(this.lastname);
        hash = 97 * hash + Objects.hashCode(this.prefix);
        hash = 97 * hash + Objects.hashCode(this.dateofbirth);
        hash = 97 * hash + Objects.hashCode(this.image);
        hash = 97 * hash + Objects.hashCode(this.status);
        hash = 97 * hash + Objects.hashCode(this.date_created);
        hash = 97 * hash + Objects.hashCode(this.author);
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
        final StaffResponse other = (StaffResponse) obj;
        if (!Objects.equals(this.firstname, other.firstname)) {
            return false;
        }
        if (!Objects.equals(this.middlename, other.middlename)) {
            return false;
        }
        if (!Objects.equals(this.lastname, other.lastname)) {
            return false;
        }
        if (!Objects.equals(this.prefix, other.prefix)) {
            return false;
        }
        if (!Objects.equals(this.image, other.image)) {
            return false;
        }
        if (!Objects.equals(this.status, other.status)) {
            return false;
        }
        if (!Objects.equals(this.author, other.author)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.dateofbirth, other.dateofbirth)) {
            return false;
        }
        return Objects.equals(this.date_created, other.date_created);
    }

    @Override
    public String toString() {
        return "StaffResponse{"
                + "id=" + id
                + ", firstname=" + firstname
                + ", middlename=" + middlename
                + ", lastname=" + lastname
                + ", prefix=" + prefix
                + ", dateofbirth=" + dateofbirth
                + ", image=" + image
                + ", status=" + status
                + ", date_created=" + date_created
                + ", author=" + author
                + '}';
    }


}
