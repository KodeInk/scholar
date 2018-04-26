/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codemovers.scholar.engine.api.v1.profile;

import com.codemovers.scholar.engine.api.v1.abstracts.AbstractService;
import com.codemovers.scholar.engine.api.v1.accounts.entities.AuthenticationResponse;
import com.codemovers.scholar.engine.api.v1.profile.entities.ProfileResponse;
import com.codemovers.scholar.engine.api.v1.profile.entities._Profile;
import com.codemovers.scholar.engine.db.controllers.ProfileJpaController;
import com.codemovers.scholar.engine.db.entities.Profile;
import com.codemovers.scholar.engine.db.entities.SchoolData;
import com.codemovers.scholar.engine.db.entities.Users;
import com.codemovers.scholar.engine.helper.enums.StatusEnum;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import java.util.logging.Level;
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
    public ProfileResponse create(SchoolData data, _Profile entity, AuthenticationResponse authenticationResponse) throws Exception {
        try {
            entity.validate();
            Profile profile = getProfile(entity);
            profile = controller.create(profile, data);

            return populateResponse(profile);
        } catch (Exception er) {

            LOG.log(Level.INFO, "Error Saving the profile information {0} ", er.getMessage());
            return null;
        }
    }

    public Profile create(SchoolData data, Profile profile, AuthenticationResponse authenticationResponse) throws Exception {
        try {
            profile.setAuthor(new Users(authenticationResponse.getId().longValue()));
            profile = controller.create(profile, data);
            return profile;
        } catch (Exception er) {

            LOG.log(Level.INFO, "Error Saving the profile information {0} ", er.getMessage());
            throw er;
        }
    }

    public Profile getProfile(_Profile entity) {
        Profile profile = new Profile();
        if (entity.getFirstName() != null) {
            profile.setFirstName(entity.getFirstName());
        }
        if (entity.getMiddleName() != null) {
            profile.setMiddleName(entity.getMiddleName());
        }
        if (entity.getLastName() != null) {
            profile.setLastName(entity.getLastName());
        }
        if (entity.getPrefix() != null) {
            profile.setPrefix(entity.getPrefix());
        }

        if (entity.getDateOfBirth() != null) {
            profile.setDateOfBirth(getDateInUTC(entity.getDateOfBirth()));
        }

        if (entity.getImage() != null) {
            profile.setImage(entity.getImage());
        }
        if (entity.getProfileType() != null) {
            profile.setParentType(entity.getProfileType().toString());
        }
        if (entity.getParentId() != null) {
            profile.setParentId(entity.getParentId());
        }
        profile.setStatus(StatusEnum.ACTIVE.toString());
        if (entity.getStatus() != null) {
            profile.setStatus(entity.getStatus().toString());
        }
        profile.setDateCreated(new Date());
        if (entity.getDateCreated() != null) {
            profile.setDateCreated(getDateInUTC(entity.getDateCreated()));
        }
        if (entity.getAuthorId() != null) {
            profile.setAuthor(new Users(entity.getAuthorId().longValue()));
        }



        return profile;
    }


    public Profile populateResponse(ProfileResponse entity) {
        Profile profile = new Profile();
        profile.setId(entity.getId().longValue());
        profile.setFirstName(entity.getFirstName());
        profile.setLastName(entity.getLastName());
        profile.setMiddleName(entity.getMiddleName());
        profile.setDateCreated(new Date(entity.getDateCreated()));
        profile.setDateOfBirth(new Date(entity.getDateOfBirth()));
        return profile;
    }

    public ProfileResponse populateResponse(Profile entity) {
        ProfileResponse pr = new ProfileResponse();
        pr.setId(entity.getId().intValue());
        pr.setFirstName(entity.getFirstName());
        pr.setLastName(entity.getLastName());
        pr.setMiddleName(entity.getMiddleName());
        if (entity.getDateOfBirth() != null) {
            pr.setDateOfBirth(entity.getDateOfBirth().toString());
        }
        if (entity.getDateCreated() != null) {
            pr.setDateCreated(entity.getDateCreated().getTime());
        }

        return pr;
    }

}
