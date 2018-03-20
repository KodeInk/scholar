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
import com.codemovers.scholar.engine.api.v1.staff.entities.StaffResponse;
import com.codemovers.scholar.engine.api.v1.staff.entities._Staff;
import com.codemovers.scholar.engine.db.controllers.StaffJpaController;
import com.codemovers.scholar.engine.db.entities.Profile;
import com.codemovers.scholar.engine.db.entities.SchoolData;
import com.codemovers.scholar.engine.db.entities.Staff;
import com.codemovers.scholar.engine.db.entities.Users;
import com.codemovers.scholar.engine.helper.enums.StatusEnum;
import com.codemovers.scholar.engine.helper.exceptions.BadRequestException;
import java.util.ArrayList;
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
        ProfileResponse profileResponse = ProfileService.getInstance().create(data, entity.getProfile(), authentication);
        Profile profile = ProfileService.getInstance().populateResponse(profileResponse);
        Staff staff = getStaff(profile, entity, authentication);
        staff = controller.create(staff, data);
        return populateResponse(staff);
    }

    @Override
    public StaffResponse update(SchoolData data, _Staff entity, AuthenticationResponse authentication) throws Exception {
        return super.update(data, entity, authentication); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public StaffResponse archive(SchoolData data, Integer id, AuthenticationResponse authentication) throws Exception {
        Staff staff = controller.findStaff(id, data);
        if (staff == null) {
            throw new BadRequestException("Staff does not exist");
        }
        staff.setStatus(StatusEnum.ARCHIVED.toString());
        staff = controller.edit(staff, data);

        return populateResponse(staff);
    }

    @Override
    public StaffResponse getById(SchoolData data, Integer Id, AuthenticationResponse authentication) throws Exception {
        Staff staff = controller.findStaff(Id, data);
        if (staff == null) {
            throw new BadRequestException("Staff does not exist");
        }
        return populateResponse(staff);
    }

    @Override
    public List<StaffResponse> list(SchoolData data, Integer ofset, Integer limit, AuthenticationResponse authentication) throws Exception {

        List<Staff> staff = controller.findStaffEntities(ofset, limit, data);

        List<StaffResponse> staffResponses = new ArrayList<>();
        if (staff != null) {
            for (Staff staff1 : staff) {
                StaffResponse staffResponse = populateResponse(staff1);
                staffResponses.add(staffResponse);
            }
        }
        return staffResponses;
    }

    public StaffResponse populateResponse(Staff entity) {
        StaffResponse staffResponse = new StaffResponse();
        if (entity.getProfile() != null) {
            staffResponse.setProfile(ProfileService.getInstance().populateResponse(entity.getProfile()));
        }

        staffResponse.setJoinDate(entity.getJoinDate().getTime());
        staffResponse.setStatus(entity.getStatus());
        staffResponse.setDate_created(entity.getDateCreated().getTime());
        if (entity.getAuthor() != null) {
            staffResponse.setAuthor(entity.getAuthor().getUsername());
        }

        return staffResponse;
    }

    public Staff getStaff(Profile profile, _Staff entity, AuthenticationResponse authentication) {
        Staff staff = new Staff();
        staff.setProfile(profile);
        staff.setJoinDate(entity.getJoinDate());
        staff.setStatus(entity.getStatus().toString());
        staff.setDateCreated(new Date(entity.getDate_created()));
        staff.setAuthor(new Users(authentication.getId().longValue()));
        return staff;
    }

}
