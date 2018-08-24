/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codemovers.scholar.engine.api.v1.studyear.entities;

import com.codemovers.scholar.engine.api.v1.curriculum.entities.CurriculumResponse;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author mover 12/20/2017
 */
public class StudyYearResponse {

    private Integer id;
    private String theme;
    private Long start_date;
    private Long end_date;
    private List<CurriculumResponse> curricula;
    private String status;
    private String author;
    private Long date_created;

    public StudyYearResponse() {
    }

    public StudyYearResponse(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
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

    public List<CurriculumResponse> getCurricula() {
        return curricula;
    }

    public void setCurricula(List<CurriculumResponse> curricula) {
        this.curricula = curricula;
    }

    

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.id);
        hash = 97 * hash + Objects.hashCode(this.theme);
        hash = 97 * hash + Objects.hashCode(this.start_date);
        hash = 97 * hash + Objects.hashCode(this.end_date);
        hash = 97 * hash + Objects.hashCode(this.curricula);
        hash = 97 * hash + Objects.hashCode(this.status);
        hash = 97 * hash + Objects.hashCode(this.author);
        hash = 97 * hash + Objects.hashCode(this.date_created);
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
        final StudyYearResponse other = (StudyYearResponse) obj;
        if (!Objects.equals(this.theme, other.theme)) {
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
        if (!Objects.equals(this.start_date, other.start_date)) {
            return false;
        }
        if (!Objects.equals(this.end_date, other.end_date)) {
            return false;
        }
        if (!Objects.equals(this.curricula, other.curricula)) {
            return false;
        }
        return Objects.equals(this.date_created, other.date_created);
    }
    
    

    @Override
    public String toString() {
        return "StudyYearResponse{"
                + "id=" + id
                + ", theme=" + theme
                + ", start_date=" + start_date
                + ", end_date=" + end_date
                + ", status=" + status
                + ", author_id=" + author
                + ", date_created=" + date_created
                + "}";
    }

}
