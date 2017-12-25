/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codemovers.scholar.engine.api.v1.classes;

import com.codemovers.scholar.engine.api.v1.abstracts.AbstractService;
import com.codemovers.scholar.engine.api.v1.accounts.entities.AuthenticationResponse;
import com.codemovers.scholar.engine.api.v1.classes.entities.ClassResponse;
import com.codemovers.scholar.engine.api.v1.classes.entities._Class;
import com.codemovers.scholar.engine.api.v1.users.UserService;
import com.codemovers.scholar.engine.db.controllers.ClassJpaController;
import com.codemovers.scholar.engine.db.entities.Classes;
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
 * @author mover 12/19/2017
 */
public class ClassService extends AbstractService<_Class, ClassResponse> {

    private static final Logger LOG = Logger.getLogger(UserService.class.getName());
    private final ClassJpaController controller;
    private static ClassService service = null;
    private final String[] CREATE_CLASS_PERMISSION = new String[]{"ALL_FUNCTIONS", "CREATE_CLASS"};
    private final String[] LIST_CLASSES_PERMISSION = new String[]{"ALL_FUNCTIONS", "LIST_CLASSES"};
    private final String[] UPDATE_CLASS_PERMISSION = new String[]{"ALL_FUNCTIONS", "UPDATE_CLASS"};


    public ClassService() {
        controller = ClassJpaController.getInstance();
    }

    public static ClassService getInstance() {
        if (service == null) {
            service = new ClassService();
        }
        return service;
    }

    @Override
    public ClassResponse create(SchoolData data, _Class entity, AuthenticationResponse authentication) throws Exception {
        //todo: check permissions
        check_access(CREATE_CLASS_PERMISSION);
        //todo: validate the class creation
        entity.validate();
        //todo: add author_id,  add status enum
        entity.setAuthor_id(authentication.getId());
        entity.setStatus(StatusEnum.ACTIVE);
        // call the controller and create the class
        Classes classes = new Classes();
        classes.setAuthor(new Users(entity.getAuthor_id().longValue()));
        classes.setName(entity.getName());
        classes.setCode(entity.getCode());
        classes.setRanking(entity.getRanking());
        classes.setDateCreated(new Date());

        classes = controller.create(classes, data);
        return populateResponse(classes);

    }

    @Override
    public List<ClassResponse> list(SchoolData data, Integer ofset, Integer limit, AuthenticationResponse authentication) throws Exception {
        //todo: check list classes permissions
        check_access(LIST_CLASSES_PERMISSION);
        //todo: you will need logging of  every operation of a logged in user
        //todo, get list  a range from the  jpa controller
      List<Classes> list = controller.findClassEntities(ofset, limit, data);
        List<ClassResponse> responses = new ArrayList<>();
        if (list != null) {
            list.forEach((_class) -> {
                responses.add(populateResponse(_class));
            });
        }

        return responses;
    }

    @Override
    public ClassResponse delete(SchoolData data, Integer id) throws Exception {
        return super.delete(data, id); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ClassResponse archive(SchoolData data, Integer id) throws Exception {
        return super.archive(data, id); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ClassResponse update(SchoolData data, _Class entity, AuthenticationResponse authentication) throws Exception {
        check_access(UPDATE_CLASS_PERMISSION);
        //todo: validate
        entity.validate();
        //todo: get the entity by id if exists
        if (entity.getId() == null) {
            throw new BadRequestException("UNIQUE ID MISSING");
        }
        Classes classes = controller.findClass(entity.getId(), data);

        if (entity.getName() != null && !entity.getName().equalsIgnoreCase(classes.getName())) {
            classes.setName(entity.getName());
        }

        if (entity.getCode() != null && !entity.getCode().equalsIgnoreCase(classes.getCode())) {
            classes.setCode(entity.getCode());
        }
        if (entity.getRanking() != null) {
            Long ranking = classes.getRanking();
            if (entity.getRanking() != null && (ranking.intValue() != entity.getRanking())) {
                classes.setRanking(ranking);
            }
        }


        //todo: update
        classes = controller.edit(classes, data);
        return populateResponse(classes);

    }

    @Override
    public ClassResponse getById(SchoolData data, Integer Id) throws Exception {
        return super.getById(data, Id); //To change body of generated methods, choose Tools | Templates.
    }

    public ClassResponse populateResponse(Classes entity) {
        ClassResponse response = new ClassResponse();
        response.setId(entity.getId().intValue());
        response.setName(entity.getName());
        response.setCode(entity.getCode());
        if (entity.getRanking() > 0) {
            Long ranking = entity.getRanking();
            response.setRanking(ranking.intValue());
        }
        response.setDate_created(entity.getDateCreated());
        if (entity.getAuthor() != null) {
            response.setAuthor(entity.getAuthor().getUsername());
        }
        return response;
    }



}
