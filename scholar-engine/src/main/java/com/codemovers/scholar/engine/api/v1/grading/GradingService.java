/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codemovers.scholar.engine.api.v1.grading;

import com.codemovers.scholar.engine.api.v1.abstracts.AbstractService;
import com.codemovers.scholar.engine.api.v1.grading.entities.GradingResponse;
import com.codemovers.scholar.engine.api.v1.grading.entities._Grading;
import com.codemovers.scholar.engine.db.controllers.GradingDetailsJpaController;
import com.codemovers.scholar.engine.db.controllers.GradingJpaController;
import com.codemovers.scholar.engine.db.entities.SchoolData;
import java.util.List;
import java.util.logging.Logger;

/**
 *
 * @author mover 12/20/2017
 */
public class GradingService extends AbstractService<_Grading, GradingResponse> {

    private static final Logger LOG = Logger.getLogger(GradingService.class.getName());

    private final GradingJpaController controller;
    private final GradingDetailsJpaController detailsJpaController;

    private static GradingService service = null;

    public GradingService() {
        controller = GradingJpaController.getInstance();
        detailsJpaController = GradingDetailsJpaController.getInstance();
    }

    public static GradingService getInstance() {
        if (service == null) {
            service = new GradingService();
        }
        return service;
    }

    @Override
    public GradingResponse create(SchoolData data, _Grading entity) throws Exception {
        return super.create(data, entity); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public GradingResponse update(SchoolData data, _Grading entity) throws Exception {
        return super.update(data, entity); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public GradingResponse archive(SchoolData data, Integer id) throws Exception {
        return super.archive(data, id); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<GradingResponse> list(SchoolData data, Integer ofset, Integer limit) throws Exception {
        return super.list(data, ofset, limit); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public GradingResponse getById(SchoolData data, Integer Id) throws Exception {
        return super.getById(data, Id); //To change body of generated methods, choose Tools | Templates.
    }

}
