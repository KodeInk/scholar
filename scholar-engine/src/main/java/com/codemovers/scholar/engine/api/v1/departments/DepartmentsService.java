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
import com.codemovers.scholar.engine.db.entities.Departments;
import com.codemovers.scholar.engine.db.entities.SchoolData;
import com.codemovers.scholar.engine.db.entities.Users;
import com.codemovers.scholar.engine.helper.enums.StatusEnum;
import com.codemovers.scholar.engine.helper.exceptions.BadRequestException;
import java.math.BigInteger;
import java.util.ArrayList;
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
        Departments parentDepartment = null;
        if (entity.getParent_id() != null) {
            parentDepartment = controller.findDepartment(entity.getParent_id(), data);
        }
        Departments department = populateEntity(entity, parentDepartment);
        department = controller.create(department, data);
        return populateResponse(department);
    }

    @Override
    public DepartmentResponse getById(SchoolData data, Integer Id, AuthenticationResponse authentication) throws Exception {

        Departments departments = controller.findDepartment(Id, data);
        return populateResponse(departments);
    }

    @Override
    public DepartmentResponse update(SchoolData data, _Department entity, AuthenticationResponse authentication) throws Exception {

        entity.validate();
        if (entity.getId() == null) {
            throw new BadRequestException("Missing Mandatory Field Id ");
        }
        Departments parentDepartment = null;
        if (entity.getParent_id() != null) {
            parentDepartment = controller.findDepartment(entity.getParent_id(), data);
            if (parentDepartment == null) {
                throw new BadRequestException(" Parent Department Does not exist");
            }
        }
        Departments department = controller.findDepartment(entity.getId(), data);
        department = populateEntity(department, entity, parentDepartment);
        controller.edit(department, data);
        return super.update(data, entity, authentication); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public DepartmentResponse archive(SchoolData data, Integer id, AuthenticationResponse authentication) throws Exception {
        Departments department = controller.findDepartment(id, data);
        department.setStatus(StatusEnum.ARCHIVED.toString());
        controller.edit(department, data);
        return populateResponse(department);
    }

    @Override
    public List<DepartmentResponse> list(SchoolData data, Integer ofset, Integer limit, AuthenticationResponse authentication) throws Exception {
        List<Departments> departments = controller.findDepartmentsEntities(limit, ofset, data);

        System.out.println(" Departments Size : " + departments.size());
        List<DepartmentResponse> departmentResponses = new ArrayList<>();
        if (departments != null) {
            for (Departments department : departments) {
                departmentResponses.add(populateResponse(department));
            }
        }
        return departmentResponses;
    }

    public static DepartmentResponse populateResponse(Departments entity) {

        DepartmentResponse response = new DepartmentResponse();

        response.setId(entity.getId().intValue());
        response.setName(entity.getName());
        response.setCode(entity.getCode());
        response.setDescription(entity.getDescription());
        response.setIsSystem(entity.getIsSystem());
        response.setStatus(entity.getStatus());
        response.setDate_created(entity.getDateCreated().getTime());
        if (entity.getAuthor() != null) {
            response.setAuthor(entity.getAuthor().getUsername());
        }

        if (entity.getParent() != null) {
            response.setParent(entity.getParent().getName());
        }

        return response;

    }

    public static Departments populateEntity(_Department entity, Departments parentDepartment) {
        Departments department = new Departments();
        department.setName(entity.getName());
        department.setCode(entity.getCode());
        department.setDescription(entity.getDescription());
        department.setIsSystem(entity.getIsSystem());
        department.setDateCreated(entity.getDate_created());
        department.setAuthor(new Users(entity.getAuthor_id().longValue()));
        if (parentDepartment != null) {
            department.setParent(parentDepartment);
        }
        return department;
    }

    public static Departments populateEntity(Departments department, _Department entity, Departments parentDepartment) throws BadRequestException {

        if (department == null) {
            throw new BadRequestException(" Department Does not Exist ");
        }

        if (entity.getName() != null) {
            department.setName(entity.getName());
        }
        if (entity.getCode() != null) {
            department.setCode(entity.getCode());
        }
        if (entity.getDescription() != null) {
            department.setDescription(entity.getDescription());
        }
        if (entity.getIsSystem() != null) {
            department.setIsSystem(entity.getIsSystem());
        }
        if (entity.getDate_created() != null) {
            department.setDateCreated(entity.getDate_created());
        }
        if (entity.getAuthor_id() != null) {
            department.setAuthor(new Users(entity.getAuthor_id().longValue()));
        }

        if (entity.getParent_id() != null) {
            department.setParent(parentDepartment);
        }

        return department;
    }

}
