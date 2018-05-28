/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codemovers.scholar.engine.api.v1.curriculum.entities;

import com.codemovers.scholar.engine.annotation.Mandatory;
import com.codemovers.scholar.engine.api.v1.abstracts.AbstractEntity;
import static com.codemovers.scholar.engine.helper.Utilities.validateMandatoryFields;
import com.codemovers.scholar.engine.helper.enums.StatusEnum;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author mover
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class _Curriculum extends AbstractEntity {

    private Integer id;
    private @Mandatory
    String name;
    private @Mandatory
    String code;
    private String description;
    private StatusEnum status;
    private Date date_created;
    private     Integer author_id;

    public _Curriculum() {
    }

    public _Curriculum(Integer id) {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
        hash = 53 * hash + Objects.hashCode(this.id);
        hash = 53 * hash + Objects.hashCode(this.name);
        hash = 53 * hash + Objects.hashCode(this.code);
        hash = 53 * hash + Objects.hashCode(this.description);
        hash = 53 * hash + Objects.hashCode(this.status);
        hash = 53 * hash + Objects.hashCode(this.date_created);
        hash = 53 * hash + Objects.hashCode(this.author_id);
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
        final _Curriculum other = (_Curriculum) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.code, other.code)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
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
        return "_Curriculum{"
                + "id=" + id
                + ", name=" + name
                + ", code=" + code
                + ", description=" + description
                + ", status=" + status
                + ", date_created=" + date_created
                + ", author_id=" + author_id
                + "}";
    }

}
