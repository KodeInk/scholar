/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codemovers.scholar.engine.api.v1.subjects.papers;

import com.codemovers.scholar.engine.api.v1.abstracts.AbstractService;
import com.codemovers.scholar.engine.api.v1.subjects.entities.SubjectPaperResponse;
import com.codemovers.scholar.engine.api.v1.subjects.papers.entities.SubjectPapers;
import com.codemovers.scholar.engine.db.controllers.SubjectPapersJpaController;
import java.util.logging.Logger;

/**
 *
 * @author mover 1/3/2018
 */
public class SubjectPapersService extends AbstractService<SubjectPapers, SubjectPaperResponse> {

    private static final Logger LOG = Logger.getLogger(SubjectPapersService.class.getName());
    private final SubjectPapersJpaController controller;
    private static SubjectPapersService service = null;

    public SubjectPapersService() {
        this.controller = new SubjectPapersJpaController();
    }

    public static SubjectPapersService getInstance() {
        if (service == null) {
            service = new SubjectPapersService();
        }
        return service;
    }

}
