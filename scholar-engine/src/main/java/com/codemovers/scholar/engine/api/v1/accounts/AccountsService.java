/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codemovers.scholar.engine.api.v1.accounts;

import com.codemovers.scholar.engine.api.v1.abstracts.AbstractService;
import com.codemovers.scholar.engine.api.v1.accounts.entities.AuthenticationResponse;
import com.codemovers.scholar.engine.api.v1.accounts.entities._Account;
import com.codemovers.scholar.engine.api.v1.accounts.entities._login;
import com.codemovers.scholar.engine.api.v1.contacts.ContactsService;
import com.codemovers.scholar.engine.api.v1.contacts.entities.ContactsResponse;
import com.codemovers.scholar.engine.api.v1.contacts.entities._Contacts;
import com.codemovers.scholar.engine.api.v1.profile.ProfileService;
import com.codemovers.scholar.engine.api.v1.profile.entities.ProfileResponse;
import com.codemovers.scholar.engine.api.v1.profile.entities.ProfileTypesEnum;
import com.codemovers.scholar.engine.api.v1.profile.entities._Profile;
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
public class AccountsService extends AbstractService<_Account, AccountResponse> {

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

    @Override
    public AccountResponse create(SchoolData tenantData, _Account entity) throws Exception {

        try {
            entity.validate();
            //todo: create User
            UserService userService = UserService.getInstance();
            _User USER = new _User();
            USER.setUsername(entity.getUsername());
            USER.setPassword(entity.getPassword());
            USER.setRoles(entity.getRoles());
            UserResponse userResponse = userService.create(tenantData, USER);

            //todo: create profile 
            _Profile profile = new _Profile();
            profile.setParentId(userResponse.getId());
            profile.setProfileType(ProfileTypesEnum.USER);
            profile.setFirstName(entity.getFirstName());
            profile.setMiddleName(entity.getMiddleName());
            profile.setLastName(entity.getLastName());
            profile.setPrefix(entity.getPrefix());
            profile.setDateOfBirth(entity.getDateOfBirth());
            profile.setDateCreated(new Date());
            profile.setStatus(StatusEnum.ACTIVE);
            profile.setAuthorId(userResponse.getId());
            ProfileResponse profileResponse = ProfileService.getInstance().create(tenantData, profile);

            //todo: if not empty contact information , add the email
            ContactsResponse contactsResponse = null;
            if (!entity.getEmailaddress().isEmpty()) {
                // trigger the sending of an email if enabled to the user etc ::

                _Contacts contacts = new _Contacts();
                contacts.setParentType(ParentTypes.PROFILE);
                contacts.setParentId(userResponse.getId());
                contacts.setContactType(ContactTypes.EMAIL);
                contacts.setDetails(entity.getEmailaddress());
                contacts.setStatus(StatusEnum.ACTIVE);
                contacts.setDateCreated(new Date());
                contacts.setAuthorId(userResponse.getId());
                contactsResponse = ContactsService.getInstance().create(tenantData, contacts);

            }

            AccountResponse accountResponse = new AccountResponse();

            return null;

        } catch (Exception e) {
            throw e;
        }
    }

    public void deactivate(SchoolData tenantData, Integer account_id) {

    }

    public AccountResponse populateResponse() {

        AccountResponse accountResponse = new AccountResponse();
        return accountResponse;
    }

}
