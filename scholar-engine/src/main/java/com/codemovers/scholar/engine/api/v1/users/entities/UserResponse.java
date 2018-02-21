/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codemovers.scholar.engine.api.v1.users.entities;

import com.codemovers.scholar.engine.api.v1.roles.entities.RoleResponse;
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
    private RoleResponse[] Roles;
    private String accounttype;
    private String emailaddress;
    private String authentication;
    private Date dateCreated;
    private ProfileResponse profileResponse;

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

    public RoleResponse[] getRoles() {
        return Roles;
    }

    public void setRoles(RoleResponse[] Roles) {
        this.Roles = Roles;
    }

    public String getAccounttype() {
        return accounttype;
    }

    public void setAccounttype(String accounttype) {
        this.accounttype = accounttype;
    }

    public String getEmailaddress() {
        return emailaddress;
    }

    public void setEmailaddress(String emailaddress) {
        this.emailaddress = emailaddress;
    }

    public String getAuthentication() {
        return authentication;
    }

    public void setAuthentication(String authentication) {
        this.authentication = authentication;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public ProfileResponse getProfileResponse() {
        return profileResponse;
    }

    public void setProfileResponse(ProfileResponse profileResponse) {
        this.profileResponse = profileResponse;
    }


    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + Objects.hashCode(this.id);
        hash = 53 * hash + Objects.hashCode(this.username);
        hash = 53 * hash + Objects.hashCode(this.status);
        hash = 53 * hash + Arrays.deepHashCode(this.Roles);
        hash = 53 * hash + Objects.hashCode(this.accounttype);
        hash = 53 * hash + Objects.hashCode(this.emailaddress);
        hash = 53 * hash + Objects.hashCode(this.authentication);
        hash = 53 * hash + Objects.hashCode(this.dateCreated);
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
        if (!Objects.equals(this.accounttype, other.accounttype)) {
            return false;
        }
        if (!Objects.equals(this.emailaddress, other.emailaddress)) {
            return false;
        }
        if (!Objects.equals(this.authentication, other.authentication)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Arrays.deepEquals(this.Roles, other.Roles)) {
            return false;
        }
        return Objects.equals(this.dateCreated, other.dateCreated);
    }

    @Override
    public String toString() {
        return "UserResponse{"
                + "id=" + id
                + ", username=" + username
                + ", status=" + status
                + ", Roles=" + Arrays.asList(Roles)
                + ", accounttype=" + accounttype
                + ", emailaddress=" + emailaddress
                + ", authentication=" + authentication
                + ", dateCreated=" + dateCreated
                + "}";
    }

}
