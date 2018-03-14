/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codemovers.scholar.engine.api.v1.classes.entities;

import com.codemovers.scholar.engine.api.v1.streams.entities.StreamResponse;
import com.codemovers.scholar.engine.helper.enums.StatusEnum;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.util.Arrays;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author mover
 */
public class ClassResponse {

    private Integer id;
    private String name;
    private String code;
    private Integer ranking;
    private StatusEnum status;
    private Date date_created;
    private String author;
    private StreamResponse[] streamResponses;

    public ClassResponse() {
    }

    public ClassResponse(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getRanking() {
        return ranking;
    }

    public void setRanking(Integer ranking) {
        this.ranking = ranking;
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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public StreamResponse[] getStreamResponses() {
        return streamResponses;
    }

    public void setStreamResponses(StreamResponse[] streamResponses) {
        this.streamResponses = streamResponses;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + Objects.hashCode(this.id);
        hash = 71 * hash + Objects.hashCode(this.name);
        hash = 71 * hash + Objects.hashCode(this.code);
        hash = 71 * hash + Objects.hashCode(this.ranking);
        hash = 71 * hash + Objects.hashCode(this.status);
        hash = 71 * hash + Objects.hashCode(this.date_created);
        hash = 71 * hash + Objects.hashCode(this.author);
        hash = 71 * hash + Arrays.deepHashCode(this.streamResponses);
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
        final ClassResponse other = (ClassResponse) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.code, other.code)) {
            return false;
        }
        if (!Objects.equals(this.author, other.author)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.ranking, other.ranking)) {
            return false;
        }
        if (this.status != other.status) {
            return false;
        }
        if (!Objects.equals(this.date_created, other.date_created)) {
            return false;
        }
        return Arrays.deepEquals(this.streamResponses, other.streamResponses);
    }

    @Override
    public String toString() {
        return "ClassResponse{"
                + "id=" + id
                + ", name=" + name
                + ", code=" + code
                + ", ranking=" + ranking
                + ", streams=" + Arrays.toString(streamResponses)
                + ", status=" + status
                + ", date_created=" + date_created
                + ", author=" + author
                + "}";
    }

}
