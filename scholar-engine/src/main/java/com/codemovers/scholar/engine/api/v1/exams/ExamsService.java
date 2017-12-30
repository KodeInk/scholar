/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codemovers.scholar.engine.api.v1.exams;

import com.codemovers.scholar.engine.api.v1.abstracts.AbstractService;
import com.codemovers.scholar.engine.api.v1.accounts.entities.AuthenticationResponse;
import com.codemovers.scholar.engine.api.v1.exams.entities.ExamResponse;
import com.codemovers.scholar.engine.api.v1.exams.entities._Exam;
import com.codemovers.scholar.engine.db.controllers.ExamsJpaController;
import com.codemovers.scholar.engine.db.entities.SchoolData;
import java.util.List;

/**
 *
 * @author mover 12/30/2017
 */
public class ExamsService extends AbstractService<_Exam, ExamResponse> {

    private final ExamsJpaController controller;
    private static ExamsService service = null;

    public ExamsService() {
        controller = ExamsJpaController.getInstance();
    }

    public static ExamsService getInstance() {
        if (service == null) {
            service = new ExamsService();
        }
        return service;
    }

    @Override
    public ExamResponse create(SchoolData data, _Exam entity, AuthenticationResponse authentication) throws Exception {
        return super.create(data, entity, authentication); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ExamResponse update(SchoolData data, _Exam entity, AuthenticationResponse authentication) throws Exception {
        return super.update(data, entity, authentication); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ExamResponse archive(SchoolData data, Integer id, AuthenticationResponse authentication) throws Exception {
        return super.archive(data, id, authentication); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ExamResponse> list(SchoolData data, Integer ofset, Integer limit, AuthenticationResponse authentication) throws Exception {
        return super.list(data, ofset, limit, authentication); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ExamResponse getById(SchoolData data, Integer Id) throws Exception {
        return super.getById(data, Id); //To change body of generated methods, choose Tools | Templates.
    }

}
