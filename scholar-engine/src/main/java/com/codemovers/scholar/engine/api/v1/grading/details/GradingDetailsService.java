/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codemovers.scholar.engine.api.v1.grading.details;

import com.codemovers.scholar.engine.api.v1.abstracts.AbstractService;
import com.codemovers.scholar.engine.api.v1.grading.GradingService;
import com.codemovers.scholar.engine.api.v1.grading.details.entities.GradingDetail;
import com.codemovers.scholar.engine.api.v1.grading.details.entities.GradingDetailResponse;
import com.codemovers.scholar.engine.db.controllers.GradingDetailsJpaController;
import java.util.logging.Logger;

/**
 *
 * @author mover 8/19/2018
 */
public class GradingDetailsService extends AbstractService<GradingDetail, GradingDetailResponse> {

    private static final Logger LOG = Logger.getLogger(GradingService.class.getName());
    private final GradingDetailsJpaController controller;
    private static GradingDetailsService service = null;

    public GradingDetailsService() {
        controller = GradingDetailsJpaController.getInstance();
    }

    public static GradingDetailsService getInstance() {
        if (service == null) {
            service = new GradingDetailsService();
        }
        return service;
    }
    
    
    

}
