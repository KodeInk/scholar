/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codemovers.scholar.engine.api.v1.contacts;

import com.codemovers.scholar.engine.api.v1.abstracts.AbstractService;
import com.codemovers.scholar.engine.api.v1.contacts.entities.ContactsResponse;
import com.codemovers.scholar.engine.api.v1.contacts.entities._Contacts;
import com.codemovers.scholar.engine.api.v1.users.UserService;
import com.codemovers.scholar.engine.db.controllers.ContactsJpaController;
import com.codemovers.scholar.engine.db.controllers.UsersJpaController;
import com.codemovers.scholar.engine.db.entities.Contacts;
import com.codemovers.scholar.engine.db.entities.SchoolData;
import com.codemovers.scholar.engine.db.entities.Users;
import java.util.logging.Logger;

/**
 *
 * @author Manny
 */
public class ContactsService extends AbstractService<_Contacts, ContactsResponse> {

    private static final Logger LOG = Logger.getLogger(ContactsService.class.getName());

    private final ContactsJpaController controller;

    private static ContactsService service = null;

    public ContactsService() {
        controller = ContactsJpaController.getInstance();
    }

    public static ContactsService getInstance() {
        if (service == null) {
            service = new ContactsService();
        }
        return service;
    }

    @Override
    public ContactsResponse create(SchoolData data, _Contacts entity) throws Exception {
        entity.validate();
        Contacts contacts = new Contacts();
        
        contacts.setParentType(entity.getParentType().toString());
        contacts.setParentId(entity.getParentId());
        contacts.setContactType(entity.getContactType().toString());
        contacts.setDetails(entity.getDetails());
        contacts.setStatus(entity.getStatus().toString());
        contacts.setDateCreated(entity.getDateCreated());
        contacts.setAuthorId(new Users(entity.getAuthorId().longValue()));

        controller.create(contacts, data);


        return null;

    }



}
