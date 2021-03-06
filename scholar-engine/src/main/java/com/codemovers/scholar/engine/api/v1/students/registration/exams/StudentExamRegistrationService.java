/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codemovers.scholar.engine.api.v1.students.registration.exams;

import com.codemovers.scholar.engine.api.v1.abstracts.AbstractService;
import com.codemovers.scholar.engine.api.v1.accounts.entities.AuthenticationResponse;
import com.codemovers.scholar.engine.api.v1.exams.ExamsService;
import com.codemovers.scholar.engine.api.v1.exams.entities.ExamResponse;
import com.codemovers.scholar.engine.api.v1.students.admissions.AdmissionService;
import com.codemovers.scholar.engine.api.v1.students.admissions.entities.AdmissionResponse;


import com.codemovers.scholar.engine.api.v1.students.registration.exams.entities.StudentExamRegistrationResponse;
import com.codemovers.scholar.engine.api.v1.students.registration.exams.entities._StudentExamRegistration;
import com.codemovers.scholar.engine.api.v1.students.terms.entities.TermRegistrationResponse;
import com.codemovers.scholar.engine.db.controllers.ExamsJpaController;
import com.codemovers.scholar.engine.db.controllers.StudentExamRegistrationJpaController;
import com.codemovers.scholar.engine.db.controllers.StudentTermRegistrationJpaController;
import com.codemovers.scholar.engine.db.controllers.TermsJpaController;
import com.codemovers.scholar.engine.db.entities.Exams;
import com.codemovers.scholar.engine.db.entities.SchoolData;
import com.codemovers.scholar.engine.db.entities.StudentAdmission;
import com.codemovers.scholar.engine.db.entities.StudentExamRegistration;
import com.codemovers.scholar.engine.db.entities.StudentTermRegistration;
import com.codemovers.scholar.engine.db.entities.Terms;
import com.codemovers.scholar.engine.db.entities.Users;
import static com.codemovers.scholar.engine.helper.Utilities.check_access;
import com.codemovers.scholar.engine.helper.enums.StatusEnum;
import com.codemovers.scholar.engine.helper.exceptions.BadRequestException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 *
 * @author mover 1/2/2018
 */
public class StudentExamRegistrationService extends AbstractService<_StudentExamRegistration, StudentExamRegistrationResponse> implements StudentExamRegistrationServiceInterface {

    private static final Logger LOG = Logger.getLogger(StudentExamRegistrationService.class.getName());
    private final StudentExamRegistrationJpaController controller;
    private static StudentExamRegistrationService service = null;

    public StudentExamRegistrationService() {
        controller = StudentExamRegistrationJpaController.getInstance();
    }

    public static StudentExamRegistrationService getInstance() {
        if (service == null) {
            service = new StudentExamRegistrationService();
        }
        return service;
    }

    @Override
    public StudentExamRegistrationResponse create(SchoolData data, _StudentExamRegistration entity, AuthenticationResponse authentication) throws Exception {
        check_access(CREATE_EXAMREGISTRATION_PERMISSION);

        entity.validate();
        entity.setAuthor_id(authentication.getId());
        entity.setStatus(StatusEnum.ACTIVE);

        StudentTermRegistration studentTermRegistration = StudentTermRegistrationJpaController.getInstance().findStudentTermRegistration(entity.getTerm_registration_id(), data);

        if (studentTermRegistration == null) {
            throw new BadRequestException("STUDENT IS NOT YET REGISTERED FOR THIS TERM");
        }

        Terms registration_term = TermsJpaController.getInstance().findTerm(entity.getTerm_registration_id(), data);
        Exams registration_exam = ExamsJpaController.getInstance().findExam(entity.getExam_id(), data);

        //StudentTermRegistration
        StudentExamRegistration examRegistration = new StudentExamRegistration();
        examRegistration.setExam(registration_exam);
        examRegistration.setTermRegistration(studentTermRegistration);
        examRegistration.setStatus(entity.getStatus().toString());
        examRegistration.setAuthor(new Users(entity.getAuthor_id().longValue()));

        examRegistration = controller.create(examRegistration, data);
        return populateResponse(examRegistration);
    }

