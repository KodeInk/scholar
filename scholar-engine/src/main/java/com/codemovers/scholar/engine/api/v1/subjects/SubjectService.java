/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codemovers.scholar.engine.api.v1.subjects;

import com.codemovers.scholar.engine.api.v1.abstracts.AbstractService;
import com.codemovers.scholar.engine.api.v1.accounts.entities.AuthenticationResponse;
import static com.codemovers.scholar.engine.api.v1.classes.ClassServiceInterface.ARCHIVE_CLASS_PERMISSION;
import com.codemovers.scholar.engine.api.v1.curriculum.CurriculumService;
import com.codemovers.scholar.engine.api.v1.curriculum.entities.CurriculumResponse;
import com.codemovers.scholar.engine.api.v1.subjects.entities.SubjectResponse;
import com.codemovers.scholar.engine.api.v1.subjects.entities.Subject;
import com.codemovers.scholar.engine.db.controllers.SubjectCurriculumJpaController;
import com.codemovers.scholar.engine.db.controllers.SubjectsJpaController;
import com.codemovers.scholar.engine.db.entities.Curriculum;
import com.codemovers.scholar.engine.db.entities.SchoolData;
import com.codemovers.scholar.engine.db.entities.SubjectCurriculum;
import com.codemovers.scholar.engine.db.entities.Subjects;
import com.codemovers.scholar.engine.db.entities.Users;
import static com.codemovers.scholar.engine.helper.Utilities.check_access;
import com.codemovers.scholar.engine.helper.enums.StatusEnum;
import com.codemovers.scholar.engine.helper.exceptions.BadRequestException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

/**
 *
 * @author mover 12/20/2017
 */
public class SubjectService extends AbstractService<Subject, SubjectResponse> implements SubjectServiceInterface {

    private static final Logger LOG = Logger.getLogger(SubjectService.class.getName());
    private final SubjectsJpaController controller;
    private final SubjectCurriculumJpaController subjectCurriculumJpaController;
    private static SubjectService service = null;

    public SubjectService() {
        controller = SubjectsJpaController.getInstance();
        subjectCurriculumJpaController = SubjectCurriculumJpaController.getInstance();
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

        List<Subjects> list = controller.findSubjects(entity.getName(), entity.getCode(), data);
        if (list.size() > 0) {
            throw new BadRequestException("Subject with the same name or code exists in the database");
        }

        entity.setAuthor_id(authentication.getId());
        entity.setStatus(StatusEnum.ACTIVE);

        Subjects subject = populateEntity(entity);
        subject.setDateCreated(new Date());

        subject = controller.create(subject, data);

        manageStudyYearCurrilum(subject, data, entity, authentication);
        //todo: add subject curriculum 
        return populateResponse(subject);
    }

    @Override
    public SubjectResponse update(SchoolData data, Subject entity, AuthenticationResponse authenticationResponse) throws Exception {
        check_access(UPDATE_SUBJECT_PERMISSION);
        entity.validate();

        if (entity.getId() == null) {
            throw new BadRequestException("UNIQUE ID MISSING");
        }

        Subjects subject = findSubject(entity.getId(), data);

        //todo: check to see that there is no other subject with same name
        List<Subjects> list = controller.findSubjects(entity.getName(), entity.getCode(), entity.getId(), data);
        if (list.size() > 0) {
            throw new BadRequestException("Subject with the same name or code exists in the database");
        }

        populateEntity(entity, subject);

        subject = controller.edit(subject, data);
        manageStudyYearCurrilum(subject, data, entity, authenticationResponse);
        return populateResponse(subject);
    }

    /**
     *
     * @param subject
     * @param data
     * @param entity
     * @param authentication
     * @return
     */
    public List<SubjectCurriculum> manageStudyYearCurrilum(Subjects subject, SchoolData data, Subject entity, AuthenticationResponse authentication) {
        //todo: remove all curricula in this study year
        List<SubjectCurriculum> list = new ArrayList<>();

        try {
            subjectCurriculumJpaController.deleteCurriculumByStudyId(subject.getId().intValue(), data);
            if (entity.getCurricula() != null) {
                entity.getCurricula().stream().map((curriculum_id) -> createStudyYearCurriculum(curriculum_id, data, subject, authentication)).forEachOrdered((studyYearCurriculum) -> {
                    list.add(studyYearCurriculum);
                });
            }
        } catch (Exception er) {
            er.printStackTrace();
        }
        return list;

    }

