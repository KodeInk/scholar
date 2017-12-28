/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codemovers.scholar.engine.api.v1.curriculum.details;

import com.codemovers.scholar.engine.api.v1.abstracts.AbstractService;
import com.codemovers.scholar.engine.api.v1.accounts.entities.AuthenticationResponse;
import com.codemovers.scholar.engine.api.v1.curriculum.CurriculumService;
import com.codemovers.scholar.engine.api.v1.curriculum.details.entities.CurriculumDetailResponse;
import com.codemovers.scholar.engine.api.v1.curriculum.details.entities._CurriculumDetail;
import com.codemovers.scholar.engine.db.controllers.CurriculumDetailsJpaController;
import com.codemovers.scholar.engine.db.entities.SchoolData;
import java.util.List;
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

    @Override
    public CurriculumDetailResponse create(SchoolData data, _CurriculumDetail entity, AuthenticationResponse authentication) throws Exception {
        return super.create(data, entity, authentication); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public CurriculumDetailResponse update(SchoolData data, _CurriculumDetail entity, AuthenticationResponse authentication) throws Exception {
        return super.update(data, entity, authentication); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public CurriculumDetailResponse archive(SchoolData data, Integer id, AuthenticationResponse authentication) throws Exception {
        return super.archive(data, id, authentication); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<CurriculumDetailResponse> list(SchoolData data, Integer ofset, Integer limit, AuthenticationResponse authentication) throws Exception {
        return super.list(data, ofset, limit, authentication); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public CurriculumDetailResponse getById(SchoolData data, Integer Id) throws Exception {
        return super.getById(data, Id); //To change body of generated methods, choose Tools | Templates.
    }


}
