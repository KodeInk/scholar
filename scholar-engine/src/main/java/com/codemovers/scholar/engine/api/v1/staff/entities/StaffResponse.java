/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codemovers.scholar.engine.api.v1.staff.entities;

import com.codemovers.scholar.engine.annotation.Mandatory;
import com.codemovers.scholar.engine.api.v1.profile.entities.ProfileResponse;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author mover 3/16/2018
 */
public class StaffResponse {

    private Integer id;
    private ProfileResponse profile;
    private Long joinDate;
    private String status;
    private Long date_created;
    private String author;
    private Boolean isTeacher;

    public StaffResponse() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ProfileResponse getProfile() {
        return profile;
    }

    public void setProfile(ProfileResponse profile) {
        this.profile = profile;
    }

    public Long getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(Long joinDate) {
        this.joinDate = joinDate;
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

    public Boolean getIsTeacher() {
        return isTeacher;
    }

    public void setIsTeacher(Boolean isTeacher) {
        this.isTeacher = isTeacher;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.id);
        hash = 97 * hash + Objects.hashCode(this.profile);
        hash = 97 * hash + Objects.hashCode(this.joinDate);
        hash = 97 * hash + Objects.hashCode(this.status);
        hash = 97 * hash + Objects.hashCode(this.date_created);
        hash = 97 * hash + Objects.hashCode(this.author);
        hash = 97 * hash + Objects.hashCode(this.isTeacher);
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
        final StaffResponse other = (StaffResponse) obj;
        if (!Objects.equals(this.status, other.status)) {
            return false;
        }
        if (!Objects.equals(this.author, other.author)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.profile, other.profile)) {
            return false;
        }
        if (!Objects.equals(this.joinDate, other.joinDate)) {
            return false;
        }
        if (!Objects.equals(this.date_created, other.date_created)) {
            return false;
        }
        return Objects.equals(this.isTeacher, other.isTeacher);
    }

    @Override
    public String toString() {
        return "StaffResponse{"
                + "id=" + id
                + ", profile=" + profile
                + ", joinDate=" + joinDate
                + ", isTeacher=" + isTeacher
                + ", status=" + status
                + ", date_created=" + date_created
                + ", author=" + author
                + '}';
    }


}
