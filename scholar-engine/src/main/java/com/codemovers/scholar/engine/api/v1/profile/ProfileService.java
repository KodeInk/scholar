/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codemovers.scholar.engine.api.v1.profile;

import com.codemovers.scholar.engine.api.v1.abstracts.AbstractService;
import com.codemovers.scholar.engine.api.v1.profile.entities.ProfileResponse;
import com.codemovers.scholar.engine.api.v1.profile.entities._Profile;
import com.codemovers.scholar.engine.api.v1.users.UserService;
import com.codemovers.scholar.engine.db.controllers.ProfileJpaController;
import com.codemovers.scholar.engine.db.controllers.UsersJpaController;
import com.codemovers.scholar.engine.db.entities.Profile;
import com.codemovers.scholar.engine.db.entities.SchoolData;
import com.codemovers.scholar.engine.db.entities.Users;
import java.util.logging.Logger;

/**
 * mm/dd/yyyy
 *
 * @author Mover 12/14/2017
 */
public class ProfileService extends AbstractService<_Profile, ProfileResponse> {

    private static final Logger LOG = Logger.getLogger(ProfileService.class.getName());

    private final ProfileJpaController controller;

    private static ProfileService service = null;

    public ProfileService() {
        controller = ProfileJpaController.getInstance();
    }

    public static ProfileService getInstance() {
        if (service == null) {
            service = new ProfileService();
        }
        return service;
    }

    @Override
    public ProfileResponse create(SchoolData data, _Profile entity) throws Exception {
        entity.validate();

        Profile profile = new Profile();
        if (!entity.getFirstName().isEmpty()) {
            profile.setFirstName(entity.getFirstName());
        }

        if (!entity.getMiddleName().isEmpty()) {
            profile.setMiddleName(entity.getMiddleName());
        }

        if (!entity.getLastName().isEmpty()) {
            profile.setLastName(entity.getLastName());
        }

        if (!entity.getPrefix().isEmpty()) {
            profile.setPrefix(entity.getPrefix());
        }

        if (entity.getDateOfBirth() != null) {
            profile.setDateOfBirth(entity.getDateOfBirth());
        }

        if (!entity.getImage().isEmpty()) {
            profile.setImage(entity.getImage());
        }

        profile.setParentType(entity.getProfileType().toString());
        profile.setParentId(entity.getParentId());

        profile.setAuthorId(new Users(entity.getAuthorId().longValue()));
        profile.setStatus(entity.getStatus().toString());

        profile.setDateCreated(entity.getDateCreated());






        return null;
    }

}
