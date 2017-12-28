/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codemovers.scholar.engine.api.v1.curriculum;

import com.codemovers.scholar.engine.api.v1.abstracts.AbstractService;
import com.codemovers.scholar.engine.api.v1.accounts.entities.AuthenticationResponse;
import com.codemovers.scholar.engine.api.v1.curriculum.entities.CurriculumResponse;
import com.codemovers.scholar.engine.api.v1.curriculum.entities._Curriculum;
import com.codemovers.scholar.engine.db.controllers.CurriculumDetailsJpaController;
import com.codemovers.scholar.engine.db.controllers.CurriculumJpaController;
import com.codemovers.scholar.engine.db.entities.Curriculum;
import com.codemovers.scholar.engine.db.entities.SchoolData;
import com.codemovers.scholar.engine.db.entities.Users;
import static com.codemovers.scholar.engine.helper.Utilities.check_access;
import com.codemovers.scholar.engine.helper.enums.StatusEnum;
import java.util.Date;
import java.util.List;
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

    final String[] CREATE_CURRICULUM_PERMISSION = new String[]{"ALL_FUNCTIONS", "CREATE_CURRICULUM"};

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
    public CurriculumResponse create(SchoolData data, _Curriculum entity, AuthenticationResponse authentication) throws Exception {

        //todo: check access
        check_access(CREATE_CURRICULUM_PERMISSION);
        //todo: validate
        entity.validate();
        //todo: populate entity
        entity.setAuthor_id(authentication.getId());
        entity.setDate_created(new Date());
        entity.setStatus(StatusEnum.ACTIVE);
        //todo: create
        Curriculum curriculum = new Curriculum();
        curriculum.setCode(entity.getCode());
        curriculum.setName(entity.getName());
        curriculum.setDescription(entity.getDescription());
        curriculum.setStatus(entity.getStatus().toString());
        curriculum.setDateCreated(entity.getDate_created());
        curriculum.setAuthor(new Users(entity.getAuthor_id().longValue()));

        curriculum = controller.create(curriculum, data);

        return populateResponse(curriculum);
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

    public CurriculumResponse populateResponse(Curriculum entity) {
        CurriculumResponse response = new CurriculumResponse();
        response.setId(entity.getId().intValue());
        response.setName(entity.getName());
        response.setCode(entity.getCode());
        response.setDescription(entity.getDescription());
        response.setStatus(entity.getStatus());
        if (entity.getAuthor() != null) {
            response.setAuthor(entity.getAuthor().getUsername());
        }

        return response;
    }
}
