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
        Roles role = populateBasicRole(entity);
        //todo: check if there is no Role by name or code in the system
        //todo: create Role 
        return super.create(data, entity, authentication); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @param schoolData
     * @param name
     * @return
     * @throws Exception
     */
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

    /**
     *
     * @param data
     * @param Id
     * @param authentication
     * @return
     * @throws Exception
     */
    @Override
    public RoleResponse getById(SchoolData data, Integer Id, AuthenticationResponse authentication) throws Exception {
        //todo:  make sure the user has permissions to make this function 
        Roles role = controller.findRole(Id, data);
        return populateResponse(role, false);
    }

    /**
     *
     * @param data
     * @param ofset
     * @param limit
     * @param authentication
     * @return
     * @throws Exception
     */
    @Override
    public List<RoleResponse> list(SchoolData data, Integer ofset, Integer limit, AuthenticationResponse authentication) throws Exception {
        //todo:  make sure the user has permissions to make this function 
        List<Roles> list = controller.findRoles(ofset, limit, data);
        List<RoleResponse> roleResponses = new ArrayList();
        if (list != null) {
            for (Roles r : list) {
                roleResponses.add(populateResponse(r, false));
            }
        }
        return roleResponses;
    }

    /**
     *
     * @param role
     * @param extended
     * @return
     */
    public static RoleResponse populateResponse(Roles role, Boolean extended) {
        RoleResponse roleResponse = new RoleResponse();

        if (role != null) {
            roleResponse.setDescription(role.getDescription());
            roleResponse.setIsSystem(role.getIsSystem() == 1);
            roleResponse.setName(role.getName());

            if (extended == true) {

                if (role.getPermissions() != null) {
                    List<PermissionsResponse> permissionsResponses = new ArrayList<>();
                    for (Permissions p : role.getPermissions()) {
                        PermissionsResponse permissionsResponse = new PermissionsResponse();
                        permissionsResponse.setCode(p.getCode());
                        permissionsResponse.setName(p.getName());
                        permissionsResponses.add(permissionsResponse);
                    }
                    PermissionsResponse[] prs = new PermissionsResponse[permissionsResponses.size()];
                    roleResponse.setPermissions(permissionsResponses.toArray(prs));
                }
            }
        }

        return roleResponse;
    }

    /**
     *
     * @param entity
     * @return
     */
    public static Roles populateBasicRole(_Role entity) {

        Roles role = new Roles();
        if (entity != null) {
            role.setName(entity.getName());
            role.setCode(entity.getCode());
            role.setDescription(entity.getDescription());
            role.setIsSystem(Short.valueOf((entity.isIsSystem() == true ? "1" : "0")));

        }

        return role;
    }

}
