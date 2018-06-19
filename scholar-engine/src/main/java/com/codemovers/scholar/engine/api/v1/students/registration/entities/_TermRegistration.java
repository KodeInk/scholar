/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codemovers.scholar.engine.api.v1.students.registration.entities;

import java.util.Objects;

/**
 *
 * @author mover
 */
public class _TermRegistration {
   private Integer id;
   private Integer addmision_id;
   private Integer term_id;
   private Integer class_id;
   private Integer stream_id;
   private Long date_created;
   private String status;
   private Integer author_id;

    public _TermRegistration() {
    }

    public _TermRegistration(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAddmision_id() {
        return addmision_id;
    }

    public void setAddmision_id(Integer addmision_id) {
        this.addmision_id = addmision_id;
    }

    public Integer getTerm_id() {
        return term_id;
    }

    public void setTerm_id(Integer term_id) {
        this.term_id = term_id;
    }

    public Integer getClass_id() {
        return class_id;
    }

    public void setClass_id(Integer class_id) {
        this.class_id = class_id;
    }

    public Integer getStream_id() {
        return stream_id;
    }

    public void setStream_id(Integer stream_id) {
        this.stream_id = stream_id;
    }

    public Long getDate_created() {
        return date_created;
    }

    public void setDate_created(Long date_created) {
        this.date_created = date_created;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
        hash = 37 * hash + Objects.hashCode(this.id);
        hash = 37 * hash + Objects.hashCode(this.addmision_id);
        hash = 37 * hash + Objects.hashCode(this.term_id);
        hash = 37 * hash + Objects.hashCode(this.class_id);
        hash = 37 * hash + Objects.hashCode(this.stream_id);
        hash = 37 * hash + Objects.hashCode(this.date_created);
        hash = 37 * hash + Objects.hashCode(this.status);
        hash = 37 * hash + Objects.hashCode(this.author_id);
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
        final _TermRegistration other = (_TermRegistration) obj;
        if (!Objects.equals(this.status, other.status)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.addmision_id, other.addmision_id)) {
            return false;
        }
        if (!Objects.equals(this.term_id, other.term_id)) {
            return false;
        }
        if (!Objects.equals(this.class_id, other.class_id)) {
            return false;
        }
        if (!Objects.equals(this.stream_id, other.stream_id)) {
            return false;
        }
        if (!Objects.equals(this.date_created, other.date_created)) {
            return false;
        }
        return Objects.equals(this.author_id, other.author_id);
    }

    @Override
    public String toString() {
        return "TermRegistration{" 
                + "id=" + id 
                + ", addmision_id=" + addmision_id 
                + ", term_id=" + term_id 
                + ", class_id=" + class_id 
                + ", stream_id=" + stream_id 
                + ", date_created=" + date_created 
                + ", status=" + status 
                + ", author_id=" + author_id 
                + '}';
    }
   
   
}
