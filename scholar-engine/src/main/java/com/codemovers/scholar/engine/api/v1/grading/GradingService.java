/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codemovers.scholar.engine.api.v1.grading;

import com.codemovers.scholar.engine.api.v1.abstracts.AbstractService;
import com.codemovers.scholar.engine.api.v1.accounts.entities.AuthenticationResponse;
import static com.codemovers.scholar.engine.api.v1.classes.ClassServiceInterface.ARCHIVE_CLASS_PERMISSION;
import com.codemovers.scholar.engine.api.v1.grading.entities.GradingResponse;
import com.codemovers.scholar.engine.api.v1.grading.entities.Gradings;
import com.codemovers.scholar.engine.db.controllers.GradingDetailsJpaController;
import com.codemovers.scholar.engine.db.controllers.GradingJpaController;
import com.codemovers.scholar.engine.db.entities.Grading;
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
 * @author mover 12/20/2017
 */
public class GradingService extends AbstractService<Gradings, GradingResponse> {

    private static final Logger LOG = Logger.getLogger(GradingService.class.getName());
    private final GradingJpaController controller;
    private final GradingDetailsJpaController detailsJpaController;
    private static GradingService service = null;

    /**
     *
     */
    public GradingService() {
        controller = GradingJpaController.getInstance();
        detailsJpaController = GradingDetailsJpaController.getInstance();
    }

    /**
     *
     * @return
     */
    public static GradingService getInstance() {
        if (service == null) {
            service = new GradingService();
        }
        return service;
    }

    /**
     *
     * @param data
     * @param entity
     * @param authentication
     * @return
     * @throws Exception
     */
    @Override
    public GradingResponse create(SchoolData data, Gradings entity, AuthenticationResponse authentication) throws Exception {
        entity.validate();
        List<Grading> gradings = controller.findGrading(entity.getName(), entity.getCode(), data);
        if (gradings.size() > 0) {
            throw new BadRequestException("A grading exists with the same name or code");
        }
        Grading grading = populateEntity(entity, authentication);
        grading = controller.create(grading, data);
        return populateResponse(grading);

    }

    /**
     *
     * @param data
     * @param entity
     * @param authentication
     * @return
     * @throws Exception
     */
    @Override
    public GradingResponse update(SchoolData data, Gradings entity, AuthenticationResponse authentication) throws Exception {
        entity.validate();
        if (entity.getId() == null) {
            throw new BadRequestException("UNIQUE ID MISSING");
        }

        Grading grading = getGrading(entity.getId(), data);
        if (grading == null) {
            throw new BadRequestException(" Record does not exist in the database");
        }

        populateEntity(entity, grading);

        List<Grading> gradings = controller.findGrading(entity.getName(), entity.getCode(), entity.getId(), data);
        if (gradings.size() > 0) {
            throw new BadRequestException("A grading exists with the same name or code");
        }
        grading = controller.edit(grading, data); 
        
        return populateResponse(grading);
    }

    /**
     *
     * @param id
     * @param data
     * @return
     */
    public Grading getGrading(Integer id, SchoolData data) {
        Grading grading = controller.findGrading(id, data);
        return grading;
    }

    /**
     *
     * @param data
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public GradingResponse archive(SchoolData data, Integer id) throws Exception {
        return super.archive(data, id); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @param data
     * @param ofset
     * @param limit
     * @param authenticationResponse
     * @return
     * @throws Exception
     */
    @Override
    public List<GradingResponse> list(SchoolData data, Integer ofset, Integer limit, AuthenticationResponse authenticationResponse) throws Exception {

        List<Grading> list = controller.findGradingEntities(limit, ofset, data);
        List<GradingResponse> responses = new ArrayList<>();
        if (list != null) {
            list.forEach((_class) -> {
                responses.add(populateResponse(_class));
            });
        }

        return responses;
    }

    /**
     *
     * @param data
     * @param query
     * @param ofset
     * @param limit
     * @param authentication
     * @return
     * @throws Exception
     */
    @Override
    public List<GradingResponse> search(SchoolData data, String query, Integer ofset, Integer limit, AuthenticationResponse authentication) throws Exception {
        check_access(ARCHIVE_CLASS_PERMISSION);

        List<Grading> list = controller.query(query, limit, ofset, data);

        List<GradingResponse> classResponses = new ArrayList<>();
        list.forEach(respond -> {
            classResponses.add(populateResponse(respond));
        });

        return classResponses;

    }

    /**
     *
     * @param data
     * @param Id
     * @return
     * @throws Exception
     */
    @Override
    public GradingResponse getById(SchoolData data, Integer Id) throws Exception {
        return super.getById(data, Id); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @param entity
     * @return
     */
    public GradingResponse populateResponse(Grading entity) {
        GradingResponse response = new GradingResponse();
        if (entity != null) {
            response.setId(entity.getId().intValue());
            response.setName(entity.getName());
            response.setCode(entity.getCode());
            response.setDescription(entity.getDescription());
            response.setStatus(StatusEnum.fromString(entity.getStatus()));
            response.setDateCreated(entity.getDateCreated().getTime());

            if (entity.getAuthor() != null) {
                response.setAuthor(entity.getAuthor().getUsername());
            }
        }

        return response;
    }

    /**
     *
     * @param entity
     * @param authentication
     * @return
     */
    public Grading populateEntity(Gradings entity, AuthenticationResponse authentication) {
        //todo: populate entity
        Grading grading = new Grading();
        grading.setName(entity.getName());
        grading.setCode(entity.getCode());
        grading.setDescription(entity.getDescription());
        grading.setAuthor(new Users(authentication.getId().longValue()));
        grading.setStatus(StatusEnum.ACTIVE.toString());
        grading.setDateCreated(new Date());
        return grading;
    }

    /**
     *
     * @param entity
     * @param grading
     */
    public void populateEntity(Gradings entity, Grading grading) {
        if (entity.getName() != null && !entity.getName().equalsIgnoreCase(grading.getName())) {
            grading.setName(entity.getName());
        }

        if (entity.getCode() != null && !entity.getCode().equalsIgnoreCase(grading.getCode())) {
            grading.setCode(entity.getCode());
        }

        if (entity.getDescription() != null && !entity.getDescription().equalsIgnoreCase(grading.getDescription())) {
            grading.setDescription(entity.getDescription());
        }
        
    }

}
