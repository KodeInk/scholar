/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codemovers.scholar.engine.api.v1.abstracts;

import com.codemovers.scholar.engine.api.v1.users.UserService;
import java.util.Collection;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

/**
 *
 * @author mover 11/15/2017
 * @param <T>
 * @param <Z>
 */
public abstract class AbstractEndpoint<T, Z> {

    UserService service = null;

    public AbstractEndpoint(@HeaderParam("schoolid") String school_name, @HeaderParam("authentication") String authentication) {
        //todo: validate login functionality
        service = UserService.getInstance();

    }

    /**
     *
     * @param school_name
     * @param authentication
     * @param entity
     * @return
     * @throws Exception
     */
    public Z create(@HeaderParam("schoolid") String school_name, @HeaderParam("authentication") String authentication, T entity) throws Exception {
        throw new UnsupportedOperationException("Not Supported ");
    }


    /**
     *
     * @param school_name
     * @param authentication
     * @param id
     * @param entity
     * @return
     */
    public abstract Z update(@HeaderParam("schoolid") String school_name, @HeaderParam("authentication") String authentication, @PathParam("id") Integer id, T entity);

    /**
     *
     * @param id
     * @return
     */
    public abstract Response archive(@PathParam("id") Integer id);

    /**
     *
     * @param id
     * @return
     */
    public abstract Response delete(@PathParam("id") Integer id);

    /**
     *
     * @param start
     * @param end
     * @return
     */
    public abstract Collection<Z> list(@QueryParam("start") int start, @QueryParam("end") int end);




}
