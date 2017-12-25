/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codemovers.scholar.engine.api.v1.curriculum;

import com.codemovers.scholar.engine.api.v1.abstracts.AbstractService;
import com.codemovers.scholar.engine.api.v1.curriculum.entities.CurriculumResponse;
import com.codemovers.scholar.engine.api.v1.curriculum.entities._Curriculum;
import com.codemovers.scholar.engine.db.controllers.CurriculumDetailsJpaController;
import com.codemovers.scholar.engine.db.controllers.CurriculumJpaController;
import com.codemovers.scholar.engine.db.entities.SchoolData;
import java.util.logging.Logger;

/**
 *
 * @author mover
 */
public class CurriculumService extends AbstractService<_Curriculum, CurriculumResponse> {

    private static final Logger LOG = Logger.getLogger(CurriculumService.class.getName());

    private final CurriculumJpaController controller;
    private final CurriculumDetailsJpaController detailsJpaController;

    private static CurriculumService service = null;

    public CurriculumService() {
        controller = CurriculumJpaController.getInstance();
        detailsJpaController = CurriculumDetailsJpaController.getInstance();
    }

    public static CurriculumService getInstance() {
        if (service == null) {
            service = new CurriculumService();
        }
        return service;
    }

    @Override
    public CurriculumResponse create(SchoolData data, _Curriculum entity) throws Exception {
        return super.create(data, entity); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public CurriculumResponse update(SchoolData data, _Curriculum entity) throws Exception {
        return super.update(data, entity); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public CurriculumResponse archive(SchoolData data, Integer id) throws Exception {
        return super.archive(data, id); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<CurriculumResponse> list(SchoolData data, Integer ofset, Integer limit) throws Exception {
        return super.list(data, ofset, limit); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public CurriculumResponse getById(SchoolData data, Integer Id) throws Exception {
        return super.getById(data, Id); //To change body of generated methods, choose Tools | Templates.
    }

}
