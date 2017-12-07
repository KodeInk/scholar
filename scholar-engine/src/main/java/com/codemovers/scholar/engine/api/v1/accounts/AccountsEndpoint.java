/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codemovers.scholar.engine.api.v1.accounts;

import com.codemovers.scholar.engine.api.v1.accounts.entities.AuthenticationResponse;
import com.codemovers.scholar.engine.api.v1.accounts.entities._login;
import com.codemovers.scholar.engine.db.entities.SchoolData;
import com.codemovers.scholar.engine.helper.Utilities;
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

    @POST
    @Path("login/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public AuthenticationResponse login(
            @HeaderParam("schoolname") String schoolname, _login login,
            @Context HttpServletRequest httpRequest
    ) throws Exception {
        SchoolData tenantdata = (SchoolData) context.getProperty("schoolname");

        String logId = context.getProperty("logId").toString();
        Utilities.logHttpServletRequest(httpRequest, logId);
        LOG.log(Level.INFO, "{0} :: start", new Object[]{logId});
        LOG.log(Level.INFO, "{0} :: start", tenantdata.getExternalId());

        return null;
        //return service.login(schoolname, login, logId);
    }

}
