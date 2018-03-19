/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codemovers.scholar.engine.api.v1.staff;

import com.codemovers.scholar.engine.api.v1.abstracts.AbstractService;
import com.codemovers.scholar.engine.api.v1.accounts.entities.AuthenticationResponse;
import com.codemovers.scholar.engine.api.v1.profile.ProfileService;
import com.codemovers.scholar.engine.api.v1.profile.entities.ProfileResponse;
import com.codemovers.scholar.engine.api.v1.profile.entities._Profile;
import com.codemovers.scholar.engine.api.v1.staff.entities.StaffResponse;
import com.codemovers.scholar.engine.api.v1.staff.entities._Staff;
import com.codemovers.scholar.engine.db.controllers.StaffJpaController;
import com.codemovers.scholar.engine.db.entities.Profile;
import com.codemovers.scholar.engine.db.entities.SchoolData;
import com.codemovers.scholar.engine.db.entities.Staff;
import com.codemovers.scholar.engine.db.entities.Users;
import com.codemovers.scholar.engine.helper.enums.StatusEnum;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

/**
 *
 * @author mover 3/16/2018
 */
public class StaffService extends AbstractService<_Staff, StaffResponse> {

    private static final Logger LOG = Logger.getLogger(StaffService.class.getName());
    private final StaffJpaController controller;
    private static StaffService service = null;

    public StaffService() {
        controller = new StaffJpaController();
    }

    public static StaffService getInstance() {
        if (service == null) {
            service = new StaffService();
        }
        return service;
    }

    @Override
    public StaffResponse create(SchoolData data, _Staff entity, AuthenticationResponse authentication) throws Exception {
        entity.validate();

        _Profile profile = getProfile(entity);
        ProfileResponse profileResponse = ProfileService.getInstance().create(data, profile, authentication);
        Profile p = new Profile();
        p.setId(profileResponse.getId().longValue());
        //todo: create Staff

        Staff staff = getStaff(p, entity, authentication);

        staff = controller.create(staff, data);
    }


    @Override
    public StaffResponse update(SchoolData data, _Staff entity, AuthenticationResponse authentication) throws Exception {
        return super.update(data, entity, authentication); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public StaffResponse archive(SchoolData data, Integer id, AuthenticationResponse authentication) throws Exception {
        return super.archive(data, id, authentication); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public StaffResponse getById(SchoolData data, Integer Id, AuthenticationResponse authentication) throws Exception {
        return super.getById(data, Id, authentication); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<StaffResponse> list(SchoolData data, Integer ofset, Integer limit, AuthenticationResponse authentication) throws Exception {
        return super.list(data, ofset, limit, authentication); //To change body of generated methods, choose Tools | Templates.
    }

    public StaffResponse populateResponse(Staff entity) {
        StaffResponse staffResponse = new StaffResponse();
        if (entity.getProfile() != null) {
            staffResponse.setFirstname(entity.getProfile().getFirstName());
        }


        return staffResponse;
    }

    public static _Profile getProfile(_Staff entity) {
        //todo : create new profile
        _Profile profile = new _Profile();
        profile.setFirstName(entity.getFirstname());
        profile.setLastName(entity.getLastname());
        profile.setMiddleName(entity.getMiddlename());
        profile.setPrefix(entity.getPrefix());
        profile.setDateOfBirth(entity.getDateofbirth());
        profile.setImage(entity.getImage());
        profile.setStatus(StatusEnum.ACTIVE);
        return profile;
    }

    public Staff getStaff(Profile p, _Staff entity, AuthenticationResponse authentication) {
        Staff staff = new Staff();
        staff.setProfile(p);
        staff.setJoinDate(entity.getJoinDate());
        staff.setStatus(entity.getStatus().toString());
        staff.setDateCreated(new Date(entity.getDate_created()));
        staff.setAuthorId(new Users(authentication.getId().longValue()));
        return staff;
    }

}
