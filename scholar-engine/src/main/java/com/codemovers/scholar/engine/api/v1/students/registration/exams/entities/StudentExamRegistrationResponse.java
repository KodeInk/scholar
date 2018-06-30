/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codemovers.scholar.engine.api.v1.students.registration.exams.entities;

import com.codemovers.scholar.engine.api.v1.exams.entities.ExamResponse;
import com.codemovers.scholar.engine.api.v1.students.terms.entities.TermRegistrationResponse;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author Manny
 */
public class StudentExamRegistrationResponse {

    private Integer id;
    private TermRegistrationResponse term_registration;
    private ExamResponse exam;
    private String status;
    private Date date_created;
    private String author;

    public StudentExamRegistrationResponse() {
    }

    public StudentExamRegistrationResponse(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public TermRegistrationResponse getTerm_registration() {
        return term_registration;
    }

    public void setTerm_registration(TermRegistrationResponse term_registration) {
        this.term_registration = term_registration;
    }

    public ExamResponse getExam() {
        return exam;
    }

    public void setExam(ExamResponse exam) {
        this.exam = exam;
    }

    public String getStatus() {
        return status;
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

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + Objects.hashCode(this.id);
        hash = 53 * hash + Objects.hashCode(this.term_registration);
        hash = 53 * hash + Objects.hashCode(this.exam);
        hash = 53 * hash + Objects.hashCode(this.status);
        hash = 53 * hash + Objects.hashCode(this.date_created);
        hash = 53 * hash + Objects.hashCode(this.author);
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
        final StudentExamRegistrationResponse other = (StudentExamRegistrationResponse) obj;
        if (!Objects.equals(this.author, other.author)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.term_registration, other.term_registration)) {
            return false;
        }
        if (!Objects.equals(this.exam, other.exam)) {
            return false;
        }
        if (this.status == null ? other.status != null : !this.status.equals(other.status)) {
            return false;
        }
        return Objects.equals(this.date_created, other.date_created);
    }

    @Override
    public String toString() {
        return "StudentExamRegistrationResponse{"
                + "id=" + id
                + ", term_registration=" + term_registration
                + ", exam=" + exam
                + ", status=" + status
                + ", date_created=" + date_created
                + ", author=" + author
                + "}";
    }

}
