/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codemovers.scholar.engine.api.v1.students.termregistration;

import com.codemovers.scholar.engine.api.v1.abstracts.AbstractService;
import com.codemovers.scholar.engine.api.v1.accounts.entities.AuthenticationResponse;
import com.codemovers.scholar.engine.api.v1.admissions.AdmissionService;
import com.codemovers.scholar.engine.api.v1.admissions.entities.AdmissionResponse;
import com.codemovers.scholar.engine.api.v1.classes.ClassService;
import com.codemovers.scholar.engine.api.v1.classes.entities.ClassResponse;
import com.codemovers.scholar.engine.api.v1.registrations.entities.StudentRegistrationResponse;
import com.codemovers.scholar.engine.api.v1.streams.StreamsService;
import com.codemovers.scholar.engine.api.v1.streams.entities.StreamResponse;
import com.codemovers.scholar.engine.api.v1.students.admission.StudentAdmissionService;
import com.codemovers.scholar.engine.api.v1.students.admission.entities.StudentAdmissionResponse;
import com.codemovers.scholar.engine.api.v1.students.termregistration.entities.StudentTermRegistrationResponse;
import com.codemovers.scholar.engine.api.v1.students.termregistration.entities._StudentTermRegistration;
import com.codemovers.scholar.engine.api.v1.terms.TermService;
import com.codemovers.scholar.engine.api.v1.terms.entities.TermResponse;
import com.codemovers.scholar.engine.db.controllers.ClassJpaController;
import com.codemovers.scholar.engine.db.controllers.StreamsJpaController;
import com.codemovers.scholar.engine.db.controllers.StudentAdmissionJpaController;
import com.codemovers.scholar.engine.db.controllers.StudentTermRegistrationJpaController;
import com.codemovers.scholar.engine.db.controllers.TermsJpaController;
import com.codemovers.scholar.engine.db.entities.Classes;
import com.codemovers.scholar.engine.db.entities.SchoolData;
import com.codemovers.scholar.engine.db.entities.Streams;
import com.codemovers.scholar.engine.db.entities.StudentAdmission;
import com.codemovers.scholar.engine.db.entities.StudentTermRegistration;
import com.codemovers.scholar.engine.db.entities.Terms;
import com.codemovers.scholar.engine.db.entities.Users;
import static com.codemovers.scholar.engine.helper.Utilities.check_access;
import com.codemovers.scholar.engine.helper.enums.StatusEnum;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.ws.rs.BadRequestException;

/**
 *
 * @author mover 1/1/2018
 */
public class StudentTermRegistrationService extends AbstractService<_StudentTermRegistration, StudentTermRegistrationResponse> implements StudentTermRegistrationServiceInterface {

    private static final Logger LOG = Logger.getLogger(StreamsService.class.getName());
    private final StudentTermRegistrationJpaController controller;
    private static StudentTermRegistrationService service = null;


    public StudentTermRegistrationService() {
        controller = StudentTermRegistrationJpaController.getInstance();
    }

    public static StudentTermRegistrationService getInstance() {
        if (service == null) {
            service = new StudentTermRegistrationService();
        }
        return service;
    }

    @Override
    public StudentTermRegistrationResponse create(SchoolData data, _StudentTermRegistration entity, AuthenticationResponse authentication) throws Exception {
        check_access(REGISTER_STUDENT_TERM_PERMISSION);
        entity.validate();

        entity.setAuthor_id(authentication.getId());
        entity.setStatus(StatusEnum.ACTIVE);

        //todo: get the admission by id;
          StudentAdmission admission = StudentAdmissionJpaController.getInstance().findStudentAdmission(entity.getAdmission_id(), data);
        //TODO: get term by id
        Terms registration_term = TermsJpaController.getInstance().findTerm(entity.getTerm_id(), data);
        //TODO: get term by id
        Classes RegistrationClass = ClassJpaController.getInstance().findClass(entity.getClass_id(), data);

        //Todo: get Stream by Id
        Streams RegistrationStream = StreamsJpaController.getInstance().findStream(entity.getStream_id(), data);


        StudentTermRegistration registration = new StudentTermRegistration();

        registration.setRegistration_term(registration_term);
        registration.setRegistration_Class(RegistrationClass);
        registration.setRegistration_Stream(RegistrationStream);
        registration.setStudent_Admission(admission);

        //todo: add user
        registration.setAuthor(new Users(entity.getAuthor_id().longValue()));
        registration.setStatus(entity.getStatus().toString());

        registration = controller.create(registration, data);

        return populateResponse(registration);
    }

