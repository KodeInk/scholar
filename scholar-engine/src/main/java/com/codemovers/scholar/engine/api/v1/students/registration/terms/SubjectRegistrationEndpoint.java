/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codemovers.scholar.engine.api.v1.students.registration.terms;

import com.codemovers.scholar.engine.api.v1.abstracts.AbstractEndpoint;
import com.codemovers.scholar.engine.api.v1.accounts.entities.AuthenticationResponse;
import com.codemovers.scholar.engine.api.v1.students.admissions.AdmissionEndpoint;
import com.codemovers.scholar.engine.api.v1.students.registration.terms.entities.TermRegistrationResponse;
import com.codemovers.scholar.engine.api.v1.students.registration.terms.entities._TermRegistration;
import com.codemovers.scholar.engine.api.v1.users.UserService;
import com.codemovers.scholar.engine.db.entities.SchoolData;
import static com.codemovers.scholar.engine.helper.Utilities.tenantdata;
import java.util.List;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author mover
 */
public class SubjectRegistrationEndpoint extends AbstractEndpoint<_TermRegistration, TermRegistrationResponse> {

    private static final Logger LOG = Logger.getLogger(AdmissionEndpoint.class.getName());
    @Context
    private ContainerRequestContext context;
    SubjectRegistrationService service = null;
    private   AuthenticationResponse authentication = null;

    public SubjectRegistrationEndpoint() {
        service = new SubjectRegistrationService();
    }

    @Override
    public void validate(SchoolData schoolData, String authentication) throws Exception {
      this.authentication = UserService.getInstance().validateAuthentication(schoolData, authentication);
    }

    
}
