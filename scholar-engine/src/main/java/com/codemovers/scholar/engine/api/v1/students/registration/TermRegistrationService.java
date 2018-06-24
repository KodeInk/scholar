/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codemovers.scholar.engine.api.v1.students.registration;

import com.codemovers.scholar.engine.api.v1.abstracts.AbstractService;
import com.codemovers.scholar.engine.api.v1.accounts.entities.AuthenticationResponse;
import com.codemovers.scholar.engine.api.v1.classes.ClassService;
import com.codemovers.scholar.engine.api.v1.classes.entities.ClassResponse;
import com.codemovers.scholar.engine.api.v1.streams.entities.StreamResponse;
import com.codemovers.scholar.engine.api.v1.students.admissions.AdmissionService;
import com.codemovers.scholar.engine.api.v1.students.admissions.entities.AdmissionResponse;
import com.codemovers.scholar.engine.api.v1.students.registration.entities.TermRegistrationResponse;
import com.codemovers.scholar.engine.api.v1.students.registration.entities._TermRegistration;
import com.codemovers.scholar.engine.api.v1.terms.TermService;
import com.codemovers.scholar.engine.api.v1.terms.entities.TermResponse;
import com.codemovers.scholar.engine.db.controllers.StudentTermRegistrationJpaController;
import com.codemovers.scholar.engine.db.entities.Classes;
import com.codemovers.scholar.engine.db.entities.SchoolData;
import com.codemovers.scholar.engine.db.entities.StudentAdmission;
import com.codemovers.scholar.engine.db.entities.StudentTermRegistration;
import com.codemovers.scholar.engine.db.entities.Terms;
import com.codemovers.scholar.engine.db.entities.Users;
import com.codemovers.scholar.engine.helper.enums.StatusEnum;
import com.codemovers.scholar.engine.helper.exceptions.BadRequestException;
import java.util.ArrayList;
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
        try {
        //todo: validate
            entity.validate();
            entity.setDate_registered(entity.getDate_registered() != null ? entity.getDate_registered() : new Date().getTime());
            entity.setAuthor_id(authentication.getId());
            entity.setStatus(StatusEnum.ACTIVE.name());

            Classes registrationClass = validateClassOfRegistration(entity, data);
            Terms registrationTerm = validateTermOfRegistration(entity, data);

            StudentAdmission admission = validateStudentAdmission(data, entity, authentication);

            StudentTermRegistration termRegistration = populateEntity(registrationClass, registrationTerm, admission, entity);

            validateIfAlreadyRegistered(registrationClass, registrationTerm, admission, data);
            //todo: create entity
            StudentTermRegistration studentTermRegistration = controller.create(termRegistration, data);

            TermRegistrationResponse registrationResponse = populateResponse(studentTermRegistration);

            return registrationResponse;
        } catch (Exception er) {
            er.printStackTrace();
            throw er;
        }

    }

    @Override
    public TermRegistrationResponse update(SchoolData data, _TermRegistration entity, AuthenticationResponse authentication) throws Exception {
        entity.validate();
        if (entity.getId() == null) {
            throw new BadRequestException("missing mandatory field ID");
        }

        Classes registrationClass = validateClassOfRegistration(entity, data);
        Terms registrationTerm = validateTermOfRegistration(entity, data);

        StudentAdmission admission = validateStudentAdmission(data, entity, authentication);

        StudentTermRegistration termRegistration = populateEntity(registrationClass, registrationTerm, admission, entity);

        termRegistration = controller.edit(termRegistration, data);

        TermRegistrationResponse registrationResponse = populateResponse(termRegistration);

        return registrationResponse;
    }

    @Override
    public TermRegistrationResponse getById(SchoolData data, Integer Id, AuthenticationResponse authentication) throws Exception {
        try {
            StudentTermRegistration termRegistration = controller.findStudentTermRegistration(Id, data);

            return populateResponse(termRegistration);
        } catch (Exception er) {
            er.printStackTrace();
            throw er;
        }

    }

    @Override
    public List<TermRegistrationResponse> list(SchoolData data, Integer ofset, Integer limit, AuthenticationResponse authentication) throws Exception {
        List<StudentTermRegistration> registrations = controller.findStudentTermRegistrations(limit, ofset, data);

        List<TermRegistrationResponse> registrationResponses = new ArrayList<>();

        registrations.forEach((StudentTermRegistration registration) -> {
            registrationResponses.add(populateResponse(registration));
        });

        return registrationResponses;
    }

    @Override
    public TermRegistrationResponse archive(SchoolData data, Integer id, AuthenticationResponse authentication) throws Exception {
        return super.archive(data, id, authentication); //To change body of generated methods, choose Tools | Templates.
    }

    public StudentTermRegistration populateEntity(Classes registrationClass, Terms registrationTerm, StudentAdmission admission, _TermRegistration entity) {
        //todo: populate entity
        StudentTermRegistration termRegistration = new StudentTermRegistration();
        termRegistration.setDateCreated(new Date());
        termRegistration.setRegistrationClass(registrationClass);
        termRegistration.setRegistrationTerm(registrationTerm);
        termRegistration.setStudentAdmission(admission);
        termRegistration.setDateRegistered(new Date(entity.getDate_registered()));
        termRegistration.setDateCreated(new Date());
        termRegistration.setStatus(entity.getStatus());
        termRegistration.setAuthor(new Users(entity.getAuthor_id().longValue()));
        return termRegistration;
    }

    public StudentTermRegistration validateIfAlreadyRegistered(Classes registrationClass, Terms registrationTerm, StudentAdmission admission, SchoolData data) throws BadRequestException {
        //todo: check if student already registered for this term
        /*
        We are checking by  Term, Class, admission
         */
        StudentTermRegistration registration = controller.findStudentByTermAndClassAndAdmission(registrationClass.getId(), registrationTerm.getId(), admission.getId(), data);
        if (registration != null) {
            throw new BadRequestException("Student Already Registered ");
        }
        return registration;
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

    public TermRegistrationResponse populateResponse(StudentTermRegistration studentTermRegistration) {
        if (studentTermRegistration != null) {
            AdmissionResponse admissionResponse = null;
            if (studentTermRegistration.getStudentAdmission() != null) {
                admissionResponse = AdmissionService.getInstance().populateResponse(studentTermRegistration.getStudentAdmission());
            }

            TermResponse termResponse = TermService.getInstance().populateResponse(studentTermRegistration.getRegistrationTerm());
            ClassResponse classResponse = ClassService.getInstance().populateResponse(studentTermRegistration.getRegistrationClass());
            StreamResponse streamResponse = null;
            TermRegistrationResponse registrationResponse = new TermRegistrationResponse();
            registrationResponse.setAdmission(admissionResponse);
            registrationResponse.setStudentClass(classResponse);
            registrationResponse.setStudentTerm(termResponse);
            registrationResponse.setStudentStream(streamResponse);
            registrationResponse.setId(studentTermRegistration.getId().intValue());
            registrationResponse.setAuthor(studentTermRegistration.getAuthor().getUsername());
            registrationResponse.setStatus(studentTermRegistration.getStatus());
            registrationResponse.setDate_created(studentTermRegistration.getDateCreated().getTime());
            registrationResponse.setDate_registered(studentTermRegistration.getDateRegistered().getTime());
            return registrationResponse;
        }
        return null;
    }

}
