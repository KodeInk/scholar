/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codemovers.scholar.engine.api.v1.students.registration;

import com.codemovers.scholar.engine.api.v1.abstracts.AbstractService;
import com.codemovers.scholar.engine.api.v1.accounts.entities.AuthenticationResponse;
import com.codemovers.scholar.engine.api.v1.classes.ClassService;
import com.codemovers.scholar.engine.api.v1.students.admissions.AdmissionService;
import com.codemovers.scholar.engine.api.v1.students.registration.entities.TermRegistrationResponse;
import com.codemovers.scholar.engine.api.v1.students.registration.entities._TermRegistration;
import com.codemovers.scholar.engine.api.v1.terms.TermService;
import com.codemovers.scholar.engine.db.controllers.StudentTermRegistrationJpaController;
import com.codemovers.scholar.engine.db.entities.Classes;
import com.codemovers.scholar.engine.db.entities.SchoolData;
import com.codemovers.scholar.engine.db.entities.StudentAdmission;
import com.codemovers.scholar.engine.db.entities.StudentTermRegistration;
import com.codemovers.scholar.engine.db.entities.Terms;
import com.codemovers.scholar.engine.db.entities.Users;
import com.codemovers.scholar.engine.helper.enums.StatusEnum;
import com.codemovers.scholar.engine.helper.exceptions.BadRequestException;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

/**
 *
 * @author mover
 */
public class TermRegistrationService extends AbstractService<_TermRegistration, TermRegistrationResponse> {
    
    private static final Logger LOG = Logger.getLogger(AdmissionService.class.getName());
    private final StudentTermRegistrationJpaController controller;
    private static TermRegistrationService instance = null;
    
    public TermRegistrationService() {
        controller = StudentTermRegistrationJpaController.getInstance();
    }
    
    public static TermRegistrationService getInstance() {
        if (instance == null) {
            instance = new TermRegistrationService();
        }
        
        return instance;
        
    }
    
    @Override
    public TermRegistrationResponse create(SchoolData data, _TermRegistration entity, AuthenticationResponse authentication) throws Exception {
        //todo: validate
        entity.validate();
        entity.setDate_registered(entity.getDate_registered() != null ? entity.getDate_registered() : new Date().getTime());
        entity.setAuthor_id(authentication.getId());
        entity.setStatus(StatusEnum.ACTIVE.name());
        
        Classes registrationClass = validateClassOfRegistration(entity, data);
        Terms registrationTerm = validateTermOfRegistration(entity, data);
        
        StudentAdmission admission = validateStudentAdmission(data, entity, authentication);

        //todo: populate entity
        StudentTermRegistration termRegistration = new StudentTermRegistration();
        termRegistration.setDateCreated(new Date());
        termRegistration.setRegistration_Class(registrationClass);
        termRegistration.setRegistration_term(registrationTerm);
        termRegistration.setStudent_Admission(admission);
        termRegistration.setDateRegistered(new Date(entity.getDate_registered()));
        termRegistration.setDateCreated(new Date());
        termRegistration.setStatus(entity.getStatus());
        termRegistration.setAuthor(new Users(entity.getAuthor_id().longValue()));
        //todo: check if student already registered for this term
        //todo: create entity
        //todo: populate response 
        return super.create(data, entity, authentication); //To change body of generated methods, choose Tools | Templates.
    }
    
    public StudentAdmission validateStudentAdmission(SchoolData data, _TermRegistration entity, AuthenticationResponse authentication) throws Exception, BadRequestException {
        //todo: get Admission By Id
        StudentAdmission admission = AdmissionService.getInstance().getAdmission(data, entity.getAddmision_id(), authentication);
        if (admission == null) {
            throw new BadRequestException("Student Admission   does not exist in the system");
        }
        return admission;
    }
    
    public Terms validateTermOfRegistration(_TermRegistration entity, SchoolData data) throws BadRequestException {
        //todo: validate admission term
        Terms registrationTerm = TermService.getInstance().getTerm(entity.getTerm_id(), data);
        if (registrationTerm == null) {
            throw new BadRequestException("Term  does not exist in the system");
        }
        return registrationTerm;
    }
    
    public Classes validateClassOfRegistration(_TermRegistration entity, SchoolData data) throws BadRequestException {
        //todo: check  addmission class
        Classes registrationClass = ClassService.getInstance().getClass(entity.getClass_id(), data);
        if (registrationClass == null) {
            throw new BadRequestException("Class does not exist in the system");
        }
        return registrationClass;
    }
    
    @Override
    public TermRegistrationResponse update(SchoolData data, _TermRegistration entity, AuthenticationResponse authentication) throws Exception {
        return super.update(data, entity, authentication); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public TermRegistrationResponse getById(SchoolData data, Integer Id, AuthenticationResponse authentication) throws Exception {
        return super.getById(data, Id, authentication); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public List<TermRegistrationResponse> list(SchoolData data, Integer ofset, Integer limit, AuthenticationResponse authentication) throws Exception {
        return super.list(data, ofset, limit, authentication); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public TermRegistrationResponse archive(SchoolData data, Integer id, AuthenticationResponse authentication) throws Exception {
        return super.archive(data, id, authentication); //To change body of generated methods, choose Tools | Templates.
    }
    
}
