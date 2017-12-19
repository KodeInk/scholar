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
    private String[] Roles;
    private String accounttype;
    private String emailaddress;
    private String authentication;
    private Date dateCreated;

    public UserResponse() {
    }

    public UserResponse(Integer id) {
        this.id = id;
    }


}
