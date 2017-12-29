/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codemovers.scholar.engine.api.v1.studyear;

import com.codemovers.scholar.engine.api.v1.abstracts.AbstractService;
import com.codemovers.scholar.engine.api.v1.accounts.entities.AuthenticationResponse;
import com.codemovers.scholar.engine.api.v1.studyear.entities.StudyYearResponse;
import com.codemovers.scholar.engine.api.v1.studyear.entities._StudyYear;
import com.codemovers.scholar.engine.db.controllers.StudyYearJpaController;
import com.codemovers.scholar.engine.db.entities.SchoolData;
import com.codemovers.scholar.engine.db.entities.StudyYear;
import com.codemovers.scholar.engine.db.entities.Users;
import static com.codemovers.scholar.engine.helper.Utilities.check_access;
import com.codemovers.scholar.engine.helper.enums.StatusEnum;
import com.codemovers.scholar.engine.helper.exceptions.BadRequestException;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

/**
 *
 * @author mover 12/20/2017
 */
public class StudyYearService extends AbstractService<_StudyYear, StudyYearResponse> {

    private static final Logger LOG = Logger.getLogger(StudyYearService.class.getName());
    private final StudyYearJpaController controller;
    private static StudyYearService service = null;

    final String[] CREATE_STUDYEAR_PERMISSION = new String[]{"ALL_FUNCTIONS", "CREATE_STUDYEAR"};
    final String[] UPDATE_STUDYEAR_PERMISSION = new String[]{"ALL_FUNCTIONS", "UPDATE_STUDYEAR"};


    public StudyYearService() {
        controller = StudyYearJpaController.getInstance();
    }

    public static StudyYearService getInstance() {
        if (service == null) {
            service = new StudyYearService();
        }
        return service;
    }

    @Override
    public StudyYearResponse create(SchoolData data, _StudyYear entity, AuthenticationResponse authentication) throws Exception {
        check_access(CREATE_STUDYEAR_PERMISSION);
        entity.validate();

        entity.setAuthor_id(authentication.getId());
        entity.setStatus(StatusEnum.ACTIVE);

        StudyYear studyYear = new StudyYear();
        studyYear.setTheme(entity.getTheme());
        studyYear.setStartDate(entity.getStart_date());
        studyYear.setEndDate(entity.getEnd_date());
        studyYear.setStatus(entity.getStatus().toString());
        studyYear.setDateCreated(new Date());
        studyYear.setAuthor(new Users(entity.getAuthor_id().longValue()));
        studyYear = controller.create(studyYear, data);
        return populateResponse(studyYear);

    }

    @Override
    public StudyYearResponse update(SchoolData data, _StudyYear entity, AuthenticationResponse authentication) throws Exception {

        check_access(UPDATE_STUDYEAR_PERMISSION);
        entity.validate();

        //todo: get the entity by id if exists
        if (entity.getId() == null) {
            throw new BadRequestException("UNIQUE ID MISSING");
        }

        StudyYear studyYear = controller.findStudyYear(entity.getId(), data);

        if (entity.getTheme() != null && !entity.getTheme().equalsIgnoreCase(studyYear.getTheme())) {
            studyYear.setTheme(entity.getTheme());
        }

        if (entity.getStart_date() != null && entity.getStart_date() != (studyYear.getStartDate())) {
            studyYear.setStartDate(entity.getStart_date());
        }

        if (entity.getStart_date() != null && entity.getStart_date() != (studyYear.getEndDate())) {
            studyYear.setEndDate(entity.getEnd_date());
        }


        studyYear = controller.edit(studyYear, data);
        return populateResponse(studyYear);
    }

    @Override
    public StudyYearResponse archive(SchoolData data, Integer id) throws Exception {
        return super.archive(data, id); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public StudyYearResponse getById(SchoolData data, Integer Id) throws Exception {
        return super.getById(data, Id); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<StudyYearResponse> list(SchoolData data, Integer ofset, Integer limit) throws Exception {
        return super.list(data, ofset, limit); //To change body of generated methods, choose Tools | Templates.
    }

    public StudyYearResponse populateResponse(StudyYear entity) {
        StudyYearResponse response = new StudyYearResponse();

        response.setTheme(entity.getTheme());
        response.setStart_date(entity.getStartDate());
        response.setEnd_date(entity.getEndDate());
        response.setStatus(entity.getStatus());
        if (entity.getAuthor() != null) {
            response.setAuthor(entity.getAuthor().getUsername());
        }

        return response;
    }

}
