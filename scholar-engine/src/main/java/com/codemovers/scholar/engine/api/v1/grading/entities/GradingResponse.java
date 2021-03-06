/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codemovers.scholar.engine.api.v1.grading.entities;

import com.codemovers.scholar.engine.api.v1.grading.details.entities.GradingDetailResponse;
import com.codemovers.scholar.engine.api.v1.subjects.entities.SubjectResponse;
import com.codemovers.scholar.engine.helper.enums.StatusEnum;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author mover 12/20/2107
 */
public class GradingResponse {

    private Integer id;
    private String name;
    private String code;
    private String description;
    private StatusEnum status;
    private Long dateCreated;
    private String author;
    private List<GradingDetailResponse> gradingDetailResponses;
    private List<SubjectResponse> subjectResponses;

    public GradingResponse() {
    }

    public GradingResponse(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Long getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Long dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public List<GradingDetailResponse> getGradingDetailResponses() {
        return gradingDetailResponses;
    }

    public void setGradingDetailResponses(List<GradingDetailResponse> gradingDetailResponses) {
        this.gradingDetailResponses = gradingDetailResponses;
    }

    public List<SubjectResponse> getSubjectResponses() {
        return subjectResponses;
    }

    public void setSubjectResponses(List<SubjectResponse> subjectResponses) {
        this.subjectResponses = subjectResponses;
    }
 
    
    

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 71 * hash + Objects.hashCode(this.id);
        hash = 71 * hash + Objects.hashCode(this.name);
        hash = 71 * hash + Objects.hashCode(this.code);
        hash = 71 * hash + Objects.hashCode(this.description);
        hash = 71 * hash + Objects.hashCode(this.status);
        hash = 71 * hash + Objects.hashCode(this.dateCreated);
        hash = 71 * hash + Objects.hashCode(this.author);
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
        final GradingResponse other = (GradingResponse) obj;
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
        if (this.status != other.status) {
            return false;
        }
        if (!Objects.equals(this.dateCreated, other.dateCreated)) {
            return false;
        }
        return Objects.equals(this.author, other.author);
    }

    @Override
    public String toString() {
        return "GradingResponse{"
                + "id=" + id 
                + ", name=" + name 
                + ", code=" + code 
                + ", description=" + description 
                + ", status=" + status 
                + ", dateCreated=" + dateCreated
                + ", author=" + author
                + ", gradingDetailResponses=" + gradingDetailResponses 
                + '}';
    }


    
    
}
