/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codemovers.scholar.engine.api.v1.abstracts;

import com.codemovers.scholar.engine.api.v1.users.UserService;
import com.codemovers.scholar.engine.db.entities.SchoolData;
import com.codemovers.scholar.engine.helper.exceptions.BadRequestException;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

/**
 *
 * @author mover 11/15/2017
 * @param <T>
 * @param <Z>
 */
@Path("/")
public abstract class AbstractEndpoint<T, Z> {

    UserService service = null;

    public void validateAuthentication(SchoolData schoolData, String authentication) {
        try {
            //todo: validate login functionality
            UserService.getInstance().validateAuthentication(schoolData, authentication);
        } catch (Exception ex) {
            Logger.getLogger(AbstractEndpoint.class.getName()).log(Level.SEVERE, null, ex);
            throw new BadRequestException("INVALID CREDENTIALS");
        }
    }

    /**
     *
     * @param entity
     * @param authentication
     * @param httpRequest
     * @return
     * @throws Exception
     */
    public Z create(T entity,
            @HeaderParam("authentication") String authentication,
            @Context HttpServletRequest httpRequest
    ) throws Exception {
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @param authentication
     * @param entity
     * @param httpRequest
     * @return
     */
    public Z update(
            T entity,
            @HeaderParam("authentication") String authentication,
            @Context HttpServletRequest httpRequest) throws Exception {
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @param id
     * @return
     */
    public Z archive(@PathParam("id") Integer id,
            @HeaderParam("authentication") String authentication,
            @Context HttpServletRequest httpRequest) throws Exception {
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @param id
     * @return
     */
    public Response delete(@PathParam("id") Integer id,
            @HeaderParam("authentication") String authentication,
            @Context HttpServletRequest httpRequest
    ) {
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @param start
     * @param end
     * @return
     */
    public Collection<Z> list(
            @QueryParam("start") int start,
            @QueryParam("end") int end,
            @HeaderParam("authentication") String authentication,
            @Context HttpServletRequest httpRequest
    ) throws Exception {
        throw new UnsupportedOperationException();
    }

    protected void validate(SchoolData schoolData, String authentication) throws Exception {
        //todo: checkout to see that the user exiss in the db befor creation

    }

}
