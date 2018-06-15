/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codemovers.scholar.engine.api.v1.admissions.entities;

import com.codemovers.scholar.engine.api.v1.classes.entities.ClassResponse;
import com.codemovers.scholar.engine.api.v1.profile.entities.ProfileResponse;
import com.codemovers.scholar.engine.api.v1.streams.entities.StreamResponse;
import com.codemovers.scholar.engine.api.v1.terms.entities.TermResponse;
import java.util.Objects;

/**
 *
 * @author mover 12/20/2017
 */
public class AdmissionResponse {

    private Integer id;
    private ProfileResponse student;
    private String admission_no;
    private String external_id;
    private Long date_of_admission;
    private TermResponse admissionTerm;
    private ClassResponse admissionClass;
    private StreamResponse admissionStream;
    private String status;
    private Long date_created;
    private Integer author_id;

    public AdmissionResponse() {
    }

    public AdmissionResponse(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ProfileResponse getStudent() {
        return student;
    }

    public void setStudent(ProfileResponse student) {
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

    public TermResponse getAdmissionTerm() {
        return admissionTerm;
    }

    public void setAdmissionTerm(TermResponse admissionTerm) {
        this.admissionTerm = admissionTerm;
    }

    public ClassResponse getAdmissionClass() {
        return admissionClass;
    }

    public void setAdmissionClass(ClassResponse admissionClass) {
        this.admissionClass = admissionClass;
    }

    public StreamResponse getAdmissionStream() {
        return admissionStream;
    }

    public void setAdmissionStream(StreamResponse admissionStream) {
        this.admissionStream = admissionStream;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
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
        int hash = 5;
        hash = 71 * hash + Objects.hashCode(this.id);
        hash = 71 * hash + Objects.hashCode(this.student);
        hash = 71 * hash + Objects.hashCode(this.admission_no);
        hash = 71 * hash + Objects.hashCode(this.external_id);
        hash = 71 * hash + Objects.hashCode(this.date_of_admission);
        hash = 71 * hash + Objects.hashCode(this.admissionTerm);
        hash = 71 * hash + Objects.hashCode(this.admissionClass);
        hash = 71 * hash + Objects.hashCode(this.admissionStream);
        hash = 71 * hash + Objects.hashCode(this.status);
        hash = 71 * hash + Objects.hashCode(this.date_created);
        hash = 71 * hash + Objects.hashCode(this.author_id);
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
        final AdmissionResponse other = (AdmissionResponse) obj;
        if (!Objects.equals(this.admission_no, other.admission_no)) {
            return false;
        }
        if (!Objects.equals(this.external_id, other.external_id)) {
            return false;
        }
        if (!Objects.equals(this.status, other.status)) {
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
        if (!Objects.equals(this.admissionTerm, other.admissionTerm)) {
            return false;
        }
        if (!Objects.equals(this.admissionClass, other.admissionClass)) {
            return false;
        }
        if (!Objects.equals(this.admissionStream, other.admissionStream)) {
            return false;
        }
        if (!Objects.equals(this.date_created, other.date_created)) {
            return false;
        }
        return Objects.equals(this.author_id, other.author_id);
    }

    @Override
    public String toString() {
        return "AdmissionResponse{"
                + "id=" + id 
                + ", student=" + student 
                + ", admission_no=" + admission_no
                + ", external_id=" + external_id 
                + ", date_of_admission=" + date_of_admission 
                + ", admissionTerm=" + admissionTerm
                + ", admissionClass=" + admissionClass 
                + ", admissionStream=" + admissionStream 
                + ", status=" + status 
                + ", date_created=" + date_created 
                + ", author_id=" + author_id 
                + '}';
    }

    

}
