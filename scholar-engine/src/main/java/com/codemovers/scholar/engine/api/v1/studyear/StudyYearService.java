/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codemovers.scholar.engine.api.v1.studyear;

import com.codemovers.scholar.engine.api.v1.abstracts.AbstractService;
import com.codemovers.scholar.engine.api.v1.accounts.entities.AuthenticationResponse;
import static com.codemovers.scholar.engine.api.v1.classes.ClassServiceInterface.ARCHIVE_CLASS_PERMISSION;
import com.codemovers.scholar.engine.api.v1.classes.entities.ClassResponse;
import com.codemovers.scholar.engine.api.v1.curriculum.CurriculumService;
import com.codemovers.scholar.engine.api.v1.curriculum.entities.CurriculumResponse;
import com.codemovers.scholar.engine.api.v1.studyear.entities.StudyYearResponse;
import com.codemovers.scholar.engine.api.v1.studyear.entities.StudyYears;
import com.codemovers.scholar.engine.db.controllers.StudyYearCurriculumJpaController;
import com.codemovers.scholar.engine.db.controllers.StudyYearJpaController;
import com.codemovers.scholar.engine.db.entities.Classes;
import com.codemovers.scholar.engine.db.entities.Curriculum;
import com.codemovers.scholar.engine.db.entities.SchoolData;
import com.codemovers.scholar.engine.db.entities.StudyYear;
import com.codemovers.scholar.engine.db.entities.StudyYearCurriculum;
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
public class StudyYearService extends AbstractService<StudyYears, StudyYearResponse> implements StudyYearServiceInterface {

    private static final Logger LOG = Logger.getLogger(StudyYearService.class.getName());
    private final StudyYearJpaController controller;
    private final StudyYearCurriculumJpaController curriculumJpaController;
    private static StudyYearService service = null;

    /**
     *
     */
    public StudyYearService() {
        controller = StudyYearJpaController.getInstance();
        curriculumJpaController = StudyYearCurriculumJpaController.getInstance();
    }

    /**
     *
     * @return
     */
    public static StudyYearService getInstance() {
        if (service == null) {
            service = new StudyYearService();
        }
        return service;
    }

    /**
     *
     * @param data
     * @param entity
     * @param authentication
     * @return
     * @throws Exception
     */
    @Override
    public StudyYearResponse create(SchoolData data, StudyYears entity, AuthenticationResponse authentication) throws Exception {
        check_access(CREATE_STUDYEAR_PERMISSION);
        entity.validate();
        StudyYear studyYear = populateEntity(entity, authentication);
        studyYear = controller.create(studyYear, data);
        //todo: create curricula existence
        manageStudyYearCurrilum(studyYear, data, entity, authentication);
        return populateResponse(studyYear);

    }

    /**
     *
     * @param data
     * @param entity
     * @param authentication
     * @return
     * @throws Exception
     */
    @Override
    public StudyYearResponse update(SchoolData data, StudyYears entity, AuthenticationResponse authentication) throws Exception {

        check_access(UPDATE_STUDYEAR_PERMISSION);
        entity.validate();

        //todo: get the entity by id if exists
        if (entity.getId() == null) {
            throw new BadRequestException("UNIQUE ID MISSING");
        }

        StudyYear studyYear = controller.findStudyYear(entity.getId(), data);

        studyYear = populateEntity(entity, studyYear);

        studyYear = controller.edit(studyYear, data);
        manageStudyYearCurrilum(studyYear, data, entity, authentication);

        return populateResponse(studyYear);
    }

    /**
     *
     * @param data
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public StudyYearResponse archive(SchoolData data, Integer id) throws Exception {
        check_access(ARCHIVE_STUDYEAR_PERMISSION);
        StudyYear studyYear = controller.findStudyYear(id, data);

        if (studyYear == null) {
            throw new BadRequestException("Record does not exist");
        }

        studyYear.setStatus(StatusEnum.ARCHIVED.toString());
        studyYear = controller.edit(studyYear, data);

        return populateResponse(studyYear);
    }

    /**
     *
     * @param data
     * @param Id
     * @return
     * @throws Exception
     */
    @Override
    public StudyYearResponse getById(SchoolData data, Integer Id) throws Exception {

        check_access(LIST_STUDYEAR_PERMISSION);
        StudyYear studyYear = controller.findStudyYear(Id, data);

        if (studyYear == null) {
            throw new BadRequestException("Record does not exist");
        }

        return populateResponse(studyYear);

    }

    /**
     *
     * @param data
     * @param ofset
     * @param limit
     * @param authentication
     * @return
     * @throws Exception
     */
    @Override
    public List<StudyYearResponse> list(SchoolData data, Integer ofset, Integer limit, AuthenticationResponse authentication) throws Exception {
        check_access(LIST_STUDYEAR_PERMISSION);

        List<StudyYear> list = controller.findStudyYears(limit, ofset, data);
        List<StudyYearResponse> responses = new ArrayList<>();
        if (list != null) {
            list.forEach((studyYear) -> {
                responses.add(populateResponse(studyYear));
            });
        }

        return responses;
    }

