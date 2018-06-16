/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codemovers.scholar.engine.api.v1.terms.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.Objects;

/**
 *
 * @author mover 12/20/2017
 */@JsonIgnoreProperties(ignoreUnknown = true)
public class TermResponse {

    private Integer id;
    private String study_year;
    private String name;
    private Long start_date;
    private Long end_date;
    private Integer ranking;
    private String status;
    private String author;
    private Long date_created;
    

    public TermResponse() {
    }

    public TermResponse(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStudy_year() {
        return study_year;
    }

    public void setStudy_year(String study_year) {
        this.study_year = study_year;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getStart_date() {
        return start_date;
    }

    public void setStart_date(Long start_date) {
        this.start_date = start_date;
    }

    public Long getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Long end_date) {
        this.end_date = end_date;
    }

    public Integer getRanking() {
        return ranking;
    }

    public void setRanking(Integer ranking) {
        this.ranking = ranking;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Long getDate_created() {
        return date_created;
    }

    public void setDate_created(Long date_created) {
        this.date_created = date_created;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 23 * hash + Objects.hashCode(this.id);
        hash = 23 * hash + Objects.hashCode(this.study_year);
        hash = 23 * hash + Objects.hashCode(this.name);
        hash = 23 * hash + Objects.hashCode(this.start_date);
        hash = 23 * hash + Objects.hashCode(this.end_date);
        hash = 23 * hash + Objects.hashCode(this.ranking);
        hash = 23 * hash + Objects.hashCode(this.status);
        hash = 23 * hash + Objects.hashCode(this.author);
        hash = 23 * hash + Objects.hashCode(this.date_created);
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
        final TermResponse other = (TermResponse) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.study_year, other.study_year)) {
            return false;
        }
        if (!Objects.equals(this.start_date, other.start_date)) {
            return false;
        }
        if (!Objects.equals(this.end_date, other.end_date)) {
            return false;
        }
        if (!Objects.equals(this.ranking, other.ranking)) {
            return false;
        }
        if (this.status != other.status) {
            return false;
        }
        if (!Objects.equals(this.author, other.author)) {
            return false;
        }
        return Objects.equals(this.date_created, other.date_created);
    }

    @Override
    public String toString() {
        return "TermResponse{"
                + "id=" + id
                + ", study_year=" + study_year
                + ", name=" + name
                + ", start_date=" + start_date
                + ", end_date=" + end_date
                + ", ranking=" + ranking
                + ", status=" + status
                + ", author=" + author
                + ", date_created=" + date_created
                + "}";
    }

}
