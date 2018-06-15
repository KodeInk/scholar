/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codemovers.scholar.engine.api.v1.admissions.entities;

import com.codemovers.scholar.engine.annotation.Mandatory;
import com.codemovers.scholar.engine.api.v1.abstracts.AbstractEntity;
import com.codemovers.scholar.engine.api.v1.profile.entities._Profile;
import static com.codemovers.scholar.engine.helper.Utilities.validateMandatoryFields;
import com.codemovers.scholar.engine.helper.enums.StatusEnum;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author mover 12/20/2017
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class _Admission extends AbstractEntity {

    private Integer id;
    private @Mandatory
    _Profile student;
    private @Mandatory
    String admission_no;
    private String external_id;
    private @Mandatory
    Long date_of_admission;
    private @Mandatory
    Integer term_id;
    private @Mandatory
    Integer class_id;
    private Integer stream_id;
    private StatusEnum status;
    private Long date_created;
    private Integer author_id;

    public _Admission() {
    }

    public _Admission(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public _Profile getStudent() {
        return student;
    }

    public void setStudent(_Profile student) {
        this.student = student;
    }

    public String getAdmission_no() {
        return admission_no;
    }

    public void setAdmission_no(String admission_no) {
        this.admission_no = admission_no;
    }

    public String getExternal_id() {
        return external_id;
    }

    public void setExternal_id(String external_id) {
        this.external_id = external_id;
    }

    public Long getDate_of_admission() {
        return date_of_admission;
    }

    public void setDate_of_admission(Long date_of_admission) {
        this.date_of_admission = date_of_admission;
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

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    public Long getDate_created() {
        return date_created;
    }

    public void setDate_created(Long date_created) {
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
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.id);
        hash = 79 * hash + Objects.hashCode(this.student);
        hash = 79 * hash + Objects.hashCode(this.admission_no);
        hash = 79 * hash + Objects.hashCode(this.external_id);
        hash = 79 * hash + Objects.hashCode(this.date_of_admission);
        hash = 79 * hash + Objects.hashCode(this.term_id);
        hash = 79 * hash + Objects.hashCode(this.class_id);
        hash = 79 * hash + Objects.hashCode(this.stream_id);
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
        final _Admission other = (_Admission) obj;
        if (!Objects.equals(this.admission_no, other.admission_no)) {
            return false;
        }
        if (!Objects.equals(this.external_id, other.external_id)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.student, other.student)) {
            return false;
        }
        if (!Objects.equals(this.date_of_admission, other.date_of_admission)) {
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
        if (this.status != other.status) {
            return false;
        }
        if (!Objects.equals(this.date_created, other.date_created)) {
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
        return "_Admission{"
                + "id=" + id
                + ", student=" + student
                + ", admission_no=" + admission_no
                + ", external_id=" + external_id
                + ", date_of_admission=" + date_of_admission
                + ", term_id=" + term_id
                + ", class_id=" + class_id
                + ", stream_id=" + stream_id
                + ", status=" + status
                + ", date_created=" + date_created
                + ", author_id=" + author_id
                + "}";
    }

}
