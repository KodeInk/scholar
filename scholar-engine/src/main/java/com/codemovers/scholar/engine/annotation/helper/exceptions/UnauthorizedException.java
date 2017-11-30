package com.codemovers.scholar.engine.annotation.helper.exceptions;

/**
 *
 * @author mover
 */
public class UnauthorizedException extends RuntimeException {

    public static final Message MESSAGE = new Message("invalid security credentials");

    public UnauthorizedException() {
        super(MESSAGE.getMessage());
    }

}
