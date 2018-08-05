/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codemovers.scholar.engine.api.v1.subjects.entities;

import com.codemovers.scholar.engine.annotation.Mandatory;
import com.codemovers.scholar.engine.api.v1.abstracts.AbstractEntity;
import static com.codemovers.scholar.engine.helper.Utilities.validateMandatoryFields;
import com.codemovers.scholar.engine.helper.enums.StatusEnum;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author mover 12/20/2017
 */
public class Subject extends AbstractEntity {

    private Integer id;
    private @Mandatory
    String name;
    private @Mandatory
    String code;
    private StatusEnum status;
    private @Mandatory
    SubjectTypeEnum type;
    private Date date_created;
    private Integer author_id;

    public Subject() {
    }

    public Subject(Integer id) {
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

    public SubjectTypeEnum getType() {
        return type;
    }

    public void setType(SubjectTypeEnum type) {
        this.type = type;
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
        int hash = 5;
        hash = 29 * hash + Objects.hashCode(this.id);
        hash = 29 * hash + Objects.hashCode(this.name);
        hash = 29 * hash + Objects.hashCode(this.code);
        hash = 29 * hash + Objects.hashCode(this.status);
        hash = 29 * hash + Objects.hashCode(this.date_created);
        hash = 29 * hash + Objects.hashCode(this.author_id);
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
        final Subject other = (Subject) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.code, other.code)) {
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
        return "_Subject{"
                + "id=" + id
                + ", name=" + name
                + ", code=" + code
                + ", category=" + type.name()
                + ", status=" + status
                + ", date_created=" + date_created
                + ", author_id=" + author_id
                + "}";
    }

}
