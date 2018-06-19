/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codemovers.scholar.engine.api.v1.students.registration.entities;

import com.codemovers.scholar.engine.api.v1.classes.entities.ClassResponse;
import com.codemovers.scholar.engine.api.v1.streams.entities.StreamResponse;
import com.codemovers.scholar.engine.api.v1.students.admissions.entities.AdmissionResponse;
import com.codemovers.scholar.engine.api.v1.terms.entities.TermResponse;
import java.util.Objects;

/**
 *
 * @author mover
 */
public class TermRegistration {
    
    private Integer id;
    private AdmissionResponse admission;
    private TermResponse studentTerm;
    private ClassResponse  studentClass;
    private StreamResponse studentStream;
    private Long date_created;
    private String status;
    private String author;

    public TermRegistration() {
    }

    public TermRegistration(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public AdmissionResponse getAdmission() {
        return admission;
    }

    public void setAdmission(AdmissionResponse admission) {
        this.admission = admission;
    }

    public TermResponse getStudentTerm() {
        return studentTerm;
    }

    public void setStudentTerm(TermResponse studentTerm) {
        this.studentTerm = studentTerm;
    }

    public ClassResponse getStudentClass() {
        return studentClass;
    }

    public void setStudentClass(ClassResponse studentClass) {
        this.studentClass = studentClass;
    }

    public StreamResponse getStudentStream() {
        return studentStream;
    }

    public void setStudentStream(StreamResponse studentStream) {
        this.studentStream = studentStream;
    }

    public Long getDate_created() {
        return date_created;
    }

    public void setDate_created(Long date_created) {
        this.date_created = date_created;
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
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.id);
        hash = 97 * hash + Objects.hashCode(this.admission);
        hash = 97 * hash + Objects.hashCode(this.studentTerm);
        hash = 97 * hash + Objects.hashCode(this.studentClass);
        hash = 97 * hash + Objects.hashCode(this.studentStream);
        hash = 97 * hash + Objects.hashCode(this.date_created);
        hash = 97 * hash + Objects.hashCode(this.status);
        hash = 97 * hash + Objects.hashCode(this.author);
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
        final TermRegistration other = (TermRegistration) obj;
        if (!Objects.equals(this.status, other.status)) {
            return false;
        }
        if (!Objects.equals(this.author, other.author)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.admission, other.admission)) {
            return false;
        }
        if (!Objects.equals(this.studentTerm, other.studentTerm)) {
            return false;
        }
        if (!Objects.equals(this.studentClass, other.studentClass)) {
            return false;
        }
        if (!Objects.equals(this.studentStream, other.studentStream)) {
            return false;
        }
        return Objects.equals(this.date_created, other.date_created);
    }
    
    
    
}
