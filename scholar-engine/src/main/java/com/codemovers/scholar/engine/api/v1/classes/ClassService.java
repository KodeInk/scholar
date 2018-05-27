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
public class ClassService extends AbstractService<_Class, ClassResponse> implements ClassServiceInterface {

    private static final Logger LOG = Logger.getLogger(UserService.class.getName());
    private final ClassJpaController controller;
    private static ClassService service = null;

    /**
     *
     */
    public ClassService() {
        controller = ClassJpaController.getInstance();
    }

    /**
     *
     * @return
     */
    public static ClassService getInstance() {
        if (service == null) {
            service = new ClassService();
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
    public ClassResponse create(SchoolData data, _Class entity, AuthenticationResponse authentication) throws Exception {
        //todo: check permissions
        check_access(CREATE_CLASS_PERMISSION);
        //todo: validate the class creation
        entity.validate();
        //todo: add author_id,  add status enum
        entity.setAuthor_id(authentication.getId());
        entity.setStatus(StatusEnum.ACTIVE);
        Classes classes = getClasses(entity);

        classes = controller.create(classes, data);
        return populateResponse(classes);

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
    public List<ClassResponse> list(SchoolData data, Integer ofset, Integer limit, AuthenticationResponse authentication) throws Exception {
        //todo: check list classes permissions
        check_access(LIST_CLASSES_PERMISSION);

        List<Classes> list = controller.findClassEntities(ofset, limit, data);
        List<ClassResponse> responses = new ArrayList<>();
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
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public ClassResponse delete(SchoolData data, Integer id) throws Exception {
        check_access(DELETE_CLASS_PERMISSION);
        //todo: get class by id
        Classes _class = controller.findClass(id, data);
        if (_class == null) {
            throw new BadRequestException("Record does not exist");
        }
        controller.destroy(_class.getId().intValue(), data);

        return null;
    }

    /**
     *
     * @param data
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public ClassResponse archive(SchoolData data, Integer id, AuthenticationResponse authentication) throws Exception {
        check_access(ARCHIVE_CLASS_PERMISSION);
        //todo: get class by id
        Classes _class = controller.findClass(id, data);
        if (_class == null) {
            throw new BadRequestException("Record does not exist");
        }
        _class.setStatus(StatusEnum.ARCHIVED.toString());
        _class = controller.edit(_class, data);
        return populateResponse(_class);

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

    /**
     *
     * @param data
     * @param Id
     * @return
     * @throws Exception
     */
    @Override
    public ClassResponse getById(SchoolData data, Integer Id) throws Exception {
        check_access(LIST_CLASSES_PERMISSION);
        //todo: get class by id
        Classes _class = controller.findClass(Id, data);
        if (_class == null) {
            throw new BadRequestException("Record does not exist");
        }

        return populateResponse(_class);
    }

    /**
     *
     * @param entity
     * @return
     */
    @Override
    public ClassResponse populateResponse(Classes entity) {
        ClassResponse response = new ClassResponse();
        response.setId(entity.getId().intValue());
        response.setName(entity.getName());
        response.setCode(entity.getCode());
        if (entity.getStatus() != null) {
            response.setStatus(StatusEnum.fromString(entity.getStatus()));
        }
        if (entity.getRanking() != 0) {
            Long ranking = entity.getRanking();
            response.setRanking(ranking.intValue());
        }
        response.setDate_created(entity.getDateCreated());
        if (entity.getAuthor() != null) {
            response.setAuthor(entity.getAuthor().getUsername());
        }
        return response;
    }

    /**
     *
     * @param entity
     * @return
     */
    public Classes getClasses(_Class entity) {
        // call the controller and create the class
        Classes classes = new Classes();
        classes.setAuthor(new Users(entity.getAuthor_id().longValue()));
        classes.setName(entity.getName());
        classes.setCode(entity.getCode());
        classes.setRanking(entity.getRanking());
        classes.setDateCreated(new Date());
        classes.setStatus(entity.getStatus().name());
        return classes;
    }
      
      
}
