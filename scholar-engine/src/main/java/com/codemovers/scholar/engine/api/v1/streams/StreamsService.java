/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codemovers.scholar.engine.api.v1.streams;

import com.codemovers.scholar.engine.api.v1.abstracts.AbstractService;
import com.codemovers.scholar.engine.api.v1.accounts.entities.AuthenticationResponse;
import static com.codemovers.scholar.engine.api.v1.classes.ClassServiceInterface.LIST_CLASSES_PERMISSION;
import com.codemovers.scholar.engine.api.v1.classes.entities.ClassResponse;
import com.codemovers.scholar.engine.api.v1.streams.entities.StreamResponse;
import com.codemovers.scholar.engine.api.v1.streams.entities._Stream;
import com.codemovers.scholar.engine.db.controllers.StreamsJpaController;
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
import java.util.logging.Logger;

/**
 *
 * @author mover 12/19/2017
 */
public class StreamsService extends AbstractService<_Stream, StreamResponse> implements StreamsServiceInterface {

    private static final Logger LOG = Logger.getLogger(StreamsService.class.getName());
    private final StreamsJpaController controller;
    private static StreamsService service = null;

    public StreamsService() {
        controller = StreamsJpaController.getInstance();
    }

    public static StreamsService getInstance() {
        if (service == null) {
            service = new StreamsService();
        }
        return service;
    }

    @Override
    public StreamResponse create(SchoolData data, _Stream entity, AuthenticationResponse authentication) throws Exception {

        //todo: check access
        check_access(CREATE_STREAM_PERMISSION);
        //todo: validate
        entity.validate();
        //todo; create
        entity.setAuthor_id(authentication.getId());
        entity.setStatus(StatusEnum.ACTIVE);

        Streams stream = new Streams();
        stream.setName(entity.getName());
        stream.setCode(entity.getCode());
        stream.setDateCreated(new Date());
        stream.setAuthor(new Users(entity.getAuthor_id().longValue()));
        stream.setStatus(entity.getStatus().toString());

        stream = controller.create(stream, data);

        return populateResponse(stream);
    }

    @Override
    public StreamResponse update(SchoolData data, _Stream entity, AuthenticationResponse authentication) throws Exception {
        check_access(UPDATE_STREAM_PERMISSION);
        entity.validate();

        if (entity.getId() == null) {
            throw new BadRequestException("UNIQUE ID MISSING");
        }

        Streams stream = controller.findStream(entity.getId(), data);

        if (entity.getName() != null && !entity.getName().equalsIgnoreCase(stream.getName())) {
            stream.setName(entity.getName());
        }

        if (entity.getCode() != null && !entity.getCode().equalsIgnoreCase(stream.getCode())) {
            stream.setCode(entity.getCode());
        }

        stream = controller.edit(stream, data);

        return populateResponse(stream);
    }

    @Override
    public StreamResponse archive(SchoolData data, Integer id, AuthenticationResponse authentication) throws Exception {
        check_access(ARCHIVE_STREAM_PERMISSION);
        Streams _stream = controller.findStream(id, data);
        if (_stream == null) {
            throw new BadRequestException(" Stream does not exist ");
        }

        _stream.setStatus(StatusEnum.ARCHIVED.toString());
        _stream = controller.edit(_stream, data);
        return populateResponse(_stream);
    }

    @Override
    public List<StreamResponse> list(SchoolData data, Integer ofset, Integer limit) throws Exception {
        check_access(LIST_STREAM_PERMISSION);
        List<Streams> list = controller.findStreams(ofset, limit, data);
        List<StreamResponse> responses = new ArrayList<>();
        if (list != null) {

            list.forEach((stream) -> {
                responses.add(populateResponse(stream));
            });
        }

        return responses;
    }

    @Override
    public StreamResponse getById(SchoolData data, Integer Id) throws Exception {
        check_access(LIST_STREAM_PERMISSION);
        Streams _stream = controller.findStream(Id, data);
        return populateResponse(_stream);
                
    }


    @Override
    public StreamResponse delete(SchoolData data, Integer id) throws Exception {
        check_access(DELETE_STREAM_PERMISSION);
        controller.destroy(id, data);
        return null;
    }

    @Override
    public StreamResponse populateResponse(Streams entity) {

        StreamResponse response = new StreamResponse();
        response.setName(entity.getName());
        response.setCode(entity.getCode());
        response.setId(entity.getId().intValue());
        response.setDate_created(entity.getDateCreated());
        response.setStatus(StatusEnum.fromString(entity.getStatus()));

        if (entity.getAuthor() != null) {
            response.setAuthor(entity.getAuthor().getUsername());
        }

        return response;
    }



}
