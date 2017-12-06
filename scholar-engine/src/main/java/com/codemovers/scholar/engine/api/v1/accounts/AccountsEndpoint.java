/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codemovers.scholar.engine.api.v1.accounts;

import com.codemovers.scholar.v1.backoffice.api.v1.abstracts.AbstractEndpoint;
import com.codemovers.scholar.v1.backoffice.api.v1.accounts.entities.AuthenticationResponse;
import com.codemovers.scholar.v1.backoffice.api.v1.accounts.entities._Account;
import com.codemovers.scholar.v1.backoffice.api.v1.accounts.entities._login;
import com.codemovers.scholar.v1.backoffice.helper.Utilities;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author mover 11/15/2017
 */
@Path("/")
public class AccountsEndpoint extends AbstractEndpoint<_Account, AccountResponse> {

    private static final Logger LOG = Logger.getLogger(AccountsEndpoint.class.getName());

    @Context
    private ContainerRequestContext context;

    private AccountsService service = null;

    public AccountsEndpoint() {
        service = AccountsService.getInstance();
    }


    @POST
    @Path("login/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public AuthenticationResponse login(
            _login login,
            @Context HttpServletRequest httpRequest
    ) throws Exception {
        String logId = context.getProperty("logId").toString();
        Utilities.logHttpServletRequest(httpRequest, logId);
        LOG.log(Level.INFO, "{0} :: start", new Object[]{logId});
        return service.login(login, logId);
    }


}
