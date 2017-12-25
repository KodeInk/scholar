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
import java.util.Date;
import java.util.logging.Logger;

/**
 *
 * @author mover 12/19/2017
 */
public class ClassService extends AbstractService<_Class, ClassResponse> {

    private static final Logger LOG = Logger.getLogger(UserService.class.getName());

    private final ClassJpaController controller;

    private static ClassService service = null;

    public ClassService() {
        controller = ClassJpaController.getInstance();
    }

    public static ClassService getInstance() {
        if (service == null) {
            service = new ClassService();
        }
        return service;
    }

    private final String[] CREATE_CLASS_PERMISSION = new String[]{"ALL_FUNCTIONS", "CREATE_CLASS"};
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

        controller.create(classes, data);


        return super.create(data, entity); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ClassResponse list(SchoolData data, Integer ofset, Integer limit) throws Exception {
        return super.list(data, ofset, limit); //To change body of generated methods, choose Tools | Templates.
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
    public ClassResponse update(SchoolData data, _Class entity) throws Exception {
        return super.update(data, entity); //To change body of generated methods, choose Tools | Templates.
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