    @Override
    public StudentTermRegistrationResponse update(SchoolData data, _StudentTermRegistration entity, AuthenticationResponse authentication) throws Exception {
        check_access(UPDATE_STUDENT_TERM_REGISTRATION_PERMISSION);
        entity.validate();

        if (entity.getId() == null) {
            throw new BadRequestException("UNIQUE ID MISSING");
        }

        StudentTermRegistration registration = controller.findStudentTermRegistration(entity.getId(), data);

        if (registration == null) {
            throw new BadRequestException("RECORD DOES NOT EXIST");
        }

        //todo: get the admission by id;
        StudentAdmission admission = StudentAdmissionJpaController.getInstance().findStudentAdmission(entity.getAdmission_id(), data);
        //TODO: get term by id
        Terms registration_term = TermsJpaController.getInstance().findTerm(entity.getTerm_id(), data);
        //TODO: get term by id
        Classes RegistrationClass = ClassJpaController.getInstance().findClass(entity.getClass_id(), data);

        //Todo: get Stream by Id
        Streams RegistrationStream = StreamsJpaController.getInstance().findStream(entity.getStream_id(), data);

        registration.setRegistration_term(registration_term);
        registration.setRegistration_Class(RegistrationClass);
        registration.setRegistration_Stream(RegistrationStream);
        registration.setStudent_Admission(admission);

        controller.edit(registration, data);
        return populateResponse(registration);
    }

    @Override
    public StudentTermRegistrationResponse archive(SchoolData data, Integer id, AuthenticationResponse authentication) throws Exception {

        check_access(ARCHIVE_STUDENT_TERM_REGISTRATION_PERMISSION);

        StudentTermRegistration registration = controller.findStudentTermRegistration(id, data);
        if (registration == null) {
            throw new BadRequestException("RECORD DOES NOT EXIST");
        }

        registration.setStatus(StatusEnum.ARCHIVED.toString());

        registration = controller.edit(registration, data);

        return populateResponse(registration);
    }

    @Override
    public StudentTermRegistrationResponse getById(SchoolData data, Integer Id) throws Exception {
        check_access(LIST_STUDENT_TERM_REGISTRATION_PERMISSION);
        StudentTermRegistration registration = controller.findStudentTermRegistration(Id, data);
        if (registration == null) {
            throw new BadRequestException("RECORD DOES NOT EXIST");
        }

        return populateResponse(registration);
    }

    @Override
    public List<StudentTermRegistrationResponse> list(SchoolData data, Integer ofset, Integer limit, AuthenticationResponse authentication) throws Exception {
        check_access(LIST_STUDENT_TERM_REGISTRATION_PERMISSION);
        List<StudentTermRegistration> list = controller.findStudentTermRegistrations(ofset, limit, data);
        List<StudentTermRegistrationResponse> responses = new ArrayList<>();
        if (list != null) {

            list.forEach((registration) -> {
                responses.add(populateResponse(registration));
            });
        }

        return responses;

    }

    @Override
    public StudentTermRegistrationResponse populateResponse(StudentTermRegistration entity) {
        StudentTermRegistrationResponse response = new StudentTermRegistrationResponse();

        response.setId(entity.getId().intValue());

        //AdmissionResponse
        if (response.getStudentAdmission() != null) {
            StudentAdmission admission = entity.getStudent_Admission();
            StudentAdmissionResponse admissionResponse = StudentAdmissionService.getInstance().populateResponse(admission);
        }

        if (response.getRegistration_term() != null) {
            Terms term = entity.getRegistration_term();
            TermResponse termResponse = TermService.getInstance().populateResponse(term);
            response.setRegistration_term(termResponse);
        }

        if (response.getRegistration_class() != null) {
            Classes _class = entity.getRegistration_Class();
            ClassResponse classResponse = ClassService.getInstance().populateResponse(_class);
            response.setRegistration_class(classResponse);
        }

        if (response.getRegistration_stream() != null) {
            Streams _Stream = entity.getRegistration_Stream();
            StreamResponse streamResponse = StreamsService.getInstance().populateResponse(_Stream);
            response.setRegistration_stream(streamResponse);
        }

        response.setStatus(entity.getStatus());

        if (entity.getAuthor() != null) {
            response.setAuthor(entity.getAuthor().getUsername());
        }

        return response;
    }

}
