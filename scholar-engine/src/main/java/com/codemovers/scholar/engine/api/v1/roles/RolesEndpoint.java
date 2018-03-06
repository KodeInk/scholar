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
import com.codemovers.scholar.engine.api.v1.users.UsersEndpoint;
import com.codemovers.scholar.engine.db.entities.SchoolData;
import static com.codemovers.scholar.engine.helper.Utilities.tenantdata;
import com.codemovers.scholar.engine.helper.exceptions.BadRequestException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author mover 3/06/2018
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

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Override
    public RoleResponse create(_Role entity, String authentication, HttpServletRequest httpRequest) throws Exception {
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

    @Override
    public RoleResponse update(_Role entity, String authentication, HttpServletRequest httpRequest) throws Exception {
        return super.update(entity, authentication, httpRequest); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<RoleResponse> list(int start, int end, String authentication, HttpServletRequest httpRequest) throws Exception {
        return super.list(start, end, authentication, httpRequest); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RoleResponse archive(Integer id, String authentication, HttpServletRequest httpRequest) throws Exception {
        return super.archive(id, authentication, httpRequest); //To change body of generated methods, choose Tools | Templates.
    }

}
