/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codemovers.scholar.engine.api.v1.exams;

import com.codemovers.scholar.engine.api.v1.abstracts.AbstractEndpoint;
import com.codemovers.scholar.engine.api.v1.accounts.entities.AuthenticationResponse;
import com.codemovers.scholar.engine.api.v1.exams.entities.ExamResponse;
import com.codemovers.scholar.engine.api.v1.exams.entities._Exam;
import com.codemovers.scholar.engine.api.v1.users.UserService;
import com.codemovers.scholar.engine.db.entities.SchoolData;
import static com.codemovers.scholar.engine.helper.Utilities.tenantdata;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author mover 12/30/2017
 */
@Path("/")
public class ExamsEndpoint extends AbstractEndpoint<_Exam, ExamResponse> {

    private static final Logger LOG = Logger.getLogger(ExamsEndpoint.class.getName());
    @Context
    private ContainerRequestContext context;
    private ExamsService service = null;
    private AuthenticationResponse authentication = null;

    public ExamsEndpoint() {
        service = new ExamsService();
    }

    @Override
    public void validate(SchoolData schoolData, String authentication) throws Exception {
        this.authentication = UserService.getInstance().validateAuthentication(schoolData, authentication);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Override
    public ExamResponse create(_Exam entity, String authentication, HttpServletRequest httpRequest) throws Exception {
        validate(tenantdata, authentication);
        return service.create(tenantdata, entity, this.authentication);
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Override
    public ExamResponse update(_Exam entity, String authentication, HttpServletRequest httpRequest) throws Exception {
        validate(tenantdata, authentication);
        return service.update(tenantdata, entity, this.authentication);

    }

    @POST
    @Path("/archive/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Override
    public ExamResponse archive(Integer id, String authentication, HttpServletRequest httpRequest) throws Exception {
        validate(tenantdata, authentication);
        return service.archive(tenantdata, id, this.authentication);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Override
    public List<ExamResponse> list(int start, int end, String authentication, HttpServletRequest httpRequest) throws Exception {
        validate(tenantdata, authentication);
        return service.list(tenantdata, start, end, this.authentication);
    }



}
