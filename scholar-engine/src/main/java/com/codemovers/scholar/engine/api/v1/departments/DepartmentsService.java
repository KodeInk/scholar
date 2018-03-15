/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codemovers.scholar.engine.api.v1.departments;

import com.codemovers.scholar.engine.api.v1.abstracts.AbstractService;
import com.codemovers.scholar.engine.api.v1.accounts.entities.AuthenticationResponse;
import com.codemovers.scholar.engine.api.v1.departments.entities.DepartmentResponse;
import com.codemovers.scholar.engine.api.v1.departments.entities._Department;
import com.codemovers.scholar.engine.db.controllers.DepartmentsJpaController;
import com.codemovers.scholar.engine.db.entities.SchoolData;
import java.util.List;
import java.util.logging.Logger;

/**
 *
 * @author mover 3/15/2018
 */
public class DepartmentsService extends AbstractService<_Department, DepartmentResponse> {
    private static final Logger LOG = Logger.getLogger(DepartmentsService.class.getName());
    private DepartmentsJpaController controller;
    private static DepartmentsService service = null;

    public DepartmentsService() {
        controller = DepartmentsJpaController.getInstance();
    }

    @Override
    public DepartmentResponse create(SchoolData data, _Department entity, AuthenticationResponse authentication) throws Exception {
        return super.create(data, entity, authentication); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public DepartmentResponse getById(SchoolData data, Integer Id, AuthenticationResponse authentication) throws Exception {
        return super.getById(data, Id, authentication); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public DepartmentResponse update(SchoolData data, _Department entity, AuthenticationResponse authentication) throws Exception {
        return super.update(data, entity, authentication); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public DepartmentResponse archive(SchoolData data, Integer id, AuthenticationResponse authentication) throws Exception {
        return super.archive(data, id, authentication); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<DepartmentResponse> list(SchoolData data, Integer ofset, Integer limit, AuthenticationResponse authentication) throws Exception {
        return super.list(data, ofset, limit, authentication); //To change body of generated methods, choose Tools | Templates.
    }


}
