/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codemovers.scholar.engine.api.v1.students.examregistration;

import com.codemovers.scholar.engine.api.v1.abstracts.AbstractEndpoint;
import com.codemovers.scholar.engine.api.v1.accounts.entities.AuthenticationResponse;
import com.codemovers.scholar.engine.api.v1.students.admission.StudentAdmissionEndpoint;
import com.codemovers.scholar.engine.api.v1.students.examregistration.entities.StudentExamRegistrationResponse;
import com.codemovers.scholar.engine.api.v1.students.examregistration.entities._StudentExamRegistration;
import com.codemovers.scholar.engine.api.v1.users.UserService;
import com.codemovers.scholar.engine.db.entities.SchoolData;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Path;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.Context;

/**
 *
 * @author mover 1/2/2018
 */
@Path("/")
public class StudentExamRegistrationEndpoint extends AbstractEndpoint<_StudentExamRegistration, StudentExamRegistrationResponse> {

    private static final Logger LOG = Logger.getLogger(StudentAdmissionEndpoint.class.getName());
    @Context
    private ContainerRequestContext context;
    StudentExamRegistrationService service = null;
    private AuthenticationResponse authentication = null;

    public StudentExamRegistrationEndpoint() {
        service = new StudentExamRegistrationService();
    }

    @Override
    public void validate(SchoolData schoolData, String authentication) throws Exception {
        this.authentication = UserService.getInstance().validateAuthentication(schoolData, authentication);
    }

    @Override
    public StudentExamRegistrationResponse create(_StudentExamRegistration entity, String authentication, HttpServletRequest httpRequest) throws Exception {
        return super.create(entity, authentication, httpRequest); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public StudentExamRegistrationResponse update(_StudentExamRegistration entity, String authentication, HttpServletRequest httpRequest) throws Exception {
        return super.update(entity, authentication, httpRequest); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public StudentExamRegistrationResponse archive(Integer id, String authentication, HttpServletRequest httpRequest) throws Exception {
        return super.archive(id, authentication, httpRequest); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<StudentExamRegistrationResponse> list(int start, int end, String authentication, HttpServletRequest httpRequest) throws Exception {
        return super.list(start, end, authentication, httpRequest); //To change body of generated methods, choose Tools | Templates.
    }



}
