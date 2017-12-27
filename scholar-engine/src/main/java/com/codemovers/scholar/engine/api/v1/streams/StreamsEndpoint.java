/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codemovers.scholar.engine.api.v1.streams;

import com.codemovers.scholar.engine.api.v1.abstracts.AbstractEndpoint;
import com.codemovers.scholar.engine.api.v1.accounts.entities.AuthenticationResponse;
import com.codemovers.scholar.engine.api.v1.streams.entities.StreamResponse;
import com.codemovers.scholar.engine.api.v1.streams.entities._Stream;
import com.codemovers.scholar.engine.api.v1.users.UserService;
import com.codemovers.scholar.engine.db.entities.SchoolData;
import static com.codemovers.scholar.engine.helper.Utilities.tenantdata;
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
    private AuthenticationResponse authentication = null;

    public StreamsEndpoint() {
        service = new StreamsService();
    }

    @Override
    public void validate(SchoolData schoolData, String authentication) throws Exception {
        this.authentication = UserService.getInstance().validateAuthentication(schoolData, authentication);
    }

    @Override
    public StreamResponse create(_Stream entity, String authentication, HttpServletRequest httpRequest) throws Exception {
        validate(tenantdata, authentication);
        return service.create(tenantdata, entity, this.authentication);
    }

    @Override
    public StreamResponse update(_Stream entity, String authentication, HttpServletRequest httpRequest) throws Exception {
        return super.update(entity, authentication, httpRequest); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Response archive(Integer id, String authentication, HttpServletRequest httpRequest) {
        return super.archive(id, authentication, httpRequest); //To change body of generated methods, choose Tools | Templates.
    }


    @Override
    public Collection<StreamResponse> list(int start, int end, String authentication, HttpServletRequest httpRequest) throws Exception {
        return super.list(start, end, authentication, httpRequest); //To change body of generated methods, choose Tools | Templates.
    }




}
