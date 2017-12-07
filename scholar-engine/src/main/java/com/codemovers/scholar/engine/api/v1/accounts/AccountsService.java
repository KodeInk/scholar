/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codemovers.scholar.engine.api.v1.accounts;

import com.codemovers.scholar.engine.api.v1.abstracts.AbstractService;
import com.codemovers.scholar.engine.api.v1.accounts.entities.AuthenticationResponse;
import com.codemovers.scholar.engine.api.v1.accounts.entities.PermissionsResponse;
import com.codemovers.scholar.engine.api.v1.accounts.entities._Account;
import com.codemovers.scholar.engine.api.v1.accounts.entities._login;
import com.codemovers.scholar.engine.api.v1.users.UserService;
import com.codemovers.scholar.engine.api.v1.users.entities.UserResponse;
import com.codemovers.scholar.engine.api.v1.users.entities._User;
import com.codemovers.scholar.engine.db.entities.Roles;
import com.codemovers.scholar.engine.db.entities.Users;
import static com.codemovers.scholar.engine.helper.Utilities.getNewExternalId;
import com.codemovers.scholar.engine.helper.enums.AccountType;
import com.codemovers.scholar.engine.helper.enums.ContactTypes;
import com.codemovers.scholar.engine.helper.enums.ParentTypes;
import com.codemovers.scholar.engine.helper.enums.StatusEnum;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.BadRequestException;

/**
 *
 * @author mover 11/18/2017
 */
public class AccountsService {

    private static final Logger LOG = Logger.getLogger(AccountsService.class.getName());

    private static AccountsService service = null;

    public AccountsService() {

    }

    public static AccountsService getInstance() {
        if (service == null) {
            service = new AccountsService();
        }
        return service;
    }




    public AuthenticationResponse login(String school_name, _login login, String logId) throws Exception {

        LOG.log(Level.INFO, "School Name {0} ", school_name);
        /*   try {
        LOG.log(Level.INFO, " General Account Service Login ");
        String authentication = null;

        boolean status = login.validate();

        if (status == false) {
            throw new BadRequestException("FILL BLANKS ");
        }

        AuthenticationResponse response = new AuthenticationResponse();

        {
            if (login.getPassword() != null && login.getUsername() != null) {
                // todo : encrypt password
                Users users = UserService.getInstance().login(login.getUsername(), login.getPassword(), logId);

                if (users == null) {
                    throw new BadRequestException("INVALID USERNAME AND OR PASSWORD ");
                } else {
                    // create response ::
                    authentication = UserService.getInstance().convertToBasicAuth(login.getUsername(), login.getPassword());
                    response.setAuthentication(authentication);
                    Set<Roles> roleslist = users.getRoles();
                    List<PermissionsResponse> permissionsResponses = new ArrayList<>();

//                      for (Roles r : roleslist) {
//
//                        Set<Permissions> _permissionset = r.getPermissions();
//
//                        for (Permissions p : _permissionset) {
//                            permissions.add(p);
//                        }
//
//
//                    }
                    roleslist.stream().map((r) -> r.getPermissions()).forEachOrdered((_permissionset) -> {
                        _permissionset.forEach((p) -> {
                            PermissionsResponse permissionsResponse = new PermissionsResponse();
                            permissionsResponse.setCode(p.getCode());
                            permissionsResponse.setName(p.getName());
                            permissionsResponse.setId(p.getId().intValue());
                            permissionsResponses.add(permissionsResponse);
                        });
                    });

                    response.setPermissions(permissionsResponses);
                    response.setIsLoggedIn(true);

                }
                //todo : check username and password
            } else {
                throw new BadRequestException(" USERNAME AND OR PASSWORD IS MANDATORY  ");
            }

         */

        //  }

        return null;
//        } catch (Exception er) {
//            throw new BadRequestException(" USERNAME AND OR PASSWORD IS MANDATORY  ");
//        }
        //todo: 
    }


}
