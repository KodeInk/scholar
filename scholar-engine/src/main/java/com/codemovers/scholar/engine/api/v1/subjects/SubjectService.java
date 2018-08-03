/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codemovers.scholar.engine.api.v1.subjects;

import com.codemovers.scholar.engine.api.v1.abstracts.AbstractService;
import com.codemovers.scholar.engine.api.v1.accounts.entities.AuthenticationResponse;
import static com.codemovers.scholar.engine.api.v1.classes.ClassServiceInterface.ARCHIVE_CLASS_PERMISSION;
import com.codemovers.scholar.engine.api.v1.classes.entities.ClassResponse;
import com.codemovers.scholar.engine.api.v1.subjects.entities.SubjectResponse;
import com.codemovers.scholar.engine.api.v1.subjects.entities.Subject;
import com.codemovers.scholar.engine.db.controllers.SubjectsJpaController;
import com.codemovers.scholar.engine.db.entities.Classes;
import com.codemovers.scholar.engine.db.entities.SchoolData;
import com.codemovers.scholar.engine.db.entities.Subjects;
import com.codemovers.scholar.engine.db.entities.Users;
import static com.codemovers.scholar.engine.helper.Utilities.check_access;
import com.codemovers.scholar.engine.helper.enums.StatusEnum;
import com.codemovers.scholar.engine.helper.exceptions.BadRequestException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 *
 * @author mover 12/20/2017
 */
public class SubjectService extends AbstractService<Subject, SubjectResponse> implements SubjectServiceInterface {

    private static final Logger LOG = Logger.getLogger(SubjectService.class.getName());

    private final SubjectsJpaController controller;
    private static SubjectService service = null;

    public SubjectService() {
        controller = SubjectsJpaController.getInstance();
    }

    public static SubjectService getInstance() {
        if (service == null) {
            service = new SubjectService();
        }
        return service;
    }

    @Override
    public SubjectResponse create(SchoolData data, Subject entity, AuthenticationResponse authentication) throws Exception {
        check_access(CREATE_SUBJECT_PERMISSION);
        entity.validate();

        //todo: check to see that the name and code of the subject does not exist
        List<Subjects> list = controller.findSubjects(entity.getName(), entity.getCode(), data);
        if (list.size() > 0) {
            throw new BadRequestException("Subject with the same name or code exists in the database");
        }

        entity.setAuthor_id(authentication.getId());
        entity.setStatus(StatusEnum.ACTIVE);

        Subjects subject = getSubject(entity);
        subject = controller.create(subject, data);
        return populateResponse(subject);
    }

    @Override
    public SubjectResponse update(SchoolData data, Subject entity, AuthenticationResponse authenticationResponse) throws Exception {
        check_access(UPDATE_SUBJECT_PERMISSION);
        entity.validate();

        if (entity.getId() == null) {
            throw new BadRequestException("UNIQUE ID MISSING");
        }

        Subjects subject = controller.findSubjects(entity.getId(), data);

        if (subject == null) {
            throw new BadRequestException("SUBJECT  RECORD DOES NOT EXIST");
        }

        //todo: check to see that there is no other subject with same name
        List<Subjects> list = controller.findSubjects(entity.getName(), entity.getCode(), entity.getId(), data);
        if (list.size() > 0) {
            throw new BadRequestException("Subject with the same name or code exists in the database");
        }

        if (entity.getName() != null && !entity.getName().equalsIgnoreCase(subject.getName())) {
            subject.setName(entity.getName());
        }

        if (entity.getCode() != null && !entity.getCode().equalsIgnoreCase(subject.getCode())) {
            subject.setCode(entity.getCode());
        }
        
         if (entity.getCategory().name()!= null && !entity.getCategory().name().equalsIgnoreCase(subject.getCategory())) {
            subject.setCategory(entity.getCategory().name());
        }
         

        subject = controller.edit(subject, data);
        return populateResponse(subject);
    }

    @Override
    public SubjectResponse archive(SchoolData data, Integer id) throws Exception {
        check_access(ARCHIVE_SUBJECT_PERMISSION);
        Subjects subject = controller.findSubjects(id, data);

        if (subject == null) {
            throw new BadRequestException("SUBJECT  RECORD DOES NOT EXIST");
        }

        subject.setStatus(StatusEnum.ARCHIVED.toString());

        return populateResponse(subject);
    }

    @Override
    public List<SubjectResponse> list(SchoolData data, Integer ofset, Integer limit, AuthenticationResponse authenticationResponse) throws Exception {
        // check_access(LIST_SUBJECT_PERMISSION);

        List<Subjects> list = controller.findSubjects(limit, ofset, data);
        List<SubjectResponse> responses = new ArrayList<>();
        if (list != null) {
            list.forEach((subject) -> {
                responses.add(populateResponse(subject));
            });
        }

        return responses;

    }

    public List<SubjectResponse> search(SchoolData data, String query, Integer ofset, Integer limit, AuthenticationResponse authentication) throws Exception {
        check_access(ARCHIVE_CLASS_PERMISSION);

        List<Subjects> list = controller.query(query, limit, ofset, data);

        List<SubjectResponse> classResponses = new ArrayList<>();
        list.forEach(response -> {
            classResponses.add(populateResponse(response));
        });

        return classResponses;

    }

    @Override
    public SubjectResponse getById(SchoolData data, Integer Id) throws Exception {

        Subjects subject = controller.findSubjects(Id, data);

        if (subject == null) {
            throw new BadRequestException("SUBJECT  RECORD DOES NOT EXIST");
        }

        return populateResponse(subject);
    }

    @Override
    public SubjectResponse populateResponse(Subjects entity) {
        SubjectResponse response = new SubjectResponse();

        response.setId(entity.getId().intValue());
        response.setName(entity.getName());
        response.setCode(entity.getCode());
        response.setCategory(entity.getCategory());
        response.setStatus(entity.getStatus());
        if (entity.getAuthor() != null) {
            response.setAuthor(entity.getAuthor().getUsername());
        }

        if (entity.getDateCreated() != null) {
            response.setDate_created(entity.getDateCreated().getTime());
        }
        return response;
    }

    public Subjects getSubject(Subject entity) {
        Subjects subject = new Subjects();
        subject.setName(entity.getName());
        subject.setCode(entity.getCode());
        subject.setStatus(entity.getStatus().toString());
        subject.setCategory(entity.getCategory().name());
        // subject.setDateCreated(entity.getDate_created());
        subject.setAuthor(new Users(entity.getAuthor_id().longValue()));
        return subject;
    }

}
