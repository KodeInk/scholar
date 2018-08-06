/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codemovers.scholar.engine.api.v1.classes;

import com.codemovers.scholar.engine.api.v1.abstracts.AbstractService;
import com.codemovers.scholar.engine.api.v1.accounts.entities.AuthenticationResponse;
import com.codemovers.scholar.engine.api.v1.classes.entities.ClassResponse;
import com.codemovers.scholar.engine.api.v1.classes.entities.SchoolClass;
import com.codemovers.scholar.engine.api.v1.streams.StreamsService;
import com.codemovers.scholar.engine.api.v1.streams.entities.StreamResponse;
import com.codemovers.scholar.engine.api.v1.users.UserService;
import com.codemovers.scholar.engine.db.controllers.ClassJpaController;
import com.codemovers.scholar.engine.db.controllers.ClassStreamJpaController;
import com.codemovers.scholar.engine.db.entities.ClassStream;
import com.codemovers.scholar.engine.db.entities.Classes;
import com.codemovers.scholar.engine.db.entities.SchoolData;
import com.codemovers.scholar.engine.db.entities.Streams;
import com.codemovers.scholar.engine.db.entities.Users;
import static com.codemovers.scholar.engine.helper.Utilities.check_access;
import com.codemovers.scholar.engine.helper.enums.StatusEnum;
import com.codemovers.scholar.engine.helper.exceptions.BadRequestException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

/**
 *
 * @author mover 12/19/2017
 */
