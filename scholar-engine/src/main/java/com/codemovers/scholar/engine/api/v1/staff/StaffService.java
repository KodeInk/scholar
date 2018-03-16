/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codemovers.scholar.engine.api.v1.staff;

import com.codemovers.scholar.engine.api.v1.abstracts.AbstractService;
import com.codemovers.scholar.engine.api.v1.accounts.entities.AuthenticationResponse;
import com.codemovers.scholar.engine.api.v1.staff.entities.StaffResponse;
import com.codemovers.scholar.engine.api.v1.staff.entities._Staff;
import com.codemovers.scholar.engine.db.controllers.StaffJpaController;
import com.codemovers.scholar.engine.db.entities.SchoolData;
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
        return super.create(data, entity, authentication); //To change body of generated methods, choose Tools | Templates.
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

}
