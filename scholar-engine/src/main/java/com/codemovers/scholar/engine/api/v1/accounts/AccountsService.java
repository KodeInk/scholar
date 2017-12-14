/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codemovers.scholar.engine.api.v1.accounts;

import com.codemovers.scholar.engine.api.v1.accounts.entities.AuthenticationResponse;
import com.codemovers.scholar.engine.api.v1.accounts.entities._Account;
import com.codemovers.scholar.engine.api.v1.accounts.entities._login;
import com.codemovers.scholar.engine.api.v1.users.UserService;
import com.codemovers.scholar.engine.api.v1.users.entities.UserResponse;
import com.codemovers.scholar.engine.api.v1.users.entities._User;
import com.codemovers.scholar.engine.db.entities.SchoolData;
import static com.codemovers.scholar.engine.helper.Utilities.getNewExternalId;
import com.codemovers.scholar.engine.helper.enums.ContactTypes;
import com.codemovers.scholar.engine.helper.enums.ParentTypes;
import com.codemovers.scholar.engine.helper.enums.StatusEnum;
import com.codemovers.scholar.engine.helper.exceptions.BadRequestException;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

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

    public AuthenticationResponse login(SchoolData tenantData, _login login, String logId) throws Exception {

        LOG.log(Level.INFO, "School Name {0} ", tenantData.getName());
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

    public AccountResponse create(SchoolData tenantData, _Account entity) throws Exception {

        try {
            entity.validate();
            //todo: create User
            UserService userService = UserService.getInstance();
            _User USER = new _User();
            USER.setUsername(entity.getUsername());
            USER.setPassword(entity.getPassword());
            USER.setRoles(entity.getRoles());
            UserResponse userResponse = userService.create(USER);


            //todo: assign permissions to the user
            //todo: create profile  and add parent type and parent_id
            //todo: create user contact information 
            //todo: check to see if there is an email with the same
            //todo: person
            Person person = null;

            //todo : create a general account
            accounts = new GeneralAccounts();
            accounts.setExternalid(getNewExternalId());

            accounts.setAccountType(entity.getAccounttype().toString());

            //accounts.setStatus(entity.getStatus().toString());
            accounts.setStatus("ACTIVE");

            accounts.setDateCreated(new Date());

            //todo: create General AcFcount ::
            GeneralAccounts account = controller.create(accounts);

            ContactsResponse contactsResponse = null;
            //todo: create the email contact for the account
            {
                if (entity.getEmailaddress() != null) {
                    //emailaddress
                    _contacts contacts = new _contacts();
                    contacts.setContactType(ContactTypes.EMAIL.toString());
                    contacts.setDetails(entity.getEmailaddress());
                    contacts.setParentType(ParentTypes.GENERALACCOUNT.toString());
                    contacts.setParentId(account.getId());

                    contactsResponse = ContactsService.getInstance().create(contacts);
                }

            }
            //todo: create a user
            _User user = new _User();
            user.setAccount_id(account.getId());

            user.setUsername(entity.getUsername());
            user.setPassword(entity.getPassword());
            user.setStatus(StatusEnum.ACTIVE.toString());

            if (accounts.getAccountType() != null) {
                switch (accounts.getAccountType()) {

                    case "NORMAL":
                        user.setRole("ADMIN");
                        break;

                    case "COMPANY":
                        user.setRole("ADMIN");
                        break;

                    case "ORGANISATION":
                        user.setRole("ADMIN");
                        break;

                    default:
                        user.setRole("ADMIN");
                        break;
                }

            }

            UserResponse userResponse = UserService.getInstance().create(user);
            String authentication = null;

            if (userResponse != null) {
                authentication = UserService.getInstance().convertToBasicAuth(entity.getUsername(), entity.getPassword());
            } else {
                throw new BadRequestException(" Account not succesfully created ");
            }

            AccountResponse response = new AccountResponse();
            response.setAccounttype(accounts.getAccountType());
            response.setUsername(entity.getUsername());
            if (contactsResponse != null) {
                response.setEmailaddress(contactsResponse.getDetails());
            }

            response.setStatus(accounts.getStatus());
            response.setScholarid(accounts.getExternalid());
            response.setAuthentication(authentication);

            return response;
        } catch (Exception e) {
            throw e;
        }
    }

}