public class ClassService extends AbstractService<SchoolClass, ClassResponse> implements ClassServiceInterface {

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
    public ClassResponse create(SchoolData data, SchoolClass entity, AuthenticationResponse authentication) throws Exception {
        //todo: check permissions
        check_access(CREATE_CLASS_PERMISSION);
        //todo: validate the class creation
        entity.validate();

        //todo: check if a class exists with the same name or code or ranking
        List<Classes> list = controller.findClasses(entity.getName(), entity.getCode(), entity.getRanking().longValue(), data);

        if (list != null && list.size() > 0) {
            throw new BadRequestException("Class exists with same name code or ranking ");
        }
        //todo: add author_id,  add status enum
        entity.setAuthor_id(authentication.getId());
        entity.setStatus(StatusEnum.ACTIVE);
        Classes classes = populateEntity(entity);

        classes = controller.create(classes, data);

        attachStream(entity, classes, data);

        return populateResponse(classes);

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
    public ClassResponse update(SchoolData data, SchoolClass entity, AuthenticationResponse authentication) throws Exception {
        check_access(UPDATE_CLASS_PERMISSION);
        //todo: validate
        entity.validate();
        //todo: get the entity by id if exists
        if (entity.getId() == null) {
            throw new BadRequestException("UNIQUE ID MISSING");
        }
        Classes classes = getClass(entity.getId(), data);

        populateEntity(entity, classes);

        //todo: check if there is a nother class withthe same ranking or name apart from this class 
        List<Classes> list = controller.findClasses(entity.getId(), entity.getName(), entity.getCode(), entity.getRanking().longValue(), data);

        if (list != null && list.size() > 0) {
            throw new BadRequestException("Another Class exists with same name code or ranking ");
        }

        //todo: update
        classes = controller.edit(classes, data);
        detachStream(classes, data);
        attachStream(entity, classes, data);
        return populateResponse(classes);

    }

    /**
     *
     * @param classes
     * @param data
     * @throws Exception
     */
    public void detachStream(Classes classes, SchoolData data) throws Exception {
        //todo: select from class Stream where class_id = 1;
        //then Destroy :: 
        ClassStreamJpaController.getInstance().deleteClassStreamByClassId(classes.getId().intValue(), data);
         
    }

    /**
     *
     * @param entity
     * @param classes
     * @param data
     */
    public void attachStream(SchoolClass entity, Classes classes, SchoolData data) {
        if (entity.getStreams() != null && classes.getId() != null) {
            //todo: attach streams to office 
            for (Integer stream : entity.getStreams()) {
                Streams streams = StreamsService.getInstance().getStream(stream, data);
                if (streams != null) {
                    ClassStream classStream = new ClassStream();
                    classStream.setStreamClass(classes);
                    classStream.setAuthor(classes.getAuthor());
                    classStream.setClassStream(streams);
                    classStream.setStatus(StatusEnum.ACTIVE.name());
                    classStream.setDateCreated(new Date());
                    //todo: create stream
                    ClassStreamJpaController.getInstance().create(classStream, data);
                }
            }

        }
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
        Classes _class = getClass(id, data);
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
        Classes _class = getClass(id, data);
        if (_class == null) {
            throw new BadRequestException("Record does not exist");
        }
        _class.setStatus(StatusEnum.ARCHIVED.toString());
        _class = controller.edit(_class, data);
        return populateResponse(_class);

    }

    @Override
    public List<ClassResponse> search(SchoolData data, String query, Integer ofset, Integer limit, AuthenticationResponse authentication) throws Exception {
        check_access(ARCHIVE_CLASS_PERMISSION);

        List<Classes> list = controller.query(query, limit, ofset, data);

        List<ClassResponse> classResponses = new ArrayList<>();
        list.forEach(respond -> {
            classResponses.add(populateResponse(respond));
        });

        return classResponses;

    }

    /**
     *
     * @param data
     * @param Id
     * @param authentication
     * @return
     * @throws Exception
     */
    @Override
    public ClassResponse getById(SchoolData data, Integer Id, AuthenticationResponse authentication) throws Exception {
        check_access(LIST_CLASSES_PERMISSION);
        Classes _class = getClass(Id, data);
        if (_class == null) {
            throw new BadRequestException("Record does not exist");
        }

        return populateResponse(_class);
    }

    public Classes getClass(Integer Id, SchoolData data) {
        //todo: get class by id
        Classes _class = controller.findClass(Id, data);

        return _class;
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
            response.setStatus(entity.getStatus());
        }
        if (entity.getRanking() != 0) {
            Long ranking = entity.getRanking();
            response.setRanking(ranking.intValue());
        }
        response.setDate_created(entity.getDateCreated().getTime());
        if (entity.getAuthor() != null) {
            response.setAuthor(entity.getAuthor().getUsername());
        }
        if (entity.getClassStream() != null) {
            Set<Streams> streams = entity.getClassStream();
            List<StreamResponse> streamResponses = new ArrayList<>();
            for (Streams stream : streams) {
                if (stream != null) {
                    Streams classStream = stream;
                    streamResponses.add(populateResponse(classStream));
                }
            }
            response.setStreamResponses(streamResponses);
        }
        return response;
    }

    /**
     *
     * @param classStream
     * @return
     */
    public StreamResponse populateResponse(Streams classStream) {
        StreamResponse streamResponse = new StreamResponse();
        if (classStream != null) {
            streamResponse.setId(classStream.getId().intValue());
            streamResponse.setCode(classStream.getCode());
            streamResponse.setName(classStream.getName());
            streamResponse.setStatus(classStream.getStatus());
            streamResponse.setDate_created(classStream.getDateCreated().getTime());
        }
        return streamResponse;
    }

    /**
     *
     * @param entity
     * @return
     */
    public Classes populateEntity(SchoolClass entity) {
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

    /**
     *
     * @param entity
     * @param classes
     */
    public void populateEntity(SchoolClass entity, Classes classes) {
        if (entity.getName() != null && !entity.getName().equalsIgnoreCase(classes.getName())) {
            classes.setName(entity.getName());
        }

        if (entity.getCode() != null && !entity.getCode().equalsIgnoreCase(classes.getCode())) {
            classes.setCode(entity.getCode());
        }
        if (entity.getRanking() != null) {
            Long ranking = entity.getRanking().longValue();
            classes.setRanking(ranking);

        }
    }

}
