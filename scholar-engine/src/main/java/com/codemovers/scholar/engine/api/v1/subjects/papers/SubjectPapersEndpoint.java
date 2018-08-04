/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codemovers.scholar.engine.api.v1.subjects.papers;

import com.codemovers.scholar.engine.api.v1.abstracts.AbstractEndpoint;
import com.codemovers.scholar.engine.api.v1.accounts.entities.AuthenticationResponse;
import com.codemovers.scholar.engine.api.v1.subjects.papers.entities.SubjectPapersResponse;
import com.codemovers.scholar.engine.api.v1.subjects.papers.entities.SubjectPaper;
import com.codemovers.scholar.engine.api.v1.users.UserService;
import com.codemovers.scholar.engine.db.entities.SchoolData;
import static com.codemovers.scholar.engine.helper.Utilities.tenantdata;
import java.util.List;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author mover 1/3/2018
 */
@Path("/")
public class SubjectPapersEndpoint extends AbstractEndpoint<SubjectPaper, SubjectPapersResponse> {

    private static final Logger LOG = Logger.getLogger(SubjectPapersEndpoint.class.getName());
    @Context
    private ContainerRequestContext context;

    SubjectPapersService service = null;
    private AuthenticationResponse authentication = null;

    public SubjectPapersEndpoint() {
        service = new SubjectPapersService();
    }

    @Override
    public void validate(SchoolData schoolData, String authentication) throws Exception {
        this.authentication = UserService.getInstance().validateAuthentication(schoolData, authentication);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Override
    public SubjectPapersResponse create(
            SubjectPaper entity,
            @HeaderParam("authentication") String authentication,
            @Context HttpServletRequest httpRequest
    ) throws Exception {
        validate(tenantdata, authentication);
        return service.create(tenantdata, entity, this.authentication);
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Override
    public SubjectPapersResponse update(
            SubjectPaper entity,
            @HeaderParam("authentication") String authentication,
            @Context HttpServletRequest httpRequest
    ) throws Exception {
        validate(tenantdata, authentication);
        return service.update(tenantdata, entity, this.authentication);

    }

    @POST
    @Path("/archive/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Override
    public SubjectPapersResponse archive(
            @QueryParam("id") Integer id,
            @HeaderParam("authentication") String authentication,
            @Context HttpServletRequest httpRequest) throws Exception {
        validate(tenantdata, authentication);
        return service.archive(tenantdata, id, this.authentication);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Override
    public List<SubjectPapersResponse> list(
            @DefaultValue("0") @QueryParam("offset") int offset,
            @DefaultValue("50") @QueryParam("limit") int limit,
            @HeaderParam("authentication") String authentication, @Context HttpServletRequest httpRequest
    ) throws Exception {
        validate(tenantdata, authentication);
        return service.list(tenantdata, offset, limit, this.authentication);

    }

}
