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
import com.codemovers.scholar.engine.db.entities.Roles;
import com.codemovers.scholar.engine.db.entities.SchoolData;
import com.codemovers.scholar.engine.db.entities.Users;
import com.codemovers.scholar.engine.helper.enums.ContactTypes;
import com.codemovers.scholar.engine.helper.enums.ParentTypes;
import com.codemovers.scholar.engine.helper.enums.StatusEnum;
import com.codemovers.scholar.engine.helper.exceptions.BadRequestException;
import java.util.ArrayList;

import java.util.Date;
import java.util.List;
import java.util.Set;
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


}
