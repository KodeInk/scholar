/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codemovers.scholar.engine.api.v1.users;

import com.codemovers.scholar.engine.api.v1.users.entities.UserResponse;
import com.codemovers.scholar.engine.api.v1.users.entities._User;
import com.codemovers.scholar.engine.db.entities.SchoolData;
import com.codemovers.scholar.engine.db.entities.Users;

/**
 *
 * @author mover
 */
public interface UserServiceInterface {

    String convertToBasicAuth(String username, String Password);

    UserResponse create(SchoolData data, _User entity) throws Exception;

    UserResponse getById(Integer Id) throws Exception;

    //todo: retrieve authentication
    Users login(String username, String password, String logid) throws Exception;

    //todo: validate authenticaton
    boolean validateAuthentication(String Schoolid, String authentication) throws Exception;

}
