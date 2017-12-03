/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codemovers.scholar.engine.api.v1.users;

import com.codemovers.scholar.engine.api.v1.abstracts.AbstractService;
import com.codemovers.scholar.engine.api.v1.users.entities.UserResponse;
import com.codemovers.scholar.engine.api.v1.users.entities._User;
import com.codemovers.scholar.engine.db.controllers.UsersJpaController;
import com.codemovers.scholar.engine.db.entities.Users;
import java.util.logging.Logger;
import org.sonatype.plexus.components.cipher.Base64;

/**
 *
 * @author MOver 11/19/2017
 */
public class UserService extends AbstractService<_User, UserResponse> {

    private static final Logger LOG = Logger.getLogger(UserService.class.getName());

    private final UsersJpaController controller;

    private static UserService service = null;

    public UserService() {
        controller = UsersJpaController.getInstance();
    }

    public static UserService getInstance() {
        if (service == null) {
            service = new UserService();
        }
        return service;
    }

    @Override
    public UserResponse create(_User entity) throws Exception {
        return null;
    }


    private UserResponse populateResponse(Users users) throws Exception {
        return null;
    }

    //todo: retrieve authentication 
    public Users login(String username, String password, String logid) throws Exception {

        return null;

    }

    @Override
    public UserResponse getById(Integer Id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String convertToBasicAuth(String username, String Password) {
        String authString = username + ":" + Password;
        byte[] authEncBytes = Base64.encodeBase64(authString.getBytes());
        String authStringEnc = new String(authEncBytes);
        return ("Basic:" + authStringEnc);
        //  String possibleAuthenticationKey = "Basic " + Base64.getEncoder().encodeToString(usernamePassowrd.trim().getBytes());

    }

    //todo: validate authenticaton
    public boolean validateAuthentication(String authentication) throws Exception {


        return true;
    }

}
