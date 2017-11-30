/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codemovers.scholar.engine.helper.exceptions;

/**
 *
 * @author mover
 */
public class ServiceUnAvailableException extends RuntimeException {

    public static final Message MESSAGE = new Message("service unavailable");

    public ServiceUnAvailableException() {
        super(MESSAGE.getMessage());

    }

}
