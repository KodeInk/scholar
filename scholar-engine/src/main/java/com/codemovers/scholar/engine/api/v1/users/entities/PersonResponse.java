/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codemovers.scholar.engine.api.v1.users.entities;

import java.awt.Image;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author Mover 11/22/2017
 */
public class PersonResponse {

    private String first_name;
    private String middle_name;
    private String last_name;
    private String prefix;
    private Date date_of_birth;
    private Image image;
    private String status;
    private Date date_created;
    private String author;

    public PersonResponse() {
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getMiddle_name() {
        return middle_name;
    }

    public void setMiddle_name(String middle_name) {
        this.middle_name = middle_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public Date getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(Date date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + Objects.hashCode(this.first_name);
        hash = 41 * hash + Objects.hashCode(this.middle_name);
        hash = 41 * hash + Objects.hashCode(this.last_name);
        hash = 41 * hash + Objects.hashCode(this.prefix);
        hash = 41 * hash + Objects.hashCode(this.date_of_birth);
        hash = 41 * hash + Objects.hashCode(this.image);
        hash = 41 * hash + Objects.hashCode(this.status);
        hash = 41 * hash + Objects.hashCode(this.date_created);
        hash = 41 * hash + Objects.hashCode(this.author);
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
        final PersonResponse other = (PersonResponse) obj;
        if (!Objects.equals(this.first_name, other.first_name)) {
            return false;
        }
        if (!Objects.equals(this.middle_name, other.middle_name)) {
            return false;
        }
        if (!Objects.equals(this.last_name, other.last_name)) {
            return false;
        }
        if (!Objects.equals(this.prefix, other.prefix)) {
            return false;
        }
        if (!Objects.equals(this.status, other.status)) {
            return false;
        }
        if (!Objects.equals(this.author, other.author)) {
            return false;
        }
        if (!Objects.equals(this.date_of_birth, other.date_of_birth)) {
            return false;
        }
        if (!Objects.equals(this.image, other.image)) {
            return false;
        }
        return Objects.equals(this.date_created, other.date_created);
    }

    @Override
    public String toString() {
        return "PersonResponse{"
                + "first_name=" + first_name
                + ", middle_name=" + middle_name
                + ", last_name=" + last_name
                + ", prefix=" + prefix
                + ", date_of_birth=" + date_of_birth
                + ", image=" + image
                + ", status=" + status
                + ", date_created=" + date_created
                + ", author=" + author
                + '}';
    }


}