    @Override
    public StudentExamRegistrationResponse update(SchoolData data, _StudentExamRegistration entity, AuthenticationResponse authentication) throws Exception {
        check_access(UPDATE_EXAMREGISTRATION_PERMISSION);
        entity.validate();

        if (entity.getId() == null) {
            throw new BadRequestException("UNIQUE ID MISSING");
        }

        StudentExamRegistration examRegistration = controller.findStudentExamRegistration(entity.getId(), data);

        if (examRegistration == null) {
            throw new BadRequestException("Record does not Exist");
        }

        StudentTermRegistration studentTermRegistration = StudentTermRegistrationJpaController.getInstance().findStudentTermRegistration(entity.getTerm_registration_id(), data);
        Exams registration_exam = ExamsJpaController.getInstance().findExam(entity.getExam_id(), data);

        if (studentTermRegistration != null) {
            examRegistration.setTermRegistration(studentTermRegistration);
        }

        if (registration_exam != null) {
            examRegistration.setExam(registration_exam);
        }

        examRegistration = controller.edit(examRegistration, data);

        return populateResponse(examRegistration);

    }

    @Override
    public StudentExamRegistrationResponse archive(SchoolData data, Integer id, AuthenticationResponse authentication) throws Exception {
        check_access(ARCHIVE_EXAMREGISTRATION_PERMISSION);
        StudentExamRegistration examRegistration = controller.findStudentExamRegistration(id, data);

        if (examRegistration == null) {
            throw new BadRequestException("Record does not Exist");
        }
        examRegistration.setStatus(StatusEnum.ARCHIVED.toString());
        examRegistration = controller.edit(examRegistration, data);
        return populateResponse(examRegistration);
    }

    @Override
    public List<StudentExamRegistrationResponse> list(SchoolData data, Integer ofset, Integer limit, AuthenticationResponse authentication) throws Exception {
        check_access(LIST_EXAMREGISTRATION_PERMISSION);

        List<StudentExamRegistration> list = controller.findStudentExamRegistrations(ofset, limit, data);
        List<StudentExamRegistrationResponse> responses = new ArrayList<>();
        if (list != null) {

            list.forEach((studentExamRegistration) -> {
                responses.add(populateResponse(studentExamRegistration));
            });
        }

        return responses;

    }

    @Override
    public StudentExamRegistrationResponse getById(SchoolData data, Integer Id) throws Exception {
        check_access(LIST_EXAMREGISTRATION_PERMISSION);

        StudentExamRegistration examRegistration = controller.findStudentExamRegistration(Id, data);

        if (examRegistration == null) {
            throw new BadRequestException("Record does not Exist");
        }
        return populateResponse(examRegistration);
    }

    @Override
    public StudentExamRegistrationResponse populateResponse(StudentExamRegistration entity) {

        StudentExamRegistrationResponse response = new StudentExamRegistrationResponse();

        if (entity.getTermRegistration() != null) {
            StudentTermRegistration studentTermRegistration = entity.getTermRegistration();
            TermRegistrationResponse registrationResponse = new TermRegistrationResponse();
            registrationResponse.setId(studentTermRegistration.getId().intValue());

            StudentAdmission admission = studentTermRegistration.getStudentAdmission();
            if (admission != null) {
                AdmissionResponse admissionResponse = AdmissionService.getInstance().populateResponse(admission);
                                registrationResponse.setAdmission(admissionResponse);
            }

            response.setTerm_registration(registrationResponse);
        }

        Exams exams = entity.getExam();
        if (exams != null) {
            ExamResponse examResponse = ExamsService.getInstance().populateResponse(exams);
            response.setExam(examResponse);
        }

        Users author = entity.getAuthor();
        if (author != null) {
            response.setAuthor(author.getUsername());
        }
        return response;
    }

}
