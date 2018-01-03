/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codemovers.scholar.engine.api.v1.students.termregistration.entities;

import com.codemovers.scholar.engine.api.v1.admissions.entities.AdmissionResponse;
import com.codemovers.scholar.engine.api.v1.classes.entities.ClassResponse;
import com.codemovers.scholar.engine.api.v1.streams.entities.StreamResponse;
import com.codemovers.scholar.engine.api.v1.students.admission.entities.StudentAdmissionResponse;
import com.codemovers.scholar.engine.api.v1.terms.entities.TermResponse;
import java.util.Objects;

/**
 *
 * @author Manny
 */
public class StudentTermRegistrationResponse {

    private Integer id;
    private StudentAdmissionResponse studentAdmission;
    private TermResponse registration_term;
    private ClassResponse registration_class;
    private StreamResponse registration_stream;
    private String status;
    private String author;

    public StudentTermRegistrationResponse() {
    }

    public StudentTermRegistrationResponse(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public StudentAdmissionResponse getStudentAdmission() {
        return studentAdmission;
    }

    public void setStudentAdmission(StudentAdmissionResponse studentAdmission) {
        this.studentAdmission = studentAdmission;
    }

    public TermResponse getRegistration_term() {
        return registration_term;
    }

    public void setRegistration_term(TermResponse registration_term) {
        this.registration_term = registration_term;
    }

    public ClassResponse getRegistration_class() {
        return registration_class;
    }

    public void setRegistration_class(ClassResponse registration_class) {
        this.registration_class = registration_class;
    }

    public StreamResponse getRegistration_stream() {
        return registration_stream;
    }

    public void setRegistration_stream(StreamResponse registration_stream) {
        this.registration_stream = registration_stream;
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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + Objects.hashCode(this.id);
        hash = 17 * hash + Objects.hashCode(this.studentAdmission);
        hash = 17 * hash + Objects.hashCode(this.registration_term);
        hash = 17 * hash + Objects.hashCode(this.registration_class);
        hash = 17 * hash + Objects.hashCode(this.registration_stream);
        hash = 17 * hash + Objects.hashCode(this.status);
        hash = 17 * hash + Objects.hashCode(this.author);
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
        final StudentTermRegistrationResponse other = (StudentTermRegistrationResponse) obj;
        if (!Objects.equals(this.status, other.status)) {
            return false;
        }
        if (!Objects.equals(this.author, other.author)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.studentAdmission, other.studentAdmission)) {
            return false;
        }
        if (!Objects.equals(this.registration_term, other.registration_term)) {
            return false;
        }
        if (!Objects.equals(this.registration_class, other.registration_class)) {
            return false;
        }
        return Objects.equals(this.registration_stream, other.registration_stream);
    }

    @Override
    public String toString() {
        return "StudentTermRegistrationResponse{"
                + "id=" + id
                + ", studentAdmission=" + studentAdmission
                + ", registration_term=" + registration_term
                + ", registration_class=" + registration_class
                + ", registration_stream=" + registration_stream
                + ", status=" + status
                + ", author=" + author
                + "}";
    }


}
