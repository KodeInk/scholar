/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codemovers.scholar.engine.api.v1.departments;

import com.codemovers.scholar.engine.api.v1.abstracts.AbstractService;
import com.codemovers.scholar.engine.api.v1.accounts.entities.AuthenticationResponse;
import com.codemovers.scholar.engine.api.v1.curriculum.CurriculumService;
import com.codemovers.scholar.engine.api.v1.departments.entities.DepartmentResponse;
import com.codemovers.scholar.engine.api.v1.departments.entities._Department;
import com.codemovers.scholar.engine.db.controllers.DepartmentsJpaController;
import com.codemovers.scholar.engine.db.entities.Departments;
import com.codemovers.scholar.engine.db.entities.SchoolData;
import com.codemovers.scholar.engine.db.entities.Users;
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

    public static DepartmentsService getInstance() {
        if (service == null) {
            service = new DepartmentsService();
        }
        return service;
    }

    @Override
    public DepartmentResponse create(SchoolData data, _Department entity, AuthenticationResponse authentication) throws Exception {
        entity.validate();
        Departments department = populateEntity(entity);
        department = controller.create(department, data);
        return populateResponse(department);
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

    public static DepartmentResponse populateResponse(Departments entity) {

        DepartmentResponse response = new DepartmentResponse();

        response.setId(entity.getId().intValue());
        response.setName(entity.getName());
        response.setDescription(entity.getDescription());
        response.setIsSystem(entity.getIsSystem());
        response.setStatus(entity.getStatus());
        response.setDate_created(entity.getDateCreated().getTime());
        if (entity.getAuthor() != null) {
            response.setAuthor(entity.getAuthor().getUsername());
        }

        return response;

    }

    public static Departments populateEntity(_Department entity) {
        Departments department = new Departments();
        department.setName(entity.getName());
        department.setDescription(entity.getDescription());
        department.setIsSystem(entity.getIsSystem());
        department.setDateCreated(entity.getDate_created());
        department.setAuthor(new Users(entity.getAuthor_id().longValue()));
        return department;
    }

}
