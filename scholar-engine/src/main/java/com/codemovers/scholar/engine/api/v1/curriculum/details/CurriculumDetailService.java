/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codemovers.scholar.engine.api.v1.curriculum.details;

import com.codemovers.scholar.engine.api.v1.abstracts.AbstractService;
import com.codemovers.scholar.engine.api.v1.accounts.entities.AuthenticationResponse;
import static com.codemovers.scholar.engine.api.v1.classes.ClassServiceInterface.CREATE_CLASS_PERMISSION;
import static com.codemovers.scholar.engine.api.v1.classes.ClassServiceInterface.UPDATE_CLASS_PERMISSION;
import com.codemovers.scholar.engine.api.v1.curriculum.CurriculumService;
import com.codemovers.scholar.engine.api.v1.curriculum.details.entities.CurriculumDetailResponse;
import com.codemovers.scholar.engine.api.v1.curriculum.details.entities._CurriculumDetail;
import com.codemovers.scholar.engine.db.controllers.CurriculumDetailsJpaController;
import com.codemovers.scholar.engine.db.entities.CurriculumDetails;
import com.codemovers.scholar.engine.db.entities.SchoolData;
import com.codemovers.scholar.engine.db.entities.Users;
import static com.codemovers.scholar.engine.helper.Utilities.check_access;
import com.codemovers.scholar.engine.helper.enums.StatusEnum;
import com.codemovers.scholar.engine.helper.exceptions.BadRequestException;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

/**
 *
 * @author mover 12/28/2017
 */
public class CurriculumDetailService extends AbstractService<_CurriculumDetail, CurriculumDetailResponse> {

    final String[] CREATE_CURRICULUMDETAIL_PERMISSION = new String[]{"ALL_FUNCTIONS", "CREATE_CURRICULUMDETAIL"};
    final String[] UPDATE_CURRICULUMDETAIL_PERMISSION = new String[]{"ALL_FUNCTIONS", "UPDATE_CURRICULUMDETAIL"};

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
