/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codemovers.scholar.engine.api.v1.students.termregistration;

import com.codemovers.scholar.engine.api.v1.abstracts.AbstractService;
import com.codemovers.scholar.engine.api.v1.accounts.entities.AuthenticationResponse;
import com.codemovers.scholar.engine.api.v1.streams.StreamsService;
import com.codemovers.scholar.engine.api.v1.students.termregistration.entities.StudentTermRegistrationResponse;
import com.codemovers.scholar.engine.api.v1.students.termregistration.entities._StudentTermRegistration;
import com.codemovers.scholar.engine.db.controllers.StudentTermRegistrationJpaController;
import com.codemovers.scholar.engine.db.entities.SchoolData;
import java.util.List;
import java.util.logging.Logger;

/**
 *
 * @author mover 1/1/2018
 */
public class StudentTermRegistrationService extends AbstractService<_StudentTermRegistration, StudentTermRegistrationResponse> {

    private static final Logger LOG = Logger.getLogger(StreamsService.class.getName());
    private final StudentTermRegistrationJpaController controller;
    private static StudentTermRegistrationService service = null;

    public StudentTermRegistrationService() {
        controller = StudentTermRegistrationJpaController.getInstance();
    }

    public static StudentTermRegistrationService getInstance() {
        if (service == null) {
            service = new StudentTermRegistrationService();
        }
        return service;
    }

    @Override
    public StudentTermRegistrationResponse create(SchoolData data, _StudentTermRegistration entity, AuthenticationResponse authentication) throws Exception {
        return super.create(data, entity, authentication); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public StudentTermRegistrationResponse update(SchoolData data, _StudentTermRegistration entity, AuthenticationResponse authentication) throws Exception {
        return super.update(data, entity, authentication); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public StudentTermRegistrationResponse archive(SchoolData data, Integer id, AuthenticationResponse authentication) throws Exception {
        return super.archive(data, id, authentication); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public StudentTermRegistrationResponse getById(SchoolData data, Integer Id) throws Exception {
        return super.getById(data, Id); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<StudentTermRegistrationResponse> list(SchoolData data, Integer ofset, Integer limit, AuthenticationResponse authentication) throws Exception {
        return super.list(data, ofset, limit, authentication); //To change body of generated methods, choose Tools | Templates.
    }


}
