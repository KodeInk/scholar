/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codemovers.scholar.engine.api.v1.students.termregistration;

import com.codemovers.scholar.engine.api.v1.abstracts.AbstractEndpoint;
import com.codemovers.scholar.engine.api.v1.accounts.entities.AuthenticationResponse;

import com.codemovers.scholar.engine.api.v1.students.termregistration.entities.StudentTermRegistrationResponse;
import com.codemovers.scholar.engine.api.v1.students.termregistration.entities._StudentTermRegistration;
import com.codemovers.scholar.engine.api.v1.users.UserService;
import com.codemovers.scholar.engine.db.entities.SchoolData;
import java.util.List;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.Context;

/**
 *
 * @author mover 1/1/2018
 */
public class StudentTermRegistrationEndpoint extends AbstractEndpoint<_StudentTermRegistration, StudentTermRegistrationResponse> {

    private static final Logger LOG = Logger.getLogger(StudentTermRegistrationEndpoint.class.getName());
    @Context
    private ContainerRequestContext context;
    StudentTermRegistrationService service = null;
    private AuthenticationResponse authentication = null;

    public StudentTermRegistrationEndpoint() {
        service = new StudentTermRegistrationService();
    }

    @Override
    public void validate(SchoolData schoolData, String authentication) throws Exception {
        this.authentication = UserService.getInstance().validateAuthentication(schoolData, authentication);
    }

    @Override
    public StudentTermRegistrationResponse create(_StudentTermRegistration entity, String authentication, HttpServletRequest httpRequest) throws Exception {
        return super.create(entity, authentication, httpRequest); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public StudentTermRegistrationResponse update(_StudentTermRegistration entity, String authentication, HttpServletRequest httpRequest) throws Exception {
        return super.update(entity, authentication, httpRequest); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public StudentTermRegistrationResponse archive(Integer id, String authentication, HttpServletRequest httpRequest) throws Exception {
        return super.archive(id, authentication, httpRequest); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<StudentTermRegistrationResponse> list(int start, int end, String authentication, HttpServletRequest httpRequest) throws Exception {
        return super.list(start, end, authentication, httpRequest); //To change body of generated methods, choose Tools | Templates.
    }

}
