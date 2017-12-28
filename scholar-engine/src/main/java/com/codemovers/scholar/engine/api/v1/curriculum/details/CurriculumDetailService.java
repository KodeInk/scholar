/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codemovers.scholar.engine.api.v1.curriculum.details;

import com.codemovers.scholar.engine.api.v1.abstracts.AbstractService;
import com.codemovers.scholar.engine.api.v1.accounts.entities.AuthenticationResponse;
import static com.codemovers.scholar.engine.api.v1.classes.ClassServiceInterface.LIST_CLASSES_PERMISSION;
import com.codemovers.scholar.engine.api.v1.classes.entities.ClassResponse;
import com.codemovers.scholar.engine.api.v1.curriculum.CurriculumService;
import com.codemovers.scholar.engine.api.v1.curriculum.details.entities.CurriculumDetailResponse;
import com.codemovers.scholar.engine.api.v1.curriculum.details.entities._CurriculumDetail;
import com.codemovers.scholar.engine.db.controllers.CurriculumDetailsJpaController;
import com.codemovers.scholar.engine.db.entities.Classes;
import com.codemovers.scholar.engine.db.entities.CurriculumDetails;
import com.codemovers.scholar.engine.db.entities.SchoolData;
import com.codemovers.scholar.engine.db.entities.Users;
import static com.codemovers.scholar.engine.helper.Utilities.check_access;
import com.codemovers.scholar.engine.helper.enums.StatusEnum;
import com.codemovers.scholar.engine.helper.exceptions.BadRequestException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

/**
 *
 * @author mover 12/28/2017
 */
public class CurriculumDetailService extends AbstractService<_CurriculumDetail, CurriculumDetailResponse> implements CurriculumDetailServiceInterface {

    final String[] CREATE_CURRICULUMDETAIL_PERMISSION = new String[]{"ALL_FUNCTIONS", "CREATE_CURRICULUMDETAIL"};
    final String[] UPDATE_CURRICULUMDETAIL_PERMISSION = new String[]{"ALL_FUNCTIONS", "UPDATE_CURRICULUMDETAIL"};
    final String[] ARCHIVE_CURRICULUMDETAIL_PERMISSION = new String[]{"ALL_FUNCTIONS", "ARCHIVE_CURRICULUMDETAIL"};
    final String[] LIST_CURRICULUMDETAIL_PERMISSION = new String[]{"ALL_FUNCTIONS", "LIST_CURRICULUMDETAIL"};

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
        check_access(CREATE_CURRICULUMDETAIL_PERMISSION);
        entity.validate();
        entity.setAuthor_id(authentication.getId());
        entity.setStatus(StatusEnum.ACTIVE);
        entity.setDate_created(new Date());

        CurriculumDetails details = new CurriculumDetails();
        details.setCode(entity.getCode());
        details.setName(entity.getName());
        details.setDescription(entity.getDescription());
        details.setStatus(entity.getStatus().toString());
        details.setAuthor(new Users(entity.getAuthor_id().longValue()));

        details = controller.create(details, data);

        return populateResponse(details);
    }

    @Override
    public CurriculumDetailResponse update(SchoolData data, _CurriculumDetail entity, AuthenticationResponse authentication) throws Exception {
        check_access(UPDATE_CURRICULUMDETAIL_PERMISSION);
        entity.validate();

        if (entity.getId() == null) {
            throw new BadRequestException("UNIQUE ID MISSING");
        }
        CurriculumDetails details = controller.findCurriculumDetail(entity.getId(), data);

        if (entity.getName() != null && !entity.getName().equalsIgnoreCase(details.getName())) {
            details.setName(entity.getName());
        }

        if (entity.getCode() != null && !entity.getCode().equalsIgnoreCase(details.getCode())) {
            details.setCode(entity.getCode());
        }

        if (entity.getDescription() != null && !entity.getDescription().equalsIgnoreCase(details.getDescription())) {
            details.setDescription(entity.getDescription());
        }

        details = controller.edit(details, data);

        return populateResponse(details);
    }

    @Override
    public CurriculumDetailResponse archive(SchoolData data, Integer id, AuthenticationResponse authentication) throws Exception {
        check_access(ARCHIVE_CURRICULUMDETAIL_PERMISSION);

        CurriculumDetails _curriculumDetail = controller.findCurriculumDetail(id, data);
        if (_curriculumDetail == null) {
            throw new BadRequestException("Record does not exist");
        }
        _curriculumDetail.setStatus(StatusEnum.ARCHIVED.toString());
        _curriculumDetail = controller.edit(_curriculumDetail, data);
        return populateResponse(_curriculumDetail);

    }

    @Override
    public List<CurriculumDetailResponse> list(SchoolData data, Integer ofset, Integer limit, AuthenticationResponse authentication) throws Exception {
        //todo: check list classes permissions
        check_access(LIST_CURRICULUMDETAIL_PERMISSION);
        //todo: you will need logging of  every operation of a logged in user
        //todo, get list  a range from the  jpa controller
        List<CurriculumDetails> list = controller.findCurriculumDetailEntities(ofset, limit, data);
        List<CurriculumDetailResponse> responses = new ArrayList<>();
        if (list != null) {
            list.forEach((_class) -> {
                responses.add(populateResponse(_class));
            });
        }

        return responses;

    }

    @Override
    public CurriculumDetailResponse getById(SchoolData data, Integer Id) throws Exception {
        check_access(LIST_CURRICULUMDETAIL_PERMISSION);
        //todo: get class by id
        CurriculumDetails _detail = controller.findCurriculumDetail(Id, data);
        if (_detail == null) {
            throw new BadRequestException("Record does not exist");
        }

        return populateResponse(_detail);


    }

    @Override
    public CurriculumDetailResponse populateResponse(CurriculumDetails entity) {
        CurriculumDetailResponse response = new CurriculumDetailResponse();
        response.setName(entity.getName());
        response.setCode(entity.getCode());
        response.setDescription(entity.getDescription());
        response.setDate_created(entity.getDateCreated());
        if (entity.getAuthor() != null) {
            response.setAuthor(entity.getAuthor().getUsername());
        }

        return response;
    }

}
