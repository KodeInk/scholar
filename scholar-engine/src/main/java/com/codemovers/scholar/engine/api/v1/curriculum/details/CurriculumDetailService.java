/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codemovers.scholar.engine.api.v1.curriculum.details;

import com.codemovers.scholar.engine.api.v1.abstracts.AbstractService;
import com.codemovers.scholar.engine.api.v1.curriculum.CurriculumService;
import com.codemovers.scholar.engine.api.v1.curriculum.details.entities.CurriculumDetailResponse;
import com.codemovers.scholar.engine.api.v1.curriculum.details.entities._CurriculumDetail;
import com.codemovers.scholar.engine.db.controllers.CurriculumDetailsJpaController;
import com.codemovers.scholar.engine.db.controllers.CurriculumJpaController;
import java.util.logging.Logger;

/**
 *
 * @author mover 12/28/2017
 */
public class CurriculumDetailService extends AbstractService<_CurriculumDetail, CurriculumDetailResponse> {

    private static final Logger LOG = Logger.getLogger(CurriculumService.class.getName());

    private final CurriculumDetailsJpaController controller;

    private static CurriculumDetailService service = null;

    public CurriculumDetailService() {
        controller = CurriculumDetailsJpaController.getInstance();

    }

    public static CurriculumDetailService getInstance() {
        if (service == null) {
            service = new CurriculumDetailService();
        }
        return service;
    }


}
