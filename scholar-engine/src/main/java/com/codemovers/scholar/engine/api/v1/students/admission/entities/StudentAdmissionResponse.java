/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codemovers.scholar.engine.api.v1.students.admission.entities;

import com.codemovers.scholar.engine.api.v1.classes.entities.ClassResponse;
import com.codemovers.scholar.engine.api.v1.profile.entities.ProfileResponse;
import com.codemovers.scholar.engine.api.v1.terms.entities.TermResponse;
import com.codemovers.scholar.engine.helper.enums.StatusEnum;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author mover 12/30/2017
 */
public class StudentAdmissionResponse {

    private Integer id;
    private Integer student_id;
    private String admission_number;
    private Date date_of_admission;
    private String external_id;
    private TermResponse term_response;
    private ClassResponse class_response;
    private String status;
    private Date date_created;
    private String author;
    private ProfileResponse profileResponse;

    public StudentAdmissionResponse() {
    }

    public StudentAdmissionResponse(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStudent_id() {
        return student_id;
    }

    public void setStudent_id(Integer student_id) {
        this.student_id = student_id;
    }

    public String getAdmission_number() {
        return admission_number;
    }

    public void setAdmission_number(String admission_number) {
        this.admission_number = admission_number;
    }

    public Date getDate_of_admission() {
        return date_of_admission;
    }

    public void setDate_of_admission(Date date_of_admission) {
        this.date_of_admission = date_of_admission;
    }

    public String getExternal_id() {
        return external_id;
    }

    public void setExternal_id(String external_id) {
        this.external_id = external_id;
    }

    public TermResponse getTerm_response() {
        return term_response;
    }

    public void setTerm_response(TermResponse term_response) {
        this.term_response = term_response;
    }

    public ClassResponse getClass_response() {
        return class_response;
    }

    public void setClass_response(ClassResponse class_response) {
        this.class_response = class_response;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDate_created() {
        return date_created;
    }

    public void setDate_created(Date date_created) {
        this.date_created = date_created;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public ProfileResponse getProfileResponse() {
        return profileResponse;
    }

    public void setProfileResponse(ProfileResponse profileResponse) {
        this.profileResponse = profileResponse;
    }


    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + Objects.hashCode(this.id);
        hash = 89 * hash + Objects.hashCode(this.student_id);
        hash = 89 * hash + Objects.hashCode(this.admission_number);
        hash = 89 * hash + Objects.hashCode(this.date_of_admission);
        hash = 89 * hash + Objects.hashCode(this.external_id);
        hash = 89 * hash + Objects.hashCode(this.term_response);
        hash = 89 * hash + Objects.hashCode(this.class_response);
        hash = 89 * hash + Objects.hashCode(this.status);
        hash = 89 * hash + Objects.hashCode(this.date_created);
        hash = 89 * hash + Objects.hashCode(this.author);
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
        final StudentAdmissionResponse other = (StudentAdmissionResponse) obj;
        if (!Objects.equals(this.admission_number, other.admission_number)) {
            return false;
        }
        if (!Objects.equals(this.external_id, other.external_id)) {
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
        if (!Objects.equals(this.student_id, other.student_id)) {
            return false;
        }
        if (!Objects.equals(this.date_of_admission, other.date_of_admission)) {
            return false;
        }
        if (!Objects.equals(this.term_response, other.term_response)) {
            return false;
        }
        if (!Objects.equals(this.class_response, other.class_response)) {
            return false;
        }
        return Objects.equals(this.date_created, other.date_created);
    }

    @Override
    public String toString() {
        return "StudentAdmissionResponse{"
                + "id=" + id
                + ", student_id=" + student_id
                + ", admission_number=" + admission_number
                + ", date_of_admission=" + date_of_admission
                + ", external_id=" + external_id
                + ", term_response=" + term_response
                + ", class_response=" + class_response
                + ", status=" + status
                + ", date_created=" + date_created
                + ", author=" + author
                + "}";
    }



}
