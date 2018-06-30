/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codemovers.scholar.engine.api.v1.students.registration.exams.entities;

import com.codemovers.scholar.engine.annotation.Mandatory;
import com.codemovers.scholar.engine.api.v1.abstracts.AbstractEntity;
import static com.codemovers.scholar.engine.helper.Utilities.validateMandatoryFields;
import com.codemovers.scholar.engine.helper.enums.StatusEnum;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author mover 1/2/2018
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class _StudentExamRegistration extends AbstractEntity {

    private Integer id;
    private @Mandatory
    Integer term_registration_id;
    private @Mandatory
    Integer exam_id;
    private StatusEnum status;
    private Date date_created;
    private Integer author_id;

    public _StudentExamRegistration() {
    }

    public _StudentExamRegistration(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTerm_registration_id() {
        return term_registration_id;
    }

    public void setTerm_registration_id(Integer term_registration_id) {
        this.term_registration_id = term_registration_id;
    }

    public Integer getExam_id() {
        return exam_id;
    }

    public void setExam_id(Integer exam_id) {
        this.exam_id = exam_id;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
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
        hash = 79 * hash + Objects.hashCode(this.id);
        hash = 79 * hash + Objects.hashCode(this.term_registration_id);
        hash = 79 * hash + Objects.hashCode(this.exam_id);
        hash = 79 * hash + Objects.hashCode(this.status);
        hash = 79 * hash + Objects.hashCode(this.date_created);
        hash = 79 * hash + Objects.hashCode(this.author_id);
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
        final _StudentExamRegistration other = (_StudentExamRegistration) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.term_registration_id, other.term_registration_id)) {
            return false;
        }
        if (!Objects.equals(this.exam_id, other.exam_id)) {
            return false;
        }
        if (this.status != other.status) {
            return false;
        }
        if (!Objects.equals(this.date_created, other.date_created)) {
            return false;
        }
        if (!Objects.equals(this.author_id, other.author_id)) {
            return false;
        }
        return true;
    }

    @Override
    public void validate() {
        validateMandatoryFields(this.getClass(), this);
    }

    @Override
    public String toString() {
        return "_StudentExamRegistration{"
                + "id=" + id
                + ", term_registration_id=" + term_registration_id
                + ", exam_id=" + exam_id
                + ", status=" + status
                + ", date_created=" + date_created
                + ", author_id=" + author_id
                + "}";
    }

}
