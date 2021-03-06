package com.codemovers.scholar.engine.helper.exceptions;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author mover
 */
public class BadRequestException extends WebApplicationException {

    private static final Logger LOG = Logger.getLogger(BadRequestException.class.getName());

    public BadRequestException() {
        super(Response.Status.BAD_REQUEST);
    }

    public BadRequestException(String message) {
        this(message, message);
    }

    public BadRequestException(String message, String logMessage) {
        super(
                Response
                        .status(Response.Status.BAD_REQUEST)
                        .entity(new Message(message))
                        .type(MediaType.APPLICATION_JSON_TYPE)
                        .build()
        );
        LOG.log(Level.WARNING, logMessage, Response.Status.BAD_REQUEST);
    }

}
