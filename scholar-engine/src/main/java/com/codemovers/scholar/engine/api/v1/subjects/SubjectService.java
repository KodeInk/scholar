/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codemovers.scholar.engine.api.v1.subjects;

import com.codemovers.scholar.engine.api.v1.abstracts.AbstractService;
import com.codemovers.scholar.engine.api.v1.subjects.entities.SubjectResponse;
import com.codemovers.scholar.engine.api.v1.subjects.entities._Subject;
import com.codemovers.scholar.engine.db.controllers.SubjectsJpaController;
import java.util.logging.Logger;

/**
 *
 * @author mover 12/20/2017
 */
public class SubjectService extends AbstractService<_Subject, SubjectResponse> {

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



}
