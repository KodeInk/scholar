/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codemovers.scholar.engine.api.v1.accounts;

import com.codemovers.scholar.engine.api.v1.accounts.entities.AuthenticationResponse;
import com.codemovers.scholar.engine.api.v1.accounts.entities._Account;
import com.codemovers.scholar.engine.api.v1.accounts.entities._login;
import com.codemovers.scholar.engine.api.v1.users.UserService;
import com.codemovers.scholar.engine.db.entities.SchoolData;
import static com.codemovers.scholar.engine.helper.Utilities.tenantdata;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author mover 11/15/2017
 */
@Path("/")
public class AccountsEndpoint {

    private static final Logger LOG = Logger.getLogger(AccountsEndpoint.class.getName());

    @Context
    private ContainerRequestContext context;

    private AccountsService service = null;

    public AccountsEndpoint() {
        service = new AccountsService();
        // with the context issue, I can get headers off this context 
        // context.getHeaders()
    }

    //todo: find if the user is ligerly in 
    //todo: check permission to perform a given operation 
    public void validate(SchoolData schoolData, String authentication) throws Exception {
        UserService.getInstance().validateAuthentication(schoolData, authentication);
    }

    //todo: missing adding authentication 
    /**
     *
     * @param account
     * @param authentication
     * @param httpRequest
     * @return
     * @throws Exception
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public AccountResponse create(
            _Account account,
            @HeaderParam("authentication") String authentication,
            @Context HttpServletRequest httpRequest
    ) throws Exception {
        try {

            validate(tenantdata, authentication);
            String logId = context.getProperty("logId").toString();
            LOG.log(Level.INFO, " IF THIS WORKS {0} CELEBERATION ", tenantdata.getExternalId());
            return service.create(tenantdata, account);

        } catch (Exception er) {
            throw er;
        }

    }

    @POST
    @Path("login/")
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
        } catch (Exception er) {
            throw er;
        }

    }

    @POST
    @Path("reset/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public AuthenticationResponse resetPassword(
            @HeaderParam("schoolname") String schoolname, _login login,
            @Context HttpServletRequest httpRequest
    ) throws Exception {
        try {
            String logId = context.getProperty("logId").toString();
            LOG.log(Level.INFO, " IF THIS WORKS {0} CELEBERATION ", tenantdata.getExternalId());
            return service.login(tenantdata, login, logId);
        } catch (Exception er) {
            throw er;
        }

    }

    @POST
    @Path("deactivate/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public AuthenticationResponse deactiveAccount(
            @HeaderParam("schoolname") String schoolname, _login login,
            @Context HttpServletRequest httpRequest
    ) throws Exception {
        try {
            String logId = context.getProperty("logId").toString();
            LOG.log(Level.INFO, " IF THIS WORKS {0} CELEBERATION ", tenantdata.getExternalId());
            return service.login(tenantdata, login, logId);
        } catch (Exception er) {
            throw er;
        }

    }

    @POST
    @Path("dactivate/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public AuthenticationResponse activeAccount(
            @HeaderParam("schoolname") String schoolname, _login login,
            @Context HttpServletRequest httpRequest
    ) throws Exception {
        try {
            String logId = context.getProperty("logId").toString();
            LOG.log(Level.INFO, " IF THIS WORKS {0} CELEBERATION ", tenantdata.getExternalId());
            return service.login(tenantdata, login, logId);
        } catch (Exception er) {
            throw er;
        }

    }

}
