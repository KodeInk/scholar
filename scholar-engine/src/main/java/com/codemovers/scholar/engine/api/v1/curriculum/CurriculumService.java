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
import com.codemovers.scholar.engine.helper.exceptions.BadRequestException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

/**
 *
 * @author mover 12/28/2017
 */
public class CurriculumService extends AbstractService<_Curriculum, CurriculumResponse> {

    private static final Logger LOG = Logger.getLogger(CurriculumService.class.getName());

    private final CurriculumJpaController controller;
    private final CurriculumDetailsJpaController detailsJpaController;
    private static CurriculumService service = null;

    final String[] CREATE_CURRICULUM_PERMISSION = new String[]{"ALL_FUNCTIONS", "CREATE_CURRICULUM"};
    final String[] ARCHIVE_CURRICULUM_PERMISSION = new String[]{"ALL_FUNCTIONS", "ARCHIVE_CURRICULUM"};
    final String[] UPDATE_CURRICULUM_PERMISSION = new String[]{"ALL_FUNCTIONS", "UPDATE_CURRICULUM"};
    final String[] LIST_CURRICULUM_PERMISSION = new String[]{"ALL_FUNCTIONS", "LIST_CURRICULUM"};

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
        Curriculum curriculum = populateEntity(entity, authentication);
        //A bug right here
        validateCurriculumExists(curriculum, data);
        curriculum = controller.create(curriculum, data);
        return populateResponse(curriculum);
    }

    @Override
    public CurriculumResponse update(SchoolData data, _Curriculum entity, AuthenticationResponse authenticationResponse) throws Exception {
        check_access(UPDATE_CURRICULUM_PERMISSION);
        entity.validate();
        //todo: get the entity by id if exists
        if (entity.getId() == null) {
            throw new BadRequestException("UNIQUE ID MISSING");
        }

        Curriculum curriculum = getCurriculum(entity.getId(), data);

        if (entity.getName() != null && !entity.getName().equalsIgnoreCase(curriculum.getName())) {
            curriculum.setName(entity.getName());
        }

        if (entity.getCode() != null && !entity.getCode().equalsIgnoreCase(curriculum.getCode())) {
            curriculum.setCode(entity.getCode());
        }

        if (entity.getDescription() != null && !entity.getDescription().equalsIgnoreCase(curriculum.getDescription())) {
            curriculum.setDescription(entity.getDescription());
        }

        validateCurriculumExists(curriculum, data);
        curriculum = controller.edit(curriculum, data);

        return populateResponse(curriculum);
    }

    @Override
    public CurriculumResponse archive(SchoolData data, Integer id) throws Exception {
        check_access(ARCHIVE_CURRICULUM_PERMISSION);
        Curriculum _Curriculum = getCurriculum(id, data);

        if (_Curriculum == null) {
            throw new BadRequestException("Record does not exist");
        }
        _Curriculum.setStatus(StatusEnum.ARCHIVED.toString());
        _Curriculum = controller.edit(_Curriculum, data);
        return populateResponse(_Curriculum);

    }

    @Override
    public List<CurriculumResponse> list(SchoolData data, Integer ofset, Integer limit, AuthenticationResponse authenticationResponse) throws Exception {
        // check_access(LIST_CURRICULUM_PERMISSION);

        List<Curriculum> list = controller.findCurriculumEntities(limit, ofset, data);
        List<CurriculumResponse> responses = new ArrayList<>();
        if (list != null) {
            list.forEach((_class) -> {
                responses.add(populateResponse(_class));
            });
        }
        return responses;
    }

    @Override
    public CurriculumResponse getById(SchoolData data, Integer Id) throws Exception {
        check_access(LIST_CURRICULUM_PERMISSION);
        Curriculum _Curriculum = getCurriculum(Id, data);
        if (_Curriculum == null) {
            throw new BadRequestException("Record does not exist");
        }

        return populateResponse(_Curriculum);

    }

    public Curriculum getCurriculum(Integer Id, SchoolData data) {
        //todo: get class by id
        Curriculum _Curriculum = controller.findCurriculum(Id, data);
        return _Curriculum;
    }

    public void validateCurriculumExists(Curriculum curriculum, SchoolData data) throws BadRequestException {
        //todo: check to see that there is no curriculum with the same name
        List<Curriculum> curriculums = null;
        if(curriculum.getId() == null){
           curriculums = controller.findCurriculumByNameCode(curriculum.getName(), curriculum.getCode(), data);
       
        }else
        {
           curriculums = controller.findCurriculumByNameCode(curriculum.getName(), curriculum.getCode(), curriculum.getId().intValue(), data);
        
        }
        
        
        if (curriculums.size() > 0) {
            throw new BadRequestException("Curriculum with the same exists in the database");
        }
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
        response.setDate_created(entity.getDateCreated().getTime());
        return response;
    }

    public Curriculum populateEntity(_Curriculum entity, AuthenticationResponse authentication) {
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
        return curriculum;
    }

}
