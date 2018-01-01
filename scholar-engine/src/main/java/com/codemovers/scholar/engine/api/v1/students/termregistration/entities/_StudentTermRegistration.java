/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codemovers.scholar.engine.api.v1.students.termregistration.entities;

import com.codemovers.scholar.engine.api.v1.abstracts.AbstractEntity;
import static com.codemovers.scholar.engine.helper.Utilities.validateMandatoryFields;
import com.codemovers.scholar.engine.helper.enums.StatusEnum;
import java.util.Objects;

/**
 *
 * @author mover 1/1/2028
 */
public class _StudentTermRegistration extends AbstractEntity {
    private Integer id;
    private Integer admission_id;
    private Integer term_id;
    private Integer stream_id;
    private StatusEnum status;
    private Integer author_id;

    public _StudentTermRegistration() {
    }

    public _StudentTermRegistration(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAdmission_id() {
        return admission_id;
    }

    public void setAdmission_id(Integer admission_id) {
        this.admission_id = admission_id;
    }

    public Integer getTerm_id() {
        return term_id;
    }

    public void setTerm_id(Integer term_id) {
        this.term_id = term_id;
    }

    public Integer getStream_id() {
        return stream_id;
    }

    public void setStream_id(Integer stream_id) {
        this.stream_id = stream_id;
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

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 11 * hash + Objects.hashCode(this.id);
        hash = 11 * hash + Objects.hashCode(this.admission_id);
        hash = 11 * hash + Objects.hashCode(this.term_id);
        hash = 11 * hash + Objects.hashCode(this.stream_id);
        hash = 11 * hash + Objects.hashCode(this.status);
        hash = 11 * hash + Objects.hashCode(this.author_id);
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
        final _StudentTermRegistration other = (_StudentTermRegistration) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.admission_id, other.admission_id)) {
            return false;
        }
        if (!Objects.equals(this.term_id, other.term_id)) {
            return false;
        }
        if (!Objects.equals(this.stream_id, other.stream_id)) {
            return false;
        }
        if (this.status != other.status) {
            return false;
        }
        return Objects.equals(this.author_id, other.author_id);
    }

    @Override
    public void validate() {
        validateMandatoryFields(this.getClass(), this);
    }


    @Override
    public String toString() {
        return "_StudentTermRegistration{"
                + "id=" + id
                + ", admission_id=" + admission_id
                + ", term_id=" + term_id
                + ", stream_id=" + stream_id
                + ", status=" + status
                + ", author_id=" + author_id
                + "}";
    }

}

