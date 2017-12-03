/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codemovers.scholar.engine.api.v1.users.entities;

import java.util.Date;

/**
 *
 * @author Mover 11/22/2017
 */
public class UserResponse {

    private Integer id;
    private String username;
    private String status;
    private PersonResponse personResponse;
    private Integer accountId;
    private Date dateCreated;

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

    public PersonResponse getPersonResponse() {
        return personResponse;
    }

    public void setPersonResponse(PersonResponse personResponse) {
        this.personResponse = personResponse;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    @Override
    public String toString() {
        return "UserResponse{"
                + "id=" + id
                + ", username=" + username
                + ", status=" + status
                + ", personResponse=" + personResponse
                + ", accountId=" + accountId
                + ", dateCreated=" + dateCreated
                + "}";
    }

}
