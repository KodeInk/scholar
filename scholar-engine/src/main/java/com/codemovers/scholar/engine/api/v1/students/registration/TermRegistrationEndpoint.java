/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codemovers.scholar.engine.api.v1.students.registration;

import com.codemovers.scholar.engine.api.v1.abstracts.AbstractEndpoint;
import com.codemovers.scholar.engine.api.v1.abstracts.AbstractService;
import com.codemovers.scholar.engine.api.v1.accounts.entities.AuthenticationResponse;
import com.codemovers.scholar.engine.api.v1.students.registration.entities.TermRegistrationResponse;
import com.codemovers.scholar.engine.api.v1.students.registration.entities._TermRegistration;
import com.codemovers.scholar.engine.api.v1.users.UserService;
import com.codemovers.scholar.engine.db.entities.SchoolData;
import java.util.List;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.Context;
import org.eclipse.jetty.io.AbstractEndPoint;

/**
 *
 * @author mover
 */
public class TermRegistrationEndpoint extends AbstractEndpoint<_TermRegistration, TermRegistrationResponse> {

    private static final Logger LOG = Logger.getLogger(TermRegistrationEndpoint.class.getName());
    @Context
    private ContainerRequestContext context;
    TermRegistrationService service = null;
    private AuthenticationResponse authentication = null;

    public TermRegistrationEndpoint() {
        service = new TermRegistrationService();
    }
    
     @Override
    public void validate(SchoolData schoolData, String authentication) throws Exception {
        this.authentication = UserService.getInstance().validateAuthentication(schoolData, authentication);
    }

    @Override
    public TermRegistrationResponse create(_TermRegistration entity, String authentication, HttpServletRequest httpRequest) throws Exception {
        return super.create(entity, authentication, httpRequest); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TermRegistrationResponse update(_TermRegistration entity, String authentication, HttpServletRequest httpRequest) throws Exception {
        return super.update(entity, authentication, httpRequest); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TermRegistrationResponse get(int id, String authentication, HttpServletRequest httpRequest) throws Exception {
        return super.get(id, authentication, httpRequest); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<TermRegistrationResponse> list(int start, int end, String authentication, HttpServletRequest httpRequest) throws Exception {
        return super.list(start, end, authentication, httpRequest); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TermRegistrationResponse archive(Integer id, String authentication, HttpServletRequest httpRequest) throws Exception {
        return super.archive(id, authentication, httpRequest); //To change body of generated methods, choose Tools | Templates.
    }
    
   
    

}
