/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codemovers.scholar.engine.api.v1.curriculum.curriculumdetails.entities;

import com.codemovers.scholar.engine.helper.enums.StatusEnum;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author mover 12/28/2017
 */
public class CurriculumDetailResponse {

    private Integer id;
    private Integer curriculum_id;
    private String name;
    private String code;
    private String description;
    private StatusEnum status;
    private String author;
    private Date date_created;

    public CurriculumDetailResponse() {
    }

    public CurriculumDetailResponse(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCurriculum_id() {
        return curriculum_id;
    }

    public void setCurriculum_id(Integer curriculum_id) {
        this.curriculum_id = curriculum_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getDate_created() {
        return date_created;
    }

    public void setDate_created(Date date_created) {
        this.date_created = date_created;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 43 * hash + Objects.hashCode(this.id);
        hash = 43 * hash + Objects.hashCode(this.curriculum_id);
        hash = 43 * hash + Objects.hashCode(this.name);
        hash = 43 * hash + Objects.hashCode(this.code);
        hash = 43 * hash + Objects.hashCode(this.description);
        hash = 43 * hash + Objects.hashCode(this.status);
        hash = 43 * hash + Objects.hashCode(this.author);
        hash = 43 * hash + Objects.hashCode(this.date_created);
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
        final CurriculumDetailResponse other = (CurriculumDetailResponse) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.code, other.code)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.curriculum_id, other.curriculum_id)) {
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
        return "CurriculumDetailResponse{"
                + "id=" + id
                + ", curriculum_id=" + curriculum_id
                + ", name=" + name
                + ", code=" + code
                + ", description=" + description
                + ", status=" + status
                + ", author=" + author
                + ", date_created=" + date_created
                + "}";
    }

}
