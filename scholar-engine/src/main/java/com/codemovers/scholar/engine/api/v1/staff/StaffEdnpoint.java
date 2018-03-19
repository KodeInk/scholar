/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codemovers.scholar.engine.api.v1.staff;

import com.codemovers.scholar.engine.api.v1.abstracts.AbstractEndpoint;
import com.codemovers.scholar.engine.api.v1.accounts.entities.AuthenticationResponse;
import com.codemovers.scholar.engine.api.v1.staff.entities.StaffResponse;
import com.codemovers.scholar.engine.api.v1.staff.entities._Staff;
import com.codemovers.scholar.engine.api.v1.users.UserService;
import com.codemovers.scholar.engine.api.v1.users.UsersEndpoint;
import com.codemovers.scholar.engine.db.entities.SchoolData;
import static com.codemovers.scholar.engine.helper.Utilities.tenantdata;
import com.codemovers.scholar.engine.helper.exceptions.BadRequestException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author mover 3/16/2018
 */
@Path("/")
public class StaffEdnpoint extends AbstractEndpoint<_Staff, StaffResponse> {

    private static final Logger LOG = Logger.getLogger(StaffEdnpoint.class.getName());
    @Context
    private ContainerRequestContext context;

    private StaffService service = null;
    private AuthenticationResponse authentication = null;

    public StaffEdnpoint() {
        service = new StaffService();
    }

    @Override
    public void validate(SchoolData schoolData, String authentication) throws Exception {
        this.authentication = UserService.getInstance().validateAuthentication(schoolData, authentication);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Override
    public StaffResponse create(_Staff entity, @HeaderParam("authentication") String authentication, @Context HttpServletRequest httpRequest) throws Exception {
        try {
            validate(tenantdata, authentication);
            return service.create(tenantdata, entity, this.authentication);
        } catch (BadRequestException exception) {
            throw exception;
        } catch (Exception ex) {
            Logger.getLogger(UsersEndpoint.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
            throw ex;
        }
    }

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Override
    public List<StaffResponse> list(
            @DefaultValue("0") @QueryParam("offset") int offset,
            @DefaultValue("50") @QueryParam("limit") int limit,
            @HeaderParam("authentication") String authentication,
            @Context HttpServletRequest httpRequest
    ) throws Exception {
        try {
            validate(tenantdata, authentication);
            String logId = context.getProperty("logId").toString();
            return service.list(tenantdata, offset, limit, this.authentication);
        } catch (WebApplicationException er) {
            throw er;
        } catch (Exception er) {
            er.printStackTrace();
            throw er;
        }
    }

    @Path("{id}")
    @Override
    public StaffResponse get(
            @QueryParam("id") int id,
            @HeaderParam("authentication") String authentication,
            @Context HttpServletRequest httpRequest) throws Exception {
        try {
            validate(tenantdata, authentication);
            String logId = context.getProperty("logId").toString();
            return service.getById(tenantdata, id, this.authentication);
        } catch (WebApplicationException er) {
            throw er;
        } catch (Exception er) {
            er.printStackTrace();
            throw er;
        }
    }

    @Override
    public StaffResponse update(_Staff entity,
            @HeaderParam("authentication") String authentication,
            @Context HttpServletRequest httpRequest) throws Exception {
        try {
            validate(tenantdata, authentication);
            String logId = context.getProperty("logId").toString();

            return null;
        } catch (WebApplicationException er) {
            throw er;
        } catch (Exception er) {
            er.printStackTrace();
            throw er;
        }

    }

    @POST
    @Path("/archive/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Override
    public StaffResponse archive(@QueryParam("id") Integer id,
            @HeaderParam("authentication") String authentication,
            @Context HttpServletRequest httpRequest) throws Exception {
        return super.archive(id, authentication, httpRequest); //To change body of generated methods, choose Tools | Templates.
    }
}
