/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codemovers.scholar.engine.api.v1.permissions;

import com.codemovers.scholar.engine.api.v1.abstracts.AbstractService;
import com.codemovers.scholar.engine.api.v1.permissions.entities.PermissionsResponse;
import com.codemovers.scholar.engine.api.v1.permissions.entities._Permission;
import com.codemovers.scholar.engine.api.v1.roles.RolesService;
import com.codemovers.scholar.engine.db.controllers.RolesJpaController;

/**
 *
 * @author mover 5/19/2018
 */
public class PermissionsService  extends AbstractService<_Permission, PermissionsResponse> {
    
     private final Permission controller;

    private static PermissionsService service = null;
    
}
