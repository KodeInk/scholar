/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codemovers.scholar.engine.api.v1.permissions;

import com.codemovers.scholar.engine.api.v1.abstracts.AbstractEndpoint;
import com.codemovers.scholar.engine.api.v1.permissions.entities.PermissionsResponse;
import com.codemovers.scholar.engine.api.v1.permissions.entities._Permission;
import com.codemovers.scholar.engine.api.v1.roles.RolesEndpoint;
import java.util.logging.Logger;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.Context;

/**
 *
 * @author mover 5/19/2018
 */
public class PermissionsEndpoint   extends AbstractEndpoint<_Permission, PermissionsResponse> {
    
     private static final Logger LOG = Logger.getLogger(PermissionsEndpoint.class.getName());
    @Context
    private ContainerRequestContext context;
}
