/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codemovers.scholar.engine.api.v1.classes;

import com.codemovers.scholar.engine.api.v1.abstracts.AbstractEndpoint;
import com.codemovers.scholar.engine.api.v1.accounts.entities.AuthenticationResponse;
import com.codemovers.scholar.engine.api.v1.classes.entities.ClassResponse;
import com.codemovers.scholar.engine.api.v1.classes.entities.SchoolClass;
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
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author mover 12/19/2017
 */
@Path("/")
public class ClassEndpoint extends AbstractEndpoint<SchoolClass, ClassResponse> {

    private static final Logger LOG = Logger.getLogger(ClassEndpoint.class.getName());
    @Context
    private ContainerRequestContext context;

    private ClassService service = null;
    private AuthenticationResponse authentication = null;

    /**
     *
     */
    public ClassEndpoint() {
        service = new ClassService();
    }

    /**
     *
     * @param schoolData
     * @param authentication
     * @throws Exception
     */
    @Override
    public void validate(SchoolData schoolData, String authentication) throws Exception {
        this.authentication = UserService.getInstance().validateAuthentication(schoolData, authentication);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Override
    public ClassResponse create(SchoolClass entity,
            @HeaderParam("authentication") String authentication,
            @Context HttpServletRequest httpRequest) throws Exception {
        validate(tenantdata, authentication);
        return service.create(tenantdata, entity, this.authentication);
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Override
    public ClassResponse update(SchoolClass entity,
            @HeaderParam("authentication") String authentication,
            @Context HttpServletRequest httpRequest) throws Exception {
        validate(tenantdata, authentication);
        return service.update(tenantdata, entity, this.authentication);
    }

    @POST
    @Path("/archive/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Override
    public ClassResponse archive(@PathParam("id") Integer id, @HeaderParam("authentication") String authentication, @Context HttpServletRequest httpRequest) throws Exception, Exception {
        validate(tenantdata, authentication);
        return service.archive(tenantdata, id, this.authentication);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Override
    public List<ClassResponse> list(
            @DefaultValue("0") @QueryParam("offset") int offset,
            @DefaultValue("50") @QueryParam("limit") int limit,
            @HeaderParam("authentication") String authentication, @Context HttpServletRequest httpRequest) throws Exception {
        validate(tenantdata, authentication);
        System.out.println("---------------------------");
        System.out.println("OFFSET : " + offset);
        System.out.println("LIMIT :  " + limit);
        System.out.println("---------------------------");
        return service.list(tenantdata, offset, limit, this.authentication);
    }

    @GET
    @Path("/search/{query}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)

    public List<ClassResponse> search(
            @PathParam("query") String query,
            @HeaderParam("authentication") String authentication,
            @DefaultValue("0") @QueryParam("offset") int offset,
            @DefaultValue("50") @QueryParam("limit") int limit,
            @Context HttpServletRequest httpRequest) throws Exception, Exception {
        validate(tenantdata, authentication);
        System.out.println("==============================");
        System.out.println(query);
        return service.search(tenantdata, query, offset, limit, this.authentication);
    }

}
