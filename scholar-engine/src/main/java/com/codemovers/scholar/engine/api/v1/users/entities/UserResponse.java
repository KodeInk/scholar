/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codemovers.scholar.engine.api.v1.users.entities;

import java.util.Arrays;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author Mover 11/22/2017
 */
public class UserResponse {

    private Integer id;
    private String username;
    private String status;
    private Date dateCreated;
    private String[] Roles;

    public UserResponse() {
    }

    public UserResponse(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String[] getRoles() {
        return Roles;
    }

    public void setRoles(String[] Roles) {
        this.Roles = Roles;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.id);
        hash = 59 * hash + Objects.hashCode(this.username);
        hash = 59 * hash + Objects.hashCode(this.status);
        hash = 59 * hash + Objects.hashCode(this.dateCreated);
        hash = 59 * hash + Arrays.deepHashCode(this.Roles);
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
        final UserResponse other = (UserResponse) obj;
        if (!Objects.equals(this.username, other.username)) {
            return false;
        }
        if (!Objects.equals(this.status, other.status)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.dateCreated, other.dateCreated)) {
            return false;
        }
        return Arrays.deepEquals(this.Roles, other.Roles);
    }

    @Override
    public String toString() {
        return "UserResponse{"
                + "id=" + id
                + ", username=" + username
                + ", status=" + status
                + ", dateCreated="
                + dateCreated
                + ", Roles=" + Roles
                + "}";
    }


}
