package com.codemovers.scholar.engine.helper.exceptions;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author mover
 */
public class UnauthorizedException extends WebApplicationException {

    public static final Message MESSAGE = new Message("invalid security credentials");
    private static final Response.ResponseBuilder RESPONSE_BUILDER = Response
            .status(Response.Status.UNAUTHORIZED)
            .type(MediaType.APPLICATION_JSON_TYPE);

    public UnauthorizedException() {
        super(RESPONSE_BUILDER.entity(MESSAGE).build());
    }

}
