package com.codemovers.scholar.engine.annotation.helper.exceptions;

import com.codemovers.scholar.v1.backoffice.helper.Utilities;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mover
 */
public class InternalErrorException extends RuntimeException {

    private static final Logger LOG = Logger.getLogger(InternalErrorException.class.getName());

    public InternalErrorException() {
        super("INTERNAL SERVER ERROR");

    }

    public InternalErrorException(String message) {
        this(message, null);
    }

    public InternalErrorException(String message, Throwable ex) {
        super(message, ex);

        LOG.log(Level.SEVERE, message, "INTERNAL SERVER ERROR ");

        if (ex != null) {
            LOG.log(Level.SEVERE, Utilities.getStackTrace(ex));
        }
    }

}