      @Override
    public List<StudyYearResponse> search(SchoolData data, String query, Integer ofset, Integer limit, AuthenticationResponse authentication) throws Exception {
        check_access(ARCHIVE_CLASS_PERMISSION);

        List<StudyYear> list = controller.query(query, limit, ofset, data);

        List<StudyYearResponse> classResponses = new ArrayList<>();
        list.forEach(respond -> {
            classResponses.add(populateResponse(respond));
        });

        return classResponses;

    }
    /**
     *
     * @param studyYear
     * @param data
     * @param entity
     * @param authentication
     * @return
     */
    public List<StudyYearCurriculum> manageStudyYearCurrilum(StudyYear studyYear, SchoolData data, StudyYears entity, AuthenticationResponse authentication) {
        //todo: remove all curricula in this study year
        List<StudyYearCurriculum> list = new ArrayList<>();
        curriculumJpaController.deleteCurriculumByStudyYearId(studyYear.getId().intValue(), data);
        if (entity.getCurricula() != null) {
            entity.getCurricula().stream().map((curriculum_id) -> createStudyYearCurriculum(curriculum_id, data, studyYear, authentication)).forEachOrdered((studyYearCurriculum) -> {
                list.add(studyYearCurriculum);
            });
        }

        return list;

    }

    /**
     *
     * @param curriculum_id
     * @param data
     * @param studyYear
     * @param authentication
     * @return
     */
    public StudyYearCurriculum createStudyYearCurriculum(Integer curriculum_id, SchoolData data, StudyYear studyYear, AuthenticationResponse authentication) {
        //todo: create curriculum_id
        Curriculum curriculum = CurriculumService.getInstance().getCurriculum(curriculum_id, data);
        StudyYearCurriculum studyYearCurriculum = new StudyYearCurriculum();
        studyYearCurriculum.setStudyYear(studyYear);
        studyYearCurriculum.setCurriculum(curriculum);
        studyYearCurriculum.setStatus(StatusEnum.ACTIVE.name());
        studyYearCurriculum.setDateCreated(new Date());
        studyYearCurriculum.setAuthorId(new Users(authentication.getId().longValue()));
        studyYearCurriculum = curriculumJpaController.create(studyYearCurriculum, data);
        return studyYearCurriculum;

    }

    /**
     *
     * @param entity
     * @return
     */
    @Override
    public StudyYearResponse populateResponse(StudyYear entity) {
        StudyYearResponse response = new StudyYearResponse();
        response.setId(entity.getId().intValue());
        response.setTheme(entity.getTheme());
        response.setStart_date(entity.getStartDate().getTime());
        response.setEnd_date(entity.getEndDate().getTime());
        response.setStatus(entity.getStatus());
        if (entity.getAuthor() != null) {
            response.setAuthor(entity.getAuthor().getUsername());
        }
        response.setDate_created(entity.getDateCreated().getTime());
        if (entity.getCurricula() != null) {
            List<CurriculumResponse> curriculumResponses = new ArrayList<>();
            for (Curriculum syc : entity.getCurricula()) {
                CurriculumResponse curriculumResponse = CurriculumService.getInstance().populateResponse(syc);
                curriculumResponses.add(curriculumResponse);
            }
            response.setCurricula(curriculumResponses);
        }
        return response;
    }

    /**
     *
     * @param entity
     * @param studyYear
     * @return
     */
    public StudyYear populateEntity(StudyYears entity, StudyYear studyYear) {
        StudyYear studyyear = studyYear;
        if (entity.getTheme() != null && !entity.getTheme().equalsIgnoreCase(studyYear.getTheme())) {
            studyyear.setTheme(entity.getTheme());
        }

        if (entity.getStart_date() != null && entity.getStart_date() != (studyYear.getStartDate().getTime())) {
            studyyear.setStartDate(new Date(entity.getStart_date()));
        }

        if (entity.getStart_date() != null && entity.getStart_date() != (studyYear.getEndDate().getTime())) {
            studyyear.setEndDate(new Date(entity.getEnd_date()));
        }

        return studyyear;
    }

    /**
     *
     * @param entity
     * @param authentication
     * @return
     */
    public StudyYear populateEntity(StudyYears entity, AuthenticationResponse authentication) {
        entity.setAuthor_id(authentication.getId());
        entity.setStatus(StatusEnum.ACTIVE);
        StudyYear studyYear = new StudyYear();
        studyYear.setTheme(entity.getTheme());
        studyYear.setStartDate(new Date(entity.getStart_date()));
        studyYear.setEndDate(new Date(entity.getEnd_date()));
        studyYear.setStatus(entity.getStatus().toString());
        studyYear.setDateCreated(new Date());
        studyYear.setAuthor(new Users(entity.getAuthor_id().longValue()));
        return studyYear;
    }

}
