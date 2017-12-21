/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codemovers.scholar.engine.api.v1.curriculum.entities;

import com.codemovers.scholar.engine.helper.enums.StatusEnum;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author mover 12/20/2017
 */
public class CurriculumDetailResponse {

    private Integer id;
    private Integer curriculum_id;
    private String name;
    private String code;
    private String description;
    private StatusEnum status;
    private Integer author_id;
    private Date date_cteated;

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

    public Integer getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(Integer author_id) {
        this.author_id = author_id;
    }

    public Date getDate_cteated() {
        return date_cteated;
    }

    public void setDate_cteated(Date date_cteated) {
        this.date_cteated = date_cteated;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 11 * hash + Objects.hashCode(this.id);
        hash = 11 * hash + Objects.hashCode(this.curriculum_id);
        hash = 11 * hash + Objects.hashCode(this.name);
        hash = 11 * hash + Objects.hashCode(this.code);
        hash = 11 * hash + Objects.hashCode(this.description);
        hash = 11 * hash + Objects.hashCode(this.status);
        hash = 11 * hash + Objects.hashCode(this.author_id);
        hash = 11 * hash + Objects.hashCode(this.date_cteated);
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
        if (!Objects.equals(this.author_id, other.author_id)) {
            return false;
        }
        return Objects.equals(this.date_cteated, other.date_cteated);
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
                + ", author_id=" + author_id
                + ", date_cteated=" + date_cteated
                + "}";
    }

}
