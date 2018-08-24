/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codemovers.scholar.engine.api.v1.studyear.entities;

import com.codemovers.scholar.engine.annotation.Mandatory;
import com.codemovers.scholar.engine.api.v1.abstracts.AbstractEntity;
import static com.codemovers.scholar.engine.helper.Utilities.validateMandatoryFields;
import com.codemovers.scholar.engine.helper.enums.StatusEnum;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author mover 12/20/2017
 */
public class StudyYears extends AbstractEntity {

    private Integer id;
    private @Mandatory
    String theme;
    private @Mandatory
    Long start_date;
    private @Mandatory
    Long end_date;
    private StatusEnum status;
    private Integer author_id;
    private Long date_created;
    private List<Integer> curricula;

    public StudyYears() {
    }

    public StudyYears(Integer id) {
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

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    public Integer getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(Integer author_id) {
        this.author_id = author_id;
    }

    public Long getDate_created() {
        return date_created;
    }

    public void setDate_created(Long date_created) {
        this.date_created = date_created;
    }

    public List<Integer> getCurricula() {
        return curricula;
    }

    public void setCurricula(List<Integer> curricula) {
        this.curricula = curricula;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 73 * hash + Objects.hashCode(this.id);
        hash = 73 * hash + Objects.hashCode(this.theme);
        hash = 73 * hash + Objects.hashCode(this.start_date);
        hash = 73 * hash + Objects.hashCode(this.end_date);
        hash = 73 * hash + Objects.hashCode(this.status);
        hash = 73 * hash + Objects.hashCode(this.author_id);
        hash = 73 * hash + Objects.hashCode(this.date_created);
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
        final StudyYears other = (StudyYears) obj;
        if (!Objects.equals(this.theme, other.theme)) {
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
        if (this.status != other.status) {
            return false;
        }
        if (!Objects.equals(this.author_id, other.author_id)) {
            return false;
        }
        return Objects.equals(this.date_created, other.date_created);
    }

    @Override
    public void validate() {
        validateMandatoryFields(this.getClass(), this);
    }

    @Override
    public String toString() {
        return "_StudyYear{"
                + "id=" + id
                + ", theme=" + theme
                + ", start_date=" + start_date
                + ", end_date=" + end_date
                + ", curricula=" + curricula
                + ", status=" + status
                + ", author_id=" + author_id
                + ", date_created=" + date_created
                + "}";
    }

}
