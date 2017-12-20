/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codemovers.scholar.engine.api.v1.streams;

import com.codemovers.scholar.engine.api.v1.abstracts.AbstractEndpoint;
import com.codemovers.scholar.engine.api.v1.streams.entities.StreamResponse;
import com.codemovers.scholar.engine.api.v1.streams.entities._Stream;
import com.codemovers.scholar.engine.api.v1.users.UserService;
import com.codemovers.scholar.engine.db.entities.SchoolData;
import java.util.Collection;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

/**
 *
 * @author mover 12/19/2017
 */
public class StreamsEndpoint extends AbstractEndpoint<_Stream, StreamResponse> {

    private static final Logger LOG = Logger.getLogger(StreamsEndpoint.class.getName());
    @Context
    private ContainerRequestContext context;

    StreamsService service = null;

    public StreamsEndpoint() {
        service = new StreamsService();
    }

    @Override
    public void validate(SchoolData schoolData, String authentication) throws Exception {
        UserService.getInstance().validateAuthentication(schoolData, authentication);
    }

    @Override
    public Collection<StreamResponse> list(int start, int end, String authentication, HttpServletRequest httpRequest) {
        return super.list(start, end, authentication, httpRequest); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Response delete(Integer id, String authentication, HttpServletRequest httpRequest) {
        return super.delete(id, authentication, httpRequest); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public StreamResponse update(_Stream entity, String authentication, HttpServletRequest httpRequest) {
        return super.update(entity, authentication, httpRequest); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public StreamResponse create(_Stream entity, String authentication, HttpServletRequest httpRequest) throws Exception {
        return super.create(entity, authentication, httpRequest); //To change body of generated methods, choose Tools | Templates.
    }

}
