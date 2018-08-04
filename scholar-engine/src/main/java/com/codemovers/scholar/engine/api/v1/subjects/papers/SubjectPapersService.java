/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codemovers.scholar.engine.api.v1.subjects.papers;

import com.codemovers.scholar.engine.api.v1.abstracts.AbstractService;
import com.codemovers.scholar.engine.api.v1.accounts.entities.AuthenticationResponse;
import com.codemovers.scholar.engine.api.v1.subjects.papers.entities.SubjectPapers;
import com.codemovers.scholar.engine.api.v1.subjects.papers.entities.SubjectPapersResponse;
import com.codemovers.scholar.engine.db.controllers.SubjectPapersJpaController;
import com.codemovers.scholar.engine.db.entities.SchoolData;
import com.codemovers.scholar.engine.helper.enums.StatusEnum;
import java.util.List;
import java.util.logging.Logger;

/**
 *
 * @author mover 1/3/2018
 */
public class SubjectPapersService extends AbstractService<SubjectPapers, SubjectPapersResponse> {

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

    /**
     *
     * @param data
     * @param entity
     * @param authentication
     * @return
     * @throws Exception
     */
    @Override
    public SubjectPapersResponse create(SchoolData data, SubjectPapers entity, AuthenticationResponse authentication) throws Exception {
        entity.validate();

        entity.setAuthor_id(authentication.getId());
        entity.setStatus(StatusEnum.ACTIVE);

        //todo: validate the entity
        //todo: check if there is no paper with the same name in the same subject 
        //todo: populate entity
        //todo:  create 
        return null;
    }

    @Override
    public SubjectPapersResponse update(SchoolData data, SubjectPapers entity, AuthenticationResponse authentication) throws Exception {
        return super.update(data, entity, authentication); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SubjectPapersResponse archive(SchoolData data, Integer id, AuthenticationResponse authentication) throws Exception {
        return super.archive(data, id, authentication); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<SubjectPapersResponse> list(SchoolData data, Integer ofset, Integer limit, AuthenticationResponse authentication) throws Exception {
        return super.list(data, ofset, limit, authentication); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SubjectPapersResponse getById(SchoolData data, Integer Id, AuthenticationResponse authentication) throws Exception {
        return super.getById(data, Id, authentication); //To change body of generated methods, choose Tools | Templates.
    }

}
