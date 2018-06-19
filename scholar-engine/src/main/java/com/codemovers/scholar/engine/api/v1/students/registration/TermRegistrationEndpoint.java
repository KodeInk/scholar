/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codemovers.scholar.engine.api.v1.students.registration;

import com.codemovers.scholar.engine.api.v1.abstracts.AbstractService;
import com.codemovers.scholar.engine.api.v1.accounts.entities.AuthenticationResponse;
import com.codemovers.scholar.engine.api.v1.students.admissions.AdmissionEndpoint;
import com.codemovers.scholar.engine.api.v1.students.admissions.AdmissionService;
import com.codemovers.scholar.engine.api.v1.students.registration.entities.TermRegistrationResponse;
import com.codemovers.scholar.engine.api.v1.students.registration.entities._TermRegistration;
import com.codemovers.scholar.engine.db.controllers.StudentExamRegistrationJpaController;
import com.codemovers.scholar.engine.db.entities.SchoolData;
import java.util.logging.Logger;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.Context;

/**
 *
 * @author mover
 */
public class TermRegistrationEndpoint extends AbstractService<_TermRegistration, TermRegistrationResponse> {

    private static final Logger LOG = Logger.getLogger(TermRegistrationEndpoint.class.getName());
    @Context
    private ContainerRequestContext context;
    TermRegistrationService service = null;
    private AuthenticationResponse authentication = null;

    public TermRegistrationEndpoint() {
        service = new TermRegistrationService();
    }

    @Override
    public TermRegistrationResponse create(SchoolData data, _TermRegistration entity, AuthenticationResponse authentication) throws Exception {
        return super.create(data, entity, authentication); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TermRegistrationResponse update(SchoolData data, _TermRegistration entity, AuthenticationResponse authentication) throws Exception {
        return super.update(data, entity, authentication); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TermRegistrationResponse archive(SchoolData data, Integer id, AuthenticationResponse authentication) throws Exception {
        return super.archive(data, id, authentication); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TermRegistrationResponse getById(SchoolData data, Integer Id, AuthenticationResponse authentication) throws Exception {
        return super.getById(data, Id, authentication); //To change body of generated methods, choose Tools | Templates.
    }
    
    

}
