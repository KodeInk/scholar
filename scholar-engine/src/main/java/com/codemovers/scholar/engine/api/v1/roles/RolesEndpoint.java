/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codemovers.scholar.engine.api.v1.roles;

import com.codemovers.scholar.engine.api.v1.abstracts.AbstractEndpoint;
import com.codemovers.scholar.engine.api.v1.accounts.entities.AuthenticationResponse;
import com.codemovers.scholar.engine.api.v1.roles.entities.RoleResponse;
import com.codemovers.scholar.engine.api.v1.roles.entities._Role;
import com.codemovers.scholar.engine.api.v1.users.UserService;
import com.codemovers.scholar.engine.api.v1.users.UserServiceInterface;
import com.codemovers.scholar.engine.db.entities.SchoolData;
import java.util.logging.Logger;
import javax.ws.rs.Path;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.Context;

/**
 *
 * @author mover
 */
@Path("/")
public class RolesEndpoint extends AbstractEndpoint<_Role, RoleResponse> {
    private static final Logger LOG = Logger.getLogger(RolesEndpoint.class.getName());
    @Context
    private ContainerRequestContext context;

    private RolesService service = null;
    private AuthenticationResponse authentication = null;

    public RolesEndpoint() {
        service = new RolesService();
    }

    @Override
    public void validate(SchoolData schoolData, String authentication) throws Exception {
        this.authentication = UserService.getInstance().validateAuthentication(schoolData, authentication);
    }
}
