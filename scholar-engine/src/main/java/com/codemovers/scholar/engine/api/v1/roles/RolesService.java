/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codemovers.scholar.engine.api.v1.roles;

import com.codemovers.scholar.engine.api.v1.abstracts.AbstractService;
import com.codemovers.scholar.engine.api.v1.roles.entities.RoleResponse;
import com.codemovers.scholar.engine.api.v1.roles.entities._Role;
import com.codemovers.scholar.engine.db.controllers.RolesJpaController;
import com.codemovers.scholar.engine.db.entities.Roles;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Manny
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
    public RoleResponse create(_Role entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RoleResponse getById(Integer Id) throws Exception {
        // controller.find(Id);

        return null;
    }

    public Roles getRoleByName(String name) throws Exception {

//        Roles r = null;
//        try {
//            List<Roles> list = controller.findByName(name);
//            if (list != null) {
//                r = list.get(0);
//            }
//        } catch (Exception er) {
//            LOG.log(Level.INFO, er.toString());
//            throw er;
//        }
//
//        return r;
        return null;
    }

}
