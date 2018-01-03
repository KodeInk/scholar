/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codemovers.scholar.engine.api.v1.subjects;

import com.codemovers.scholar.engine.api.v1.abstracts.AbstractService;
import com.codemovers.scholar.engine.api.v1.accounts.entities.AuthenticationResponse;
import com.codemovers.scholar.engine.api.v1.subjects.entities.SubjectResponse;
import com.codemovers.scholar.engine.api.v1.subjects.entities._Subject;
import com.codemovers.scholar.engine.db.controllers.SubjectsJpaController;
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
public class SubjectService extends AbstractService<_Subject, SubjectResponse> implements SubjectServiceInterface {

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
    public SubjectResponse create(SchoolData data, _Subject entity, AuthenticationResponse authentication) throws Exception {
        check_access(CREATE_SUBJECT_PERMISSION);
        entity.validate();

        entity.setAuthor_id(authentication.getId());
        entity.setStatus(StatusEnum.ACTIVE);

        Subjects subject = new Subjects();
        subject.setName(entity.getName());
        subject.setCode(entity.getCode());
        subject.setDateCreated(entity.getDate_created());
        subject.setAuthor(new Users(entity.getAuthor_id().longValue()));
        subject = controller.create(subject, data);
        return populateResponse(subject);
    }

    @Override
    public SubjectResponse update(SchoolData data, _Subject entity) throws Exception {
        check_access(UPDATE_SUBJECT_PERMISSION);
        entity.validate();

        if (entity.getId() == null) {
            throw new BadRequestException("UNIQUE ID MISSING");
        }

        Subjects subject = controller.findSubjects(entity.getId(), data);

        if (subject == null) {
            throw new BadRequestException("SUBJECT  RECORD DOES NOT EXIST");
        }


        if (entity.getName() != null && !entity.getName().equalsIgnoreCase(subject.getName())) {
            subject.setName(entity.getName());
        }

        if (entity.getCode() != null && !entity.getCode().equalsIgnoreCase(subject.getCode())) {
            subject.setCode(entity.getCode());
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
    public List<SubjectResponse> list(SchoolData data, Integer ofset, Integer limit) throws Exception {
        check_access(LIST_SUBJECT_PERMISSION);

        List<Subjects> list = controller.findSubjects(ofset, limit, data);
        List<SubjectResponse> responses = new ArrayList<>();
        if (list != null) {
            list.forEach((_subject) -> {
                responses.add(populateResponse(_subject));
            });
        }

        return responses;

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

        response.setCode(entity.getCode());
        response.setName(entity.getName());
        response.setStatus(entity.getStatus());
        if (entity.getAuthor() != null) {
            response.setAuthor(entity.getAuthor().getUsername());
        }

        return response;
    }

}
