/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codemovers.scholar.engine.api.v1.users;

import com.codemovers.scholar.engine.api.v1.abstracts.AbstractEndpoint;
import com.codemovers.scholar.engine.api.v1.accounts.entities.AuthenticationResponse;
import com.codemovers.scholar.engine.api.v1.users.entities._login;
import com.codemovers.scholar.engine.api.v1.users.entities.UserResponse;
import com.codemovers.scholar.engine.api.v1.users.entities._User;
import com.codemovers.scholar.engine.db.entities.SchoolData;
import static com.codemovers.scholar.engine.helper.Utilities.tenantdata;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author mover 12/6/2017
 */
@Path("/")
public class UsersEndpoint extends AbstractEndpoint<_User, UserResponse> {

    private static final Logger LOG = Logger.getLogger(UsersEndpoint.class.getName());
    @Context
    private ContainerRequestContext context;

    private UserService service = null;

    public UsersEndpoint() {
        service = new UserService();
    }

    @Override
    public void validate(SchoolData schoolData, String authentication) throws Exception {
        service.validateAuthentication(schoolData, authentication);
    }

    //todo: create user
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Override
    public UserResponse create(_User entity,
            @HeaderParam("authentication") String authentication,
            @Context HttpServletRequest httpRequest) throws Exception {
        validate(tenantdata, authentication);
        String logId = context.getProperty("logId").toString();
        return service.create(tenantdata, entity);

    }

    //todo: Update User
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public UserResponse update(
            @HeaderParam("authentication") String authentication,
            _User entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @param login
     * @param httpRequest
     * @return
     * @throws Exception
     */
    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public AuthenticationResponse login(
            _login login,
            @Context HttpServletRequest httpRequest
    ) throws Exception {

        try {
            String logId = context.getProperty("logId").toString();
            LOG.log(Level.INFO, " IF THIS WORKS {0} CELEBERATION ", tenantdata.getExternalId());
            return service.login(tenantdata, login, logId);
        } catch (WebApplicationException exception) {
            throw exception;
        } catch (Exception ex) {
            Logger.getLogger(UsersEndpoint.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
            throw ex;
        }

    }

    /**
     *
     * @param authentication
     * @param id
     * @param httpRequest
     * @return
     * @throws Exception
     */
    @POST
    @Path("/deactivate/{user_id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response deactivateAccount(
            @HeaderParam("authentication") String authentication,
            @PathParam("user_id") Integer id,
            @Context HttpServletRequest httpRequest
    ) throws Exception {
        try {
            validate(tenantdata, authentication);
            String logId = context.getProperty("logId").toString();
            LOG.log(Level.INFO, " IF THIS WORKS {0} CELEBERATION ", tenantdata.getExternalId());
            service.deactivate(tenantdata, id);
            return Response.ok().build();

        } catch (Exception er) {
            throw er;
        }

    }

    @POST
    @Path("/activate")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response activateAccount(
            @HeaderParam("authentication") String authentication,
            @PathParam("user_id") Integer id,
            @Context HttpServletRequest httpRequest
    ) throws Exception {
        try {
            validate(tenantdata, authentication);
            String logId = context.getProperty("logId").toString();
            LOG.log(Level.INFO, " IF THIS WORKS {0} CELEBERATION ", tenantdata.getExternalId());
            service.activate(tenantdata, id);
            return Response.ok().build();

        } catch (Exception er) {
            throw er;
        }

    }

}
