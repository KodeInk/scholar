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
import static com.codemovers.scholar.engine.helper.Utilities.check_access;
import com.codemovers.scholar.engine.helper.enums.StatusEnum;
import java.util.List;
import java.util.logging.Logger;

/**
 *
 * @author mover 12/20/2017
 */
public class SubjectService extends AbstractService<_Subject, SubjectResponse> {

    private static final Logger LOG = Logger.getLogger(SubjectService.class.getName());

    private final SubjectsJpaController controller;
    private static SubjectService service = null;

    final String[] CREATE_SUBJECT_PERMISSION = new String[]{"ALL_FUNCTIONS", "CREATE_SUBJECT"};


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


        return super.create(data, entity);
        //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SubjectResponse update(SchoolData data, _Subject entity) throws Exception {
        return super.update(data, entity); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SubjectResponse archive(SchoolData data, Integer id) throws Exception {
        return super.archive(data, id); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<SubjectResponse> list(SchoolData data, Integer ofset, Integer limit) throws Exception {
        return super.list(data, ofset, limit); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SubjectResponse getById(SchoolData data, Integer Id) throws Exception {
        return super.getById(data, Id); //To change body of generated methods, choose Tools | Templates.
    }

}
