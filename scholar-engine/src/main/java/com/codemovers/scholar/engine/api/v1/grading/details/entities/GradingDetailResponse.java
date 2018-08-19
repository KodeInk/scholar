/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codemovers.scholar.engine.api.v1.grading.details.entities;

import com.codemovers.scholar.engine.helper.enums.StatusEnum;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author mover 12/20/2017
 */
public class GradingDetailResponse {

    private Integer id;
    private Integer grading_id;
    private Integer grading_scale;
    private String symbol;
    private Integer min_grade;
    private Integer max_grade;
    private String value;
    private StatusEnum status;
    private Date date_created;
    private Integer author_id;

    public GradingDetailResponse() {
    }

    public GradingDetailResponse(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGrading_id() {
        return grading_id;
    }

    public void setGrading_id(Integer grading_id) {
        this.grading_id = grading_id;
    }

    public Integer getGrading_scale() {
        return grading_scale;
    }

    public void setGrading_scale(Integer grading_scale) {
        this.grading_scale = grading_scale;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Integer getMin_grade() {
        return min_grade;
    }

    public void setMin_grade(Integer min_grade) {
        this.min_grade = min_grade;
    }

    public Integer getMax_grade() {
        return max_grade;
    }

    public void setMax_grade(Integer max_grade) {
        this.max_grade = max_grade;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
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
        int hash = 7;
        hash = 19 * hash + Objects.hashCode(this.id);
        hash = 19 * hash + Objects.hashCode(this.grading_id);
        hash = 19 * hash + Objects.hashCode(this.grading_scale);
        hash = 19 * hash + Objects.hashCode(this.symbol);
        hash = 19 * hash + Objects.hashCode(this.min_grade);
        hash = 19 * hash + Objects.hashCode(this.max_grade);
        hash = 19 * hash + Objects.hashCode(this.value);
        hash = 19 * hash + Objects.hashCode(this.status);
        hash = 19 * hash + Objects.hashCode(this.date_created);
        hash = 19 * hash + Objects.hashCode(this.author_id);
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
        final GradingDetailResponse other = (GradingDetailResponse) obj;
        if (!Objects.equals(this.symbol, other.symbol)) {
            return false;
        }
        if (!Objects.equals(this.value, other.value)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.grading_id, other.grading_id)) {
            return false;
        }
        if (!Objects.equals(this.grading_scale, other.grading_scale)) {
            return false;
        }
        if (!Objects.equals(this.min_grade, other.min_grade)) {
            return false;
        }
        if (!Objects.equals(this.max_grade, other.max_grade)) {
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
    public String toString() {
        return "GradingDetailResponse{"
                + "id=" + id
                + ", grading_id=" + grading_id
                + ", grading_scale=" + grading_scale
                + ", symbol=" + symbol
                + ", min_grade=" + min_grade
                + ", max_grade=" + max_grade
                + ", value=" + value
                + ", status=" + status
                + ", date_created=" + date_created
                + ", author_id=" + author_id
                + "}";
    }

}
