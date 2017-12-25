/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codemovers.scholar.engine.api.v1.streams;

import com.codemovers.scholar.engine.api.v1.abstracts.AbstractService;
import com.codemovers.scholar.engine.api.v1.streams.entities.StreamResponse;
import com.codemovers.scholar.engine.api.v1.streams.entities._Stream;
import com.codemovers.scholar.engine.db.controllers.StreamsJpaController;
import com.codemovers.scholar.engine.db.entities.SchoolData;
import java.util.List;
import java.util.logging.Logger;

/**
 *
 * @author mover 12/19/2017
 */
public class StreamsService extends AbstractService<_Stream, StreamResponse> {

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
    public List<StreamResponse> list(SchoolData data, Integer ofset, Integer limit) throws Exception {
        return super.list(data, ofset, limit); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public StreamResponse delete(SchoolData data, Integer id) throws Exception {
        return super.delete(data, id); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public StreamResponse update(SchoolData data, _Stream entity) throws Exception {
        return super.update(data, entity); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public StreamResponse getById(SchoolData data, Integer Id) throws Exception {
        return super.getById(data, Id); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public StreamResponse create(SchoolData data, _Stream entity) throws Exception {
        return super.create(data, entity); //To change body of generated methods, choose Tools | Templates.
    }




}
