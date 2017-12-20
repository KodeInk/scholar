/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codemovers.scholar.engine.api.v1.studyear;

import com.codemovers.scholar.engine.api.v1.abstracts.AbstractService;
import com.codemovers.scholar.engine.api.v1.studyear.entities.StudyYearResponse;
import com.codemovers.scholar.engine.api.v1.studyear.entities._StudyYear;
import com.codemovers.scholar.engine.db.controllers.StudyYearJpaController;
import com.codemovers.scholar.engine.db.entities.SchoolData;
import java.util.logging.Logger;

/**
 *
 * @author mover 12/20/2017
 */
public class StudyYearService extends AbstractService<_StudyYear, StudyYearResponse> {

    private static final Logger LOG = Logger.getLogger(StudyYearService.class.getName());

    private final StudyYearJpaController controller;

    private static StudyYearService service = null;

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
    public StudyYearResponse create(SchoolData data, _StudyYear entity) throws Exception {
        return super.create(data, entity); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public StudyYearResponse update(SchoolData data, _StudyYear entity) throws Exception {
        return super.update(data, entity); //To change body of generated methods, choose Tools | Templates.
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
    public StudyYearResponse list(SchoolData data, Integer ofset, Integer limit) throws Exception {
        return super.list(data, ofset, limit); //To change body of generated methods, choose Tools | Templates.
    }

}
