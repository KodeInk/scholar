/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codemovers.scholar.engine.api.v1.students.admission;

import com.codemovers.scholar.engine.api.v1.abstracts.AbstractService;
import com.codemovers.scholar.engine.api.v1.accounts.entities.AuthenticationResponse;
import com.codemovers.scholar.engine.api.v1.classes.entities.ClassResponse;
import com.codemovers.scholar.engine.api.v1.profile.ProfileService;
import com.codemovers.scholar.engine.api.v1.profile.entities.ProfileResponse;
import com.codemovers.scholar.engine.api.v1.streams.StreamsService;
import com.codemovers.scholar.engine.api.v1.streams.entities.StreamResponse;
import com.codemovers.scholar.engine.api.v1.students.admission.entities.StudentAdmissionResponse;
import com.codemovers.scholar.engine.api.v1.students.admission.entities._StudentAdmission;
import com.codemovers.scholar.engine.api.v1.terms.entities.TermResponse;
import com.codemovers.scholar.engine.db.controllers.ClassJpaController;
import com.codemovers.scholar.engine.db.controllers.ProfileJpaController;
import com.codemovers.scholar.engine.db.controllers.StreamsJpaController;
import com.codemovers.scholar.engine.db.controllers.StudentAdmissionJpaController;
import com.codemovers.scholar.engine.db.controllers.TermsJpaController;
import com.codemovers.scholar.engine.db.entities.Classes;
import com.codemovers.scholar.engine.db.entities.Profile;
import com.codemovers.scholar.engine.db.entities.SchoolData;
import com.codemovers.scholar.engine.db.entities.Streams;
import com.codemovers.scholar.engine.db.entities.StudentAdmission;
import com.codemovers.scholar.engine.db.entities.Terms;
import static com.codemovers.scholar.engine.helper.Utilities.check_access;
import static com.codemovers.scholar.engine.helper.Utilities.getNewExternalId;
import com.codemovers.scholar.engine.helper.enums.StatusEnum;
import com.codemovers.scholar.engine.helper.exceptions.BadRequestException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 *
 * @author mover 12/30/2017
 */
public class StudentAdmissionService extends AbstractService<_StudentAdmission, StudentAdmissionResponse> implements StudentAdmissionServiceInterface {

    private static final Logger LOG = Logger.getLogger(StudentAdmissionService.class.getName());
    private final StudentAdmissionJpaController controller;
    private static StudentAdmissionService service = null;

    public StudentAdmissionService() {
        controller = StudentAdmissionJpaController.getInstance();
    }

    public static StudentAdmissionService getInstance() {
        if (service == null) {
            service = new StudentAdmissionService();
        }
        return service;
    }

    @Override
    public StudentAdmissionResponse create(SchoolData data, _StudentAdmission entity, AuthenticationResponse authentication) throws Exception {
        check_access(ADMIT_STUDENT_PERMISSION);
        entity.validate();
        entity.setAuthor_id(authentication.getId());
        entity.setStatus(StatusEnum.ACTIVE);

        //todo: checkt to see if there is no account with this admision number :: 
        //todo: create a profile_by id
        ProfileResponse pr = ProfileService.getInstance().create(data, entity.getProfile());
        //todo: generate external Id 
        if (entity.getExternal_id().isEmpty()) {
            entity.setExternal_id(getNewExternalId());
        }

        //get profile by Id;
        Profile profile = ProfileJpaController.getInstance().findProfile(pr.getId(), data);
        //todo: get  class by Id

        Classes AdmissionClass = ClassJpaController.getInstance().findClass(entity.getClass_id(), data);
        //todo: get stream by id
        Streams AdmissionStream = StreamsJpaController.getInstance().findStream(entity.getStream_id(), data);
        //todo: get  Term by ID
        Terms AdmissionTerm = TermsJpaController.getInstance().findTerm(entity.getTerm_id(), data);

        //todo: creation pojo ::
        StudentAdmission studentAdmission = new StudentAdmission();
        studentAdmission.setAdmissionNo(entity.getAdmission_number());
        studentAdmission.setExternalId(entity.getExternal_id());
        studentAdmission.setDateOfAdmission(entity.getDate_of_admission());
        studentAdmission.setStatus(entity.getStatus().toString());

        if (AdmissionTerm != null) {
            studentAdmission.setAdmissionTerm(AdmissionTerm);
        }
        if (AdmissionClass != null) {
            studentAdmission.setAdmissionClass(AdmissionClass);
        }
        if (AdmissionStream != null) {
            studentAdmission.setAdmissionStream(AdmissionStream);
        }

        studentAdmission = controller.create(studentAdmission, data);
        return populateResponse(studentAdmission);

    }