    /**
     *
     * @param curriculum_id
     * @param data
     * @param subject
     * @param authentication
     * @return
     */
    public SubjectCurriculum createStudyYearCurriculum(Integer curriculum_id, SchoolData data, Subjects subject, AuthenticationResponse authentication) {
        //todo: create curriculum_id
        Curriculum curriculum = CurriculumService.getInstance().getCurriculum(curriculum_id, data);
        SubjectCurriculum subjectCurriculum = new SubjectCurriculum();
        subjectCurriculum.setSubject(subject);
        subjectCurriculum.setCurriculum(curriculum);
        subjectCurriculum.setStatus(StatusEnum.ACTIVE.name());
        subjectCurriculum.setDateCreated(new Date());
        subjectCurriculum.setAuthor(new Users(authentication.getId().longValue()));
        subjectCurriculum = subjectCurriculumJpaController.create(subjectCurriculum, data);
        return subjectCurriculum;

    }

    /**
     *
     * @param data
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public SubjectResponse archive(SchoolData data, Integer id) throws Exception {
        check_access(ARCHIVE_SUBJECT_PERMISSION);
        Subjects subject = findSubject(id, data);

        subject.setStatus(StatusEnum.ARCHIVED.toString());

        return populateResponse(subject);
    }

    /**
     *
     * @param data
     * @param ofset
     * @param limit
     * @param authenticationResponse
     * @return
     * @throws Exception
     */
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

    /**
     *
     * @param data
     * @param query
     * @param ofset
     * @param limit
     * @param authentication
     * @return
     * @throws Exception
     */
    @Override
    public List<SubjectResponse> search(SchoolData data, String query, Integer ofset, Integer limit, AuthenticationResponse authentication) throws Exception {
        check_access(ARCHIVE_CLASS_PERMISSION);

        List<Subjects> list = controller.query(query, limit, ofset, data);

        List<SubjectResponse> classResponses = new ArrayList<>();
        list.forEach(response -> {
            classResponses.add(populateResponse(response));
        });

        return classResponses;

    }

    /**
     *
     * @param data
     * @param Id
     * @return
     * @throws Exception
     */
    @Override
    public SubjectResponse getById(SchoolData data, Integer Id) throws Exception {

        Subjects subject = findSubject(Id, data);

        return populateResponse(subject);
    }

    /**
     *
     * @param Id
     * @param data
     * @return
     * @throws BadRequestException
     */
    public Subjects findSubject(Integer Id, SchoolData data) throws BadRequestException {
        Subjects subject = controller.findSubjects(Id, data);
        if (subject == null) {
            throw new BadRequestException("SUBJECT  RECORD DOES NOT EXIST");
        }
        return subject;
    }

    /**
     *
     * @param entity
     * @return
     */
    @Override
    public SubjectResponse populateResponse(Subjects entity) {
        SubjectResponse response = new SubjectResponse();

        response.setId(entity.getId().intValue());
        response.setName(entity.getName());
        response.setCode(entity.getCode());
        response.setType(entity.getType());
        response.setStatus(entity.getStatus());
        if (entity.getAuthor() != null) {
            response.setAuthor(entity.getAuthor().getUsername());
        }

        if (entity.getCurricula() != null) {
            List<CurriculumResponse> curriculumResponses = new ArrayList<>();
            for (Curriculum curriculum : entity.getCurricula()) {
                CurriculumResponse cr = CurriculumService.getInstance().populateResponse(curriculum);
                curriculumResponses.add(cr);
            }
            response.setCurriculumResponses(curriculumResponses);
        }

        if (entity.getDateCreated() != null) {
            response.setDate_created(entity.getDateCreated().getTime());
        }
        return response;
    }

    /**
     *
     * @param entity
     * @return
     */
    public Subjects populateEntity(Subject entity) {
        Subjects subject = new Subjects();
        subject.setName(entity.getName());
        subject.setCode(entity.getCode());
        subject.setStatus(entity.getStatus().toString());
        subject.setType(entity.getType().name());
        // subject.setDateCreated(entity.getDate_created());
        subject.setAuthor(new Users(entity.getAuthor_id().longValue()));
        return subject;
    }

    public void populateEntity(Subject entity, Subjects subject) {
        if (entity.getName() != null && !entity.getName().equalsIgnoreCase(subject.getName())) {
            subject.setName(entity.getName());
        }

        if (entity.getCode() != null && !entity.getCode().equalsIgnoreCase(subject.getCode())) {
            subject.setCode(entity.getCode());
        }

        if (entity.getType().name() != null && !entity.getType().name().equalsIgnoreCase(subject.getType())) {
            subject.setType(entity.getType().name());
        }
    }

}
