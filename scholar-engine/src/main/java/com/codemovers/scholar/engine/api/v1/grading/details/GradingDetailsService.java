/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codemovers.scholar.engine.api.v1.grading.details;

import com.codemovers.scholar.engine.api.v1.abstracts.AbstractService;
import com.codemovers.scholar.engine.api.v1.accounts.entities.AuthenticationResponse;
import com.codemovers.scholar.engine.api.v1.grading.GradingService;
import com.codemovers.scholar.engine.api.v1.grading.details.entities.GradingDetail;
import com.codemovers.scholar.engine.api.v1.grading.details.entities.GradingDetailResponse;
import com.codemovers.scholar.engine.api.v1.grading.entities.GradingResponse;
import com.codemovers.scholar.engine.db.controllers.GradingDetailsJpaController;
import com.codemovers.scholar.engine.db.entities.GradingDetails;
import com.codemovers.scholar.engine.db.entities.SchoolData;
import com.codemovers.scholar.engine.helper.exceptions.BadRequestException;
import java.util.ArrayList;
import java.util.List;
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

    @Override
    public GradingDetailResponse create(SchoolData data, GradingDetail entity, AuthenticationResponse authentication) throws Exception {
        //todo: validate entity
        entity.validate();
        //todo: find if there exists the same 
        List<GradingDetails> gds = controller.findGradingDetailEntities(entity.getSymbol(), entity.getMin_grade(), entity.getMax_grade(),entity.getGrading_scale(), 0, 1, data);

        if(gds != null  && gds.size() > 0  ){
                throw new BadRequestException("Grading detail Exists with the same symbol or code in the same Grading ");
        }
        
        return super.create(data, entity, authentication);
        //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public GradingDetailResponse update(SchoolData data, GradingDetail entity, AuthenticationResponse authentication) throws Exception {
        return super.update(data, entity, authentication); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @param data
     * @param ofset
     * @param limit
     * @param authentication
     * @return
     * @throws Exception
     */
    @Override
    public List<GradingDetailResponse> list(SchoolData data, Integer ofset, Integer limit, AuthenticationResponse authentication) throws Exception {

        List<GradingDetails> list = controller.findGradingDetailEntities(limit, ofset, data);
        List<GradingDetailResponse> responses = new ArrayList<>();

        if (list != null) {
            list.forEach((gradingDetail) -> {
                responses.add(populateResponse(gradingDetail));
            });
        }
        return responses;
    }

    @Override
    public GradingDetailResponse getById(SchoolData data, Integer Id, AuthenticationResponse authentication) throws Exception {
        return super.getById(data, Id, authentication); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<GradingDetailResponse> search(SchoolData data, String query, Integer ofset, Integer limit, AuthenticationResponse authentication) throws Exception {
        return super.search(data, query, ofset, limit, authentication); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public GradingDetailResponse archive(SchoolData data, Integer id, AuthenticationResponse authentication) throws Exception {
        return super.archive(data, id, authentication); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @param gradingDetail
     * @return
     */
    public GradingDetailResponse populateResponse(GradingDetails gradingDetail) {
        GradingDetailResponse detailResponse = new GradingDetailResponse();
        if (gradingDetail.getGradingScale() != null) {
            GradingResponse gradingResponse = GradingService.getInstance().populateResponse(gradingDetail.getGradingScale());
            detailResponse.setGradingScale(gradingResponse);
        }
        detailResponse.setId(gradingDetail.getId().intValue());
        detailResponse.setDate_created(gradingDetail.getDateCreated().getTime());
        detailResponse.setMax_grade(gradingDetail.getMaxgrade());
        detailResponse.setMin_grade(gradingDetail.getMingrade());
        detailResponse.setStatus(gradingDetail.getStatus());
        detailResponse.setSymbol(gradingDetail.getStatus());

        if (gradingDetail.getAuthor() != null) {
            detailResponse.setAuthor(gradingDetail.getAuthor().getUsername());
        }

        return detailResponse;
    }

}
