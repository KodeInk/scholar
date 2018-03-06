/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codemovers.scholar.engine.api.v1.roles;

import com.codemovers.scholar.engine.api.v1.abstracts.AbstractService;
import com.codemovers.scholar.engine.api.v1.accounts.entities.AuthenticationResponse;
import com.codemovers.scholar.engine.api.v1.roles.entities.PermissionsResponse;
import com.codemovers.scholar.engine.api.v1.roles.entities.RoleResponse;
import com.codemovers.scholar.engine.api.v1.roles.entities._Role;
import com.codemovers.scholar.engine.db.controllers.RolesJpaController;
import com.codemovers.scholar.engine.db.entities.Permissions;
import com.codemovers.scholar.engine.db.entities.Roles;
import com.codemovers.scholar.engine.db.entities.SchoolData;
import java.util.ArrayList;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mover 3/6/2018
 */
public class RolesService extends AbstractService<_Role, RoleResponse> {

    private static final Logger LOG = Logger.getLogger(RolesService.class.getName());

    private final RolesJpaController controller;

    private static RolesService service = null;

    public RolesService() {
        controller = RolesJpaController.getInstance();
    }

    public static RolesService getInstance() {
        if (service == null) {
            service = new RolesService();
        }
        return service;
    }

    @Override
    public RoleResponse create(SchoolData data, _Role entity, AuthenticationResponse authentication) throws Exception {

        entity.validate();

        //todo: check if there is no Role by name or code in the system
        //todo: create Role 

        return super.create(data, entity, authentication); //To change body of generated methods, choose Tools | Templates.
    }


    @Override
    public RoleResponse getById(Integer Id) throws Exception {
        // controller.find(Id);

        return null;
    }

    public Roles getRoleByName(SchoolData schoolData, String name) throws Exception {

        Roles r = null;
        try {
            List<Roles> list = controller.findByName(name, schoolData);
            if (list != null) {
                r = list.get(0);
            }
        } catch (Exception er) {
            LOG.log(Level.INFO, er.toString());
            throw er;
        }

        return r;

    }

    @Override
    public List<RoleResponse> list(SchoolData data, Integer ofset, Integer limit, AuthenticationResponse authentication) throws Exception {
        List<Roles> list = controller.findRoles(ofset, limit, data);
        return null;
    }

    /**
     *
     * @param _role
     * @param extended
     * @return
     */
    public static RoleResponse populateResponse(Roles _role, Boolean extended) {
        RoleResponse roleResponse = new RoleResponse();
        roleResponse.setDescription(_role.getDescription());
        roleResponse.setIsSystem(_role.getIsSystem() == 1);
        roleResponse.setName(_role.getName());

        if (extended == true) {

            if (_role.getPermissions() != null) {
                List<PermissionsResponse> permissionsResponses = new ArrayList<>();
                for (Permissions p : _role.getPermissions()) {
                    PermissionsResponse permissionsResponse = new PermissionsResponse();
                    permissionsResponse.setCode(p.getCode());
                    permissionsResponse.setName(p.getName());
                    permissionsResponses.add(permissionsResponse);
                }
                PermissionsResponse[] prs = new PermissionsResponse[permissionsResponses.size()];
                roleResponse.setPermissions(permissionsResponses.toArray(prs));
            }
        }

        return roleResponse;
    }

}
