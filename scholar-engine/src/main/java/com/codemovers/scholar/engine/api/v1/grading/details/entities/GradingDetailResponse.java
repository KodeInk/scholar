/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codemovers.scholar.engine.api.v1.grading.details.entities;

import com.codemovers.scholar.engine.api.v1.grading.entities.GradingResponse;
import java.util.Objects;

/**
 *
 * @author mover 12/20/2017
 */
public class GradingDetailResponse {

    private Integer id;
    private GradingResponse gradingScale;
    private String symbol;
    private Long min_grade;
    private Long max_grade;    
    private String status;
    private Long date_created;
    private String author;

    public GradingDetailResponse() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public GradingResponse getGradingScale() {
        return gradingScale;
    }

    public void setGradingScale(GradingResponse gradingScale) {
        this.gradingScale = gradingScale;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Long getMin_grade() {
        return min_grade;
    }

    public void setMin_grade(Long min_grade) {
        this.min_grade = min_grade;
    }

    public Long getMax_grade() {
        return max_grade;
    }

    public void setMax_grade(Long max_grade) {
        this.max_grade = max_grade;
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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.id);
        hash = 59 * hash + Objects.hashCode(this.gradingScale);
        hash = 59 * hash + Objects.hashCode(this.symbol);
        hash = 59 * hash + Objects.hashCode(this.min_grade);
        hash = 59 * hash + Objects.hashCode(this.max_grade);       
        hash = 59 * hash + Objects.hashCode(this.status);
        hash = 59 * hash + Objects.hashCode(this.date_created);
        hash = 59 * hash + Objects.hashCode(this.author);
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
        
        if (!Objects.equals(this.status, other.status)) {
            return false;
        }
        if (!Objects.equals(this.author, other.author)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.gradingScale, other.gradingScale)) {
            return false;
        }
        if (!Objects.equals(this.min_grade, other.min_grade)) {
            return false;
        }
        if (!Objects.equals(this.max_grade, other.max_grade)) {
            return false;
        }
        if (!Objects.equals(this.date_created, other.date_created)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "GradingDetailResponse"
                + "{"
                + "id=" + id
                + ", gradingScale=" + gradingScale
                + ", symbol=" + symbol
                + ", min_grade=" + min_grade
                + ", max_grade=" + max_grade               
                + ", status=" + status
                + ", date_created=" + date_created
                + ", author=" + author
                + '}';
    }

}
