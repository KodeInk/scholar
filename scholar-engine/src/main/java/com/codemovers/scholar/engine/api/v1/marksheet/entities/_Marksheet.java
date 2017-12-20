/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codemovers.scholar.engine.api.v1.marksheet.entities;

import com.codemovers.scholar.engine.annotation.Mandatory;
import com.codemovers.scholar.engine.api.v1.abstracts.AbstractEntity;
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
public class _Marksheet extends AbstractEntity {

    private Integer id;
    private @Mandatory
    Integer student_id;
    private @Mandatory
    Integer subject_id;
    private Integer paper_id;
    private @Mandatory
    Integer class_id;
    private @Mandatory
    Integer exam_id;
    private StatusEnum status;
    private @Mandatory
    Integer mark;
    private String comment;
    private Integer author_id;
    private Date date_created;

    public _Marksheet() {
    }

    public _Marksheet(Integer id) {
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

    public Integer getSubject_id() {
        return subject_id;
    }

    public void setSubject_id(Integer subject_id) {
        this.subject_id = subject_id;
    }

    public Integer getPaper_id() {
        return paper_id;
    }

    public void setPaper_id(Integer paper_id) {
        this.paper_id = paper_id;
    }

    public Integer getClass_id() {
        return class_id;
    }

    public void setClass_id(Integer class_id) {
        this.class_id = class_id;
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

    public Integer getMark() {
        return mark;
    }

    public void setMark(Integer mark) {
        this.mark = mark;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(Integer author_id) {
        this.author_id = author_id;
    }

    public Date getDate_created() {
        return date_created;
    }

    public void setDate_created(Date date_created) {
        this.date_created = date_created;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 71 * hash + Objects.hashCode(this.id);
        hash = 71 * hash + Objects.hashCode(this.student_id);
        hash = 71 * hash + Objects.hashCode(this.subject_id);
        hash = 71 * hash + Objects.hashCode(this.paper_id);
        hash = 71 * hash + Objects.hashCode(this.class_id);
        hash = 71 * hash + Objects.hashCode(this.exam_id);
        hash = 71 * hash + Objects.hashCode(this.status);
        hash = 71 * hash + Objects.hashCode(this.mark);
        hash = 71 * hash + Objects.hashCode(this.comment);
        hash = 71 * hash + Objects.hashCode(this.author_id);
        hash = 71 * hash + Objects.hashCode(this.date_created);
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
        final _Marksheet other = (_Marksheet) obj;
        if (!Objects.equals(this.comment, other.comment)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.student_id, other.student_id)) {
            return false;
        }
        if (!Objects.equals(this.subject_id, other.subject_id)) {
            return false;
        }
        if (!Objects.equals(this.paper_id, other.paper_id)) {
            return false;
        }
        if (!Objects.equals(this.class_id, other.class_id)) {
            return false;
        }
        if (!Objects.equals(this.exam_id, other.exam_id)) {
            return false;
        }
        if (this.status != other.status) {
            return false;
        }
        if (!Objects.equals(this.mark, other.mark)) {
            return false;
        }
        if (!Objects.equals(this.author_id, other.author_id)) {
            return false;
        }
        return Objects.equals(this.date_created, other.date_created);
    }


    @Override
    public void validate() {
        validateMandatoryFields(this.getClass(), this);
    }

    @Override
    public String toString() {
        return "_Marksheet{"
                + "id=" + id
                + ", student_id=" + student_id
                + ", subject_id=" + subject_id
                + ", paper_id=" + paper_id
                + ", class_id=" + class_id
                + ", exam_id=" + exam_id
                + ", status=" + status
                + ", mark=" + mark
                + ", comment=" + comment
                + ", author_id=" + author_id
                + ", date_created=" + date_created
                + "}";
    }


}
