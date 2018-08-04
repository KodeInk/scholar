/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codemovers.scholar.engine.api.v1.subjects.papers;

import com.codemovers.scholar.engine.api.v1.abstracts.AbstractService;
import com.codemovers.scholar.engine.api.v1.accounts.entities.AuthenticationResponse;
import com.codemovers.scholar.engine.api.v1.subjects.entities.SubjectPaperResponse;
import com.codemovers.scholar.engine.api.v1.subjects.papers.entities.SubjectPapers;
import com.codemovers.scholar.engine.db.controllers.SubjectPapersJpaController;
import com.codemovers.scholar.engine.db.entities.SchoolData;
import java.util.List;
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

    @Override
    public SubjectPaperResponse create(SchoolData data, SubjectPapers entity, AuthenticationResponse authentication) throws Exception {
        return super.create(data, entity, authentication); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SubjectPaperResponse update(SchoolData data, SubjectPapers entity, AuthenticationResponse authentication) throws Exception {
        return super.update(data, entity, authentication); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SubjectPaperResponse archive(SchoolData data, Integer id, AuthenticationResponse authentication) throws Exception {
        return super.archive(data, id, authentication); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<SubjectPaperResponse> list(SchoolData data, Integer ofset, Integer limit, AuthenticationResponse authentication) throws Exception {
        return super.list(data, ofset, limit, authentication); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SubjectPaperResponse getById(SchoolData data, Integer Id, AuthenticationResponse authentication) throws Exception {
        return super.getById(data, Id, authentication); //To change body of generated methods, choose Tools | Templates.
    }

    
    
    
}
