/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codemovers.scholar.engine.api.v1.staff.entities;

import com.codemovers.scholar.engine.annotation.Mandatory;
import com.codemovers.scholar.engine.api.v1.abstracts.AbstractEntity;
import com.codemovers.scholar.engine.api.v1.profile.entities._Profile;
import static com.codemovers.scholar.engine.helper.Utilities.validateMandatoryFields;
import com.codemovers.scholar.engine.helper.enums.StatusEnum;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author mover 3/16/2018
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class _Staff extends AbstractEntity {

    private Integer id;
    private @Mandatory
    _Profile profile;
    private @Mandatory
    Date joinDate;
    private StatusEnum status;
    private Long date_created;
    private Integer author_id;
    private @Mandatory
    Boolean isTeacher;

    public _Staff() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public _Profile getProfile() {
        return profile;
    }

    public void setProfile(_Profile profile) {
        this.profile = profile;
    }

    public Date getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(Date joinDate) {
        this.joinDate = joinDate;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
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

    public Boolean getIsTeacher() {
        return isTeacher;
    }

    public void setIsTeacher(Boolean isTeacher) {
        this.isTeacher = isTeacher;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.id);
        hash = 23 * hash + Objects.hashCode(this.profile);
        hash = 23 * hash + Objects.hashCode(this.joinDate);
        hash = 23 * hash + Objects.hashCode(this.status);
        hash = 23 * hash + Objects.hashCode(this.date_created);
        hash = 23 * hash + Objects.hashCode(this.author_id);
        hash = 23 * hash + Objects.hashCode(this.isTeacher);
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
        final _Staff other = (_Staff) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.profile, other.profile)) {
            return false;
        }
        if (!Objects.equals(this.joinDate, other.joinDate)) {
            return false;
        }
        if (this.status != other.status) {
            return false;
        }
        if (!Objects.equals(this.date_created, other.date_created)) {
            return false;
        }
        if (!Objects.equals(this.author_id, other.author_id)) {
            return false;
        }
        return Objects.equals(this.isTeacher, other.isTeacher);
    }



    @Override
    public void validate() {
        validateMandatoryFields(this.getClass(), this);
    }

    @Override
    public String toString() {
        return "_Staff{"
                + "id=" + id
                + ", profile=" + profile
                + ", joinDate=" + joinDate
                + ", isTeacher=" + isTeacher
                + ", status=" + status
                + ", date_created=" + date_created
                + ", author_id=" + author_id
                + '}';
    }

}
