/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codemovers.scholar.engine.api.v1.students.registration;

import com.codemovers.scholar.engine.api.v1.abstracts.AbstractService;
import com.codemovers.scholar.engine.api.v1.accounts.entities.AuthenticationResponse;
import com.codemovers.scholar.engine.api.v1.students.admissions.AdmissionService;
import com.codemovers.scholar.engine.api.v1.students.registration.entities.TermRegistrationResponse;
import com.codemovers.scholar.engine.api.v1.students.registration.entities._TermRegistration;
import com.codemovers.scholar.engine.db.controllers.StudentTermRegistrationJpaController;
import com.codemovers.scholar.engine.db.entities.SchoolData;
import java.util.List;
import java.util.logging.Logger;

/**
 *
 * @author mover
 */
public class TermRegistrationService extends AbstractService<_TermRegistration, TermRegistrationResponse> {

    private static final Logger LOG = Logger.getLogger(AdmissionService.class.getName());
    private final StudentTermRegistrationJpaController controller;
    private static TermRegistrationService instance = null;

    public TermRegistrationService() {
        controller = StudentTermRegistrationJpaController.getInstance();
    }

    public static TermRegistrationService getInstance() {
        if (instance == null) {
            instance = new TermRegistrationService();
        }

        return instance;

    }

    @Override
    public TermRegistrationResponse create(SchoolData data, _TermRegistration entity, AuthenticationResponse authentication) throws Exception {
       //todo: validate
       entity.validate();
       //todo: populate entity
       //todo: check if student already registered for this term
       //todo: create entity
       //todo: populate response 
        
        return super.create(data, entity, authentication); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TermRegistrationResponse update(SchoolData data, _TermRegistration entity, AuthenticationResponse authentication) throws Exception {
        return super.update(data, entity, authentication); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TermRegistrationResponse getById(SchoolData data, Integer Id, AuthenticationResponse authentication) throws Exception {
        return super.getById(data, Id, authentication); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<TermRegistrationResponse> list(SchoolData data, Integer ofset, Integer limit, AuthenticationResponse authentication) throws Exception {
        return super.list(data, ofset, limit, authentication); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TermRegistrationResponse archive(SchoolData data, Integer id, AuthenticationResponse authentication) throws Exception {
        return super.archive(data, id, authentication); //To change body of generated methods, choose Tools | Templates.
    }

    
    
    
    
}