    @Override
    public StudentAdmissionResponse update(SchoolData data, _StudentAdmission entity, AuthenticationResponse authentication) throws Exception {
        check_access(UPDATE_STUDENT_ADMISSION_PERMISSION);
        entity.validate();

        if (entity.getId() == null) {
            throw new BadRequestException("UNIQUE ID MISSING");
        }

        StudentAdmission studentAdmission = controller.findStudentAdmission(entity.getId(), data);
        if (studentAdmission == null) {
            throw new BadRequestException("RECORD DOES NOT EXIST ");
        }

        if (entity.getAdmission_number() != null && !entity.getAdmission_number().equalsIgnoreCase(studentAdmission.getAdmissionNo())) {
            studentAdmission.setAdmissionNo(entity.getAdmission_number());
        }

        if (entity.getDate_of_admission() != null && entity.getDate_of_admission() != (studentAdmission.getDateOfAdmission())) {
            studentAdmission.setDateOfAdmission(entity.getDate_of_admission());
        }

        Classes AdmissionClass = ClassJpaController.getInstance().findClass(entity.getClass_id(), data);
        //todo: get stream by id
        Streams AdmissionStream = StreamsJpaController.getInstance().findStream(entity.getStream_id(), data);
        //todo: get  Term by ID
        Terms AdmissionTerm = TermsJpaController.getInstance().findTerm(entity.getTerm_id(), data);

        if (AdmissionTerm != null) {
            studentAdmission.setAdmissionTerm(AdmissionTerm);
        }
        if (AdmissionClass != null) {
            studentAdmission.setAdmissionClass(AdmissionClass);
        }
        if (AdmissionStream != null) {
            studentAdmission.setAdmissionStream(AdmissionStream);
        }

        studentAdmission = controller.edit(studentAdmission, data);

        return populateResponse(studentAdmission);

    }

    @Override
    public StudentAdmissionResponse archive(SchoolData data, Integer id, AuthenticationResponse authentication) throws Exception {

        check_access(ARCHIVE_STUDENT_ADMISSION_PERMISSION);
        StudentAdmission studentAdmission = controller.findStudentAdmission(id, data);
        if (studentAdmission == null) {
            throw new BadRequestException("RECORD DOES NOT EXIST ");
        }

        studentAdmission.setStatus(StatusEnum.ARCHIVED.toString());

        studentAdmission = controller.edit(studentAdmission, data);

        return populateResponse(studentAdmission);
    }

    @Override
    public StudentAdmissionResponse getById(SchoolData data, Integer Id) throws Exception {
        check_access(LIST_STUDENT_ADMISSION_PERMISSION);
        StudentAdmission studentAdmission = controller.findStudentAdmission(Id, data);
        return populateResponse(studentAdmission);
    }

    @Override
    public List<StudentAdmissionResponse> list(SchoolData data, Integer ofset, Integer limit, AuthenticationResponse authentication) throws Exception {
        check_access(LIST_STUDENT_ADMISSION_PERMISSION);

        List<StudentAdmission> list = controller.findStudentAdmissions(ofset, limit, data);
        List<StudentAdmissionResponse> responses = new ArrayList<>();
        if (list != null) {

            list.forEach((studentAdmission) -> {
                responses.add(populateResponse(studentAdmission));
            });
        }

        return responses;

    }

    @Override
    public StudentAdmissionResponse populateResponse(StudentAdmission entity) {
        StudentAdmissionResponse response = new StudentAdmissionResponse();
        if (entity.getAdmissionClass() != null) {
            Classes _class = entity.getAdmissionClass();
            ClassResponse classResponse = new ClassResponse();
            classResponse.setId(_class.getId().intValue());
            classResponse.setName(_class.getName());
            classResponse.setCode(_class.getCode());
            response.setClass_response(classResponse);
        }

        if (entity.getAdmissionStream() != null) {
            Streams _stream = entity.getAdmissionStream();
            StreamResponse streamResponse = new StreamResponse();
            streamResponse.setId(_stream.getId().intValue());
            streamResponse.setName(_stream.getName());
            streamResponse.setCode(_stream.getCode());
            response.setStreamResponse(streamResponse);
        }

        if (entity.getAdmissionTerm() != null) {
            Terms _term = entity.getAdmissionTerm();
            TermResponse termResponse = new TermResponse();
            //termResponse.setStudy_year(_term.getStudyYear());
            termResponse.setName(_term.getName());
            termResponse.setStart_date(_term.getStartDate());
            termResponse.setEnd_date(_term.getEndDate());
            response.setTerm_response(termResponse);

        }
        response.setDate_of_admission(entity.getDateOfAdmission());
        response.setAdmission_number(entity.getAdmissionNo());
        response.setExternal_id(entity.getExternalId());

        return response;
    }

}
